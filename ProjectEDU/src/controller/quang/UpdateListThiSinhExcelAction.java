package controller.quang;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
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
import model.bean.DangKyDuThi;
import model.bean.Info;
import model.bean.ThiSinh;
import model.bean.ThiSinhTHPT;
import model.bo.quang.ChiTietDKDTBO;
import model.bo.quang.DangKyDuThiBO;
import model.bo.quang.ThiSinhBO;

@SuppressWarnings("serial")
public class UpdateListThiSinhExcelAction extends ActionSupport {

	private File danhSachCN, listAnh;
	private String danhSachCNFileName, listAnhFileName;
	private ArrayList<ThiSinh> listTSDK;
	private HashMap<String, ArrayList<ThiSinhTHPT>> listThiSinhTHPT;
	private ArrayList<ChiTietDKDT> listCTDKDT;
	private ArrayList<DangKyDuThi> listDangKyDT;

	private ThiSinhBO thiSinhBO;

	private Info info;
	private String nguoiDK = "";

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		nguoiDK = (String) session.get("soCMND");
		DangKyDuThiBO dkdtBO = new DangKyDuThiBO();

		if (nguoiDK != null && !nguoiDK.isEmpty()) {
			String account = (String) session.get("account");
			if ("2".equals(account)) {

				// cập nhật danh sách ảnh
				if (listAnh == null && danhSachCN == null) {
					info = new Info("Lỗi nhập liệu", "Chưa chọn file upload!");
					return ERROR;
				} else {
					long size = 0;
					if (listAnh != null)
						size += listAnh.length();
					if (danhSachCN != null)
						size += danhSachCN.length();
					size = size / 1024 / 1024;
					if (size < 50) {
						if (listAnh != null) {
							if ("rar".equals(Library.getExensionFile(listAnhFileName))) {
								ServletContext context = ServletActionContext.getServletContext();
								String anhPath = context.getRealPath("") + "\\AnhThanhVien\\";
								System.out.println(anhPath);
								ExtractZipRar.extractRARFile(anhPath, listAnh);
							} else {
								info = new Info("Lỗi nhập file sai định dạng",
										"File ảnh thí sinh phải là file RAR (.rar)");
								return ERROR;
							}
						}

						// Cập nhật danh sách thí sinh đăng ký.
						if (danhSachCN != null) {
							System.out.println("filename: " + danhSachCNFileName);
							String extension = Library.getExensionFile(danhSachCNFileName);
							if ("xlsx".equals(extension) || "xls".equals(extension)) {

								String dong = readExcel();
								if ("".equals(dong)) {
									if (listTSDK == null || listThiSinhTHPT == null) {
										info = new Info("Lỗi nhập liệu",
												"File danh sách thí sinh Excel phải theo định dạng !");
										return ERROR;
									} else {

										// cập nhật vào csdl
										thiSinhBO = new ThiSinhBO();
										String listTSDKError = thiSinhBO.updateListDangKyDT(listTSDK, listThiSinhTHPT);
										if (listTSDKError != null && !listTSDKError.isEmpty()) {
											info = new Info("Lỗi nhập liệu", listTSDKError);
											return ERROR;
										}

										if(listDangKyDT != null){
											dkdtBO.updateListDangKy(listDangKyDT);
										}
										
										if(listCTDKDT != null){
											new ChiTietDKDTBO().updateListCTDKDT(listCTDKDT);
										}
									}
								} else {
									info = new Info("Lỗi nhập liệu",
											"File excel, dòng: " + dong + " nhập không theo định dạng");
									return ERROR;
								}
							} else {
								info = new Info("Lỗi nhập file sai định dạng",
										"File dang sách thí sinh phải là file Excel 2007 hoặc cũ hơn (.xls, .xlsx)");
								return ERROR;
							}
						}
					} else {
						info = new Info("Lỗi nhập file sai định dạng", "2 file upload phải dưới 50MB");
						return ERROR;
					}
					info = new Info("Cập nhật thành công", "Upload file thành công!");
					return SUCCESS;
				}
			} else {
				info = new Info("Lỗi", "Bạn không đủ quyền vào trang này!");
				return ERROR;
			}
		} else {
			info = new Info("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}
		return null;
	}

	/**
	 * Read file Excel Upload
	 * 
	 * @return
	 */
	private String readExcel() {

		// read file danh sach dang ky du thi excel
		FileInputStream inputStream = null;
		Workbook workbook = null;
		ArrayList<ThiSinhTHPT> arrayTSTHPT;
		String error = "";
		int dong = 1;

		// đọc file Excel + lấy dữ liệu
		try {
			workbook = WorkbookFactory.create(danhSachCN);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			iterator.next();
			String tenTS = "";
			ThiSinh thiSinh;
			ThiSinhTHPT thiSinhTHPT;
			ChiTietDKDT chiTietDKDT;
			DangKyDuThi dangKyDuThi;
			
			// read rows
			listTSDK = new ArrayList<ThiSinh>();
			listThiSinhTHPT = new HashMap<String, ArrayList<ThiSinhTHPT>>();
			listCTDKDT = new ArrayList<ChiTietDKDT>();
			listDangKyDT = new ArrayList<DangKyDuThi>();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				thiSinh = new ThiSinh();
				String soCMND = "";
				String[] part = null;
				Cell nextCell;
				arrayTSTHPT = new ArrayList<ThiSinhTHPT>();

				// read columns
				while (cellIterator.hasNext()) {
					nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 1:
						tenTS = Library.trimString(getCellValue(nextCell));
						break;
					case 2:
						thiSinh.setHoTen(tenTS + " " + Library.trimString(getCellValue(nextCell)));
						break;
					case 3:
						thiSinh.setGioiTinh(("Nam".equals(Library.trimString(getCellValue(nextCell)))) ? true : false);
						break;
					case 4: {
						thiSinh.setNgaySinh(Library.untilToSql(DateUtil.getJavaDate(Double.valueOf(Library.trimString(getCellValue(nextCell))))));
						break;
					}
					case 5:
						thiSinh.setNoiSinh(Library.trimString(getCellValue(nextCell)));
						break;
					case 6:
						thiSinh.setDanToc(Library.trimString(getCellValue(nextCell)));
						break;
					case 7: {
						soCMND = Library.trimString(getCellValue(nextCell));
						thiSinh.setSoCMND(soCMND);
						break;
					}
					case 8: {
						thiSinh.setMaTinh(Library.trimString((String) getCellValue(nextCell)));
						if(cellIterator.hasNext()){
							nextCell = cellIterator.next();
						}
						thiSinh.setMaHuyen(Library.trimString((String) getCellValue(nextCell)));
						if(cellIterator.hasNext()){
							nextCell = cellIterator.next();
						}
						thiSinh.setMaXa(getCellValue(nextCell) == null ? null : Library.trimString(getCellValue(nextCell)));
						break;
					}
					case 11: {
						part = ((String) getCellValue(nextCell)).split(",");
						thiSinhTHPT = new ThiSinhTHPT();
						thiSinhTHPT.setSoCMND(soCMND);
						thiSinhTHPT.setMaTinhTHPT(Library.trimString(part[0]));
						thiSinhTHPT.setMaTruong(Library.trimString(part[1]));
						thiSinhTHPT.setLop(10);

						arrayTSTHPT.add(thiSinhTHPT);
						break;
					}
					case 12: {
						part = ((String) getCellValue(nextCell)).split(",");
						thiSinhTHPT = new ThiSinhTHPT();
						thiSinhTHPT.setSoCMND(soCMND);
						thiSinhTHPT.setMaTinhTHPT(Library.trimString(part[0]));
						thiSinhTHPT.setMaTruong(Library.trimString(part[1]));
						thiSinhTHPT.setLop(11);

						arrayTSTHPT.add(thiSinhTHPT);
						break;
					}
					case 13: {
						part = ((String) getCellValue(nextCell)).split(",");
						thiSinhTHPT = new ThiSinhTHPT();
						thiSinhTHPT.setSoCMND(soCMND);
						thiSinhTHPT.setMaTinhTHPT(Library.trimString(part[0]));
						thiSinhTHPT.setMaTruong(Library.trimString(part[1]));
						thiSinhTHPT.setLop(12);

						arrayTSTHPT.add(thiSinhTHPT);
						listThiSinhTHPT.put(soCMND, arrayTSTHPT);
						break;
					}
					case 14:
						thiSinh.setDiaChi(Library.trimString(getCellValue(nextCell)));
						break;
					case 15:
						thiSinh.setEmail(Library.trimString(getCellValue(nextCell)));
						break;
					case 16:
						thiSinh.setSoDT(Library.trimString(getCellValue(nextCell)));
						break;
					case 17:
						thiSinh.setDoiTuongUT(Library.trimString(getCellValue(nextCell)));
						break;
					case 18: {
						double n = (double) getCellValue(nextCell);
						thiSinh.setNamTN((int) n);
						break;
					}
					case 19: {
						dangKyDuThi = new DangKyDuThi();
						dangKyDuThi.setSoCMND(soCMND);
						dangKyDuThi.setMaCumThi(Library.trimString(getCellValue(nextCell)));

						// System.out.println(dangKyDuThi);
						listDangKyDT.add(dangKyDuThi);
						break;
					}
					case 20: {
						part = ((String) getCellValue(nextCell)).split(",");
						for (int i = 0; i < part.length; i++) {
							chiTietDKDT = new ChiTietDKDT();
							chiTietDKDT.setMaMonThi(Library.trimString(part[i]));
							chiTietDKDT.setSoCMND(soCMND);
							listCTDKDT.add(chiTietDKDT);
						}
						break;
					}
					}

					thiSinh.setTrangThai(true);
					thiSinh.setHinhAnh("/anhThanhVien/" + soCMND);
					thiSinh.setNguoiDK(nguoiDK);
					Random random = new Random();
					thiSinh.setMatKhau(String.valueOf(random.nextInt()));
				}
				if (soCMND != null && !"".equals(soCMND))
					listTSDK.add(thiSinh);
				dong++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("read file Excel: " + e.getMessage());
			error += dong + ", ";
		} finally {
			try {
				if(workbook != null)
					workbook.close();
				if(inputStream != null)
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

	public File getDanhSachCN() {
		return danhSachCN;
	}

	public void setDanhSachCN(File danhSachCN) {
		this.danhSachCN = danhSachCN;
	}

	public File getListAnh() {
		return listAnh;
	}

	public void setListAnh(File listAnh) {
		this.listAnh = listAnh;
	}

	public String getDanhSachCNFileName() {
		return danhSachCNFileName;
	}

	public void setDanhSachCNFileName(String danhSachCNFileName) {
		this.danhSachCNFileName = danhSachCNFileName;
	}

	public String getListAnhFileName() {
		return listAnhFileName;
	}

	public void setListAnhFileName(String listAnhFileName) {
		this.listAnhFileName = listAnhFileName;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}
