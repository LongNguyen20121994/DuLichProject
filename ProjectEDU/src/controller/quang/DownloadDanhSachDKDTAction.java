/**
 * 
 */
package controller.quang;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bo.quang.ChiTietDKDTBO;

/**
 * @author MinhQuang
 *
 */
@SuppressWarnings("serial")
public class DownloadDanhSachDKDTAction extends ActionSupport {
	private Info info;
	private String fileDownLoadName;
	private InputStream inputStream;
	private long fileLength;

	String soCMND;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("4".equals(account)) {

				ChiTietDKDTBO chiTietDKDT = new ChiTietDKDTBO();
				ArrayList<ArrayList<ArrayList<String>>> list = chiTietDKDT.getListDKDTByCumThi(soCMND);
				if (list != null) {
					File f = writeExcel(list);
					if (f.exists()) {
						fileDownLoadName = "DanhSachDKDT_" + soCMND + ".xls";
						fileLength = f.length();
						inputStream = new FileInputStream(f);
						return SUCCESS;
					} else {
						info = new Info("Lỗi download", "Không tìm thấy file !");
						return ERROR;
					}

				} else {
					info = new Info("Lỗi download", "Không tìm thấy file !");
					return ERROR;
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

	/**
	 * Write file excel
	 * 
	 * @param listTS
	 * @return
	 */
	@SuppressWarnings("resource")
	private File writeExcel(ArrayList<ArrayList<ArrayList<String>>> list) {

		// Blank workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet;
		ArrayList<ArrayList<String>> array;
		String[] hoTen;
		String hoLot;

		for (int s = 0; s < list.size(); s++) {
			sheet = workbook.createSheet(list.get(s).get(0).get(4) + "_" + list.get(s).get(0).get(5));
			for (int r = 0; r < list.get(s).size(); r++) {
				array = list.get(s);

				// This data needs to be written (Object[])
				Map<String, Object[]> data = new TreeMap<String, Object[]>();
				data.put("1", new Object[] { "STT", "Số CMND", "Họ lót", "Tên", "Số báo danh", "ID phòng", "Điểm" });

				int stt = 1, i = 2;
				for (ArrayList<String> row : array) {
					hoTen = row.get(1).split(" ");
					hoLot = row.get(1).substring(0, row.get(1).length() - hoTen[hoTen.length - 1].length());
					// DangKyDuThi.SoCMND, ThiSinh.HoTen, SBD, IDPhong,
					// MonThi.MaMon, TenMonThi
					data.put(String.valueOf(i), new Object[] { stt, row.get(0), hoLot, hoTen[hoTen.length - 1],
							row.get(2), row.get(3), "" });
					i++;
					stt++;
				}

				// format cell
				// format align
				CellStyle cellAlign = workbook.createCellStyle();
				cellAlign.setAlignment(CellStyle.ALIGN_CENTER);
				cellAlign.setBorderTop((short) 1);
				cellAlign.setBorderBottom((short) 1);
				cellAlign.setBorderLeft((short) 1);
				cellAlign.setBorderRight((short) 1);

				// format border
				CellStyle cellBorder = workbook.createCellStyle();
				cellBorder.setBorderTop((short) 1);
				cellBorder.setBorderBottom((short) 1);
				cellBorder.setBorderLeft((short) 1);
				cellBorder.setBorderRight((short) 1);

				// format font
				CellStyle cellTieuDe = workbook.createCellStyle();
				Font font = workbook.createFont();
				font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				cellTieuDe.setFont(font);
				cellTieuDe.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				cellTieuDe.setAlignment(CellStyle.ALIGN_CENTER);
				cellTieuDe.setBorderTop((short) 1);
				cellTieuDe.setBorderBottom((short) 1);
				cellTieuDe.setBorderLeft((short) 1);
				cellTieuDe.setBorderRight((short) 1);

				// Iterate over data and write to sheet
				Set<String> keyset = data.keySet();
				int rownum = 0;
				for (String key : keyset) {
					Row row = sheet.createRow(rownum++);
					Object[] objArr = data.get(key);
					int cellnum = 0;
					for (Object obj : objArr) {

						Cell cell = row.createCell(cellnum);
						cell.setCellStyle(cellBorder);

						if (rownum == 1) {
							cell.setCellStyle(cellTieuDe);
						} else {
							if (cellnum == 0 || cellnum == 5 || cellnum == 6) {
								cell.setCellStyle(cellAlign);
							}
						}
						if (obj instanceof String) {
							cell.setCellValue((String) obj);
						} else {
							if (obj instanceof Integer) {
								cell.setCellValue((Integer) obj);
							}
						}
						sheet.autoSizeColumn(cellnum);
						cellnum++;
					}
				}
			}
		}

		File f = null;
		FileOutputStream out = null;
		try {
			// Write the workbook in file system
			ServletContext context = ServletActionContext.getServletContext();
			String path = context.getRealPath("") + "\\DownloadDanhSachDuThi\\";
			f = new File(path);
			if (!f.isDirectory()) {
				f.mkdirs();
			}
			f = new File(path + "DanhSachDKDT_" + soCMND + ".xls");
			System.out.println(f.getPath());

			// write file
			out = new FileOutputStream(f);
			workbook.write(out);

			// System.out.println(f.getAbsolutePath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		return f;
	}

	/**
	 * @return the info
	 */
	public Info getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(Info info) {
		this.info = info;
	}

	/**
	 * @return the fileDownLoadName
	 */
	public String getFileDownLoadName() {
		return fileDownLoadName;
	}

	/**
	 * @param fileDownLoadName
	 *            the fileDownLoadName to set
	 */
	public void setFileDownLoadName(String fileDownLoadName) {
		this.fileDownLoadName = fileDownLoadName;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream
	 *            the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the fileLength
	 */
	public long getFileLength() {
		return fileLength;
	}

	/**
	 * @param fileLength
	 *            the fileLength to set
	 */
	public void setFileLength(long fileLength) {
		this.fileLength = fileLength;
	}

}
