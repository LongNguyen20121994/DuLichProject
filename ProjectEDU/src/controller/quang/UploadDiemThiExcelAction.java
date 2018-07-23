/**
 * 
 */
package controller.quang;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.ExtractZipRar;
import common.Library;
import model.bean.ChiTietDKDT;
import model.bean.Info;
import model.bo.quang.ChiTietDKDTBO;

/**
 * @author MinhQuang
 * 
 */
@SuppressWarnings("serial")
public class UploadDiemThiExcelAction extends ActionSupport {

	private File danhSachDiem, listAnh;
	private String danhSachDiemFileName, listAnhFileName;
	ArrayList<ChiTietDKDT> listThiSinhDiem;

	private Info info;

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");
		
		if ((listAnh == null && danhSachDiem == null) || listThiSinhDiem == null) {
			info = new Info("Lỗi nhập liệu", "Chưa chọn file upload hoặc nhập file sai định dạng!");
			return ERROR;
		} else {
			
			long size = 0;
			size += danhSachDiem.length() + listAnh.length();
			size = size / 1024 / 1024;
			if (size < 50) {
				if (listAnh != null) {
					if ("rar".equals(Library.getExensionFile(listAnhFileName))) {
						ServletContext context = ServletActionContext.getServletContext();
						String anhPath = context.getRealPath("") + "\\anhThanhVien\\";
						System.out.println(anhPath);
						ExtractZipRar.extractRARFile(anhPath, listAnh);
					} else {
						info = new Info("Lỗi nhập file sai định dạng",
								"File ảnh thí sinh phải là file RAR (.rar)");
						return ERROR;
					}
				}
				
				String extension = Library.getExensionFile(danhSachDiemFileName);
				if ("xlsx".equals(extension) || "xls".equals(extension)) {
					String dong = readExcel();
					
					if ("".equals(dong)) {
						String listDiemError = new ChiTietDKDTBO().updateListDiemThi(soCMND, listThiSinhDiem);
						if (listDiemError != null && !"".equals(listDiemError)) {
							info = new Info("Lỗi nhập liệu", listDiemError);
							return ERROR;
						}else{
							info = new Info("Cập nhật thành công", "Bạn đã cập nhật điểm thi thành công!");
							return ERROR;
						}
					} else {
						info = new Info("Lỗi nhập liệu", "File excel, dòng: " + dong + " nhập không theo định dạng");
						return ERROR;
					}
				} else {
					info = new Info("Lỗi nhập file sai định dạng",
							"File dang sách thí sinh phải là file Excel 2007 hoặc cũ hơn (.xls, .xlsx)");
					return ERROR;
				}
			}else{
				info = new Info("Lỗi nhập file sai định dạng", " file upload phải dưới 50MB");
				return ERROR;
			}
		}
	}

	public String display() {
		return SUCCESS;
	}

	/**
	 * Read file Excel Upload
	 * 
	 * @return
	 */
	private String readExcel() {
		// read file danh sach điểm thi excel
		FileInputStream inputStream = null;
		Workbook workbook = null;
		ChiTietDKDT chiTietDiem;
		String error = "";
		int i = 0;
		int dong = 1;
		// đọc file Excel + lấy dữ liệu
		try {
			workbook = WorkbookFactory.create(danhSachDiem);
			Iterator<Sheet> iteratorSheet = workbook.iterator();
			Sheet sheet;
			Row nextRow;
			Cell nextCell;
			Iterator<Cell> cellIterator;
			Iterator<Row> iterator;
			
			// read sheets
			listThiSinhDiem = new ArrayList<ChiTietDKDT>();
			while(iteratorSheet.hasNext()){
				dong = 1;
				sheet = iteratorSheet.next();
				String[] part = sheet.getSheetName().split("-");
				iterator = sheet.iterator();
				nextRow = iterator.next();
				int columnIndex = 0;

				// read rows
				while (iterator.hasNext()) {
					nextRow = iterator.next();
					cellIterator = nextRow.cellIterator();
					dong = nextRow.getRowNum();
					chiTietDiem = new ChiTietDKDT();

					// read collumn
					while (cellIterator.hasNext()) {
						nextCell = cellIterator.next();
						columnIndex = nextCell.getColumnIndex();

						switch (columnIndex) {
						case 1:
							chiTietDiem.setSoCMND(Library.trimString(getCellValue(nextCell)));
							break;
						case 6:
							chiTietDiem.setDiemThi(Double.valueOf(Library.trimString(getCellValue(nextCell))));
							break;
						}
						chiTietDiem.setMaMonThi(Library.trimString(part[0]));
					}
					listThiSinhDiem.add(chiTietDiem);
					dong++;
				}
				i++;
			}

		} catch (Exception e) {
			System.out.print("read file Excel: " + e.getMessage());
			e.printStackTrace();
			error = "Sheet  thứ: " + (i) + " dong " + dong + ", ";
		} finally {
			try {
				if (workbook != null)
					workbook.close();
				if (inputStream != null)
					inputStream.close();
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
		if (error.length() > 2) {
			error = error.substring(0, error.length() - 2);
		}
		return error;
	}

	/**
	 * return type of value Excell
	 * 
	 * @param cell
	 * @return
	 */
	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();

		case Cell.CELL_TYPE_BLANK:
			return null;
		}

		return null;
	}

	public File getDanhSachDiem() {
		return danhSachDiem;
	}

	public void setDanhSachDiem(File danhSachDiem) {
		this.danhSachDiem = danhSachDiem;
	}

	public String getDanhSachDiemFileName() {
		return danhSachDiemFileName;
	}

	public void setDanhSachDiemFileName(String danhSachDiemFileName) {
		this.danhSachDiemFileName = danhSachDiemFileName;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}
