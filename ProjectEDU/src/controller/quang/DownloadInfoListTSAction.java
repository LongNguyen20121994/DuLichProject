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
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bean.ThiSinh;
import model.bo.quang.ThiSinhBO;

@SuppressWarnings("serial")
public class DownloadInfoListTSAction extends ActionSupport {

	private Info info;
	private ArrayList<ThiSinh> listTS;
	private ThiSinhBO thiSinhBO;

	private String fileDownLoadName;
	private InputStream inputStream;
	private long fileLength;

	private String soCMNDGiaoVien = "";
	// private String passGiaoVien = "";

	@Override
	public String execute() throws Exception {

		Map<String, Object> session = ActionContext.getContext().getSession();
		soCMNDGiaoVien = (String) session.get("soCMND");

		if (soCMNDGiaoVien != null && !soCMNDGiaoVien.isEmpty()) {
			String account = (String) session.get("account");
			if ("2".equals(account)) {

				thiSinhBO = new ThiSinhBO();
				listTS = thiSinhBO.getListInfoPassWord(soCMNDGiaoVien);

				if (listTS != null) {
					File f = writeExcel(listTS);
					if (f.exists()) {
						fileDownLoadName = "DanhSachThiSinh_" + soCMNDGiaoVien + ".xls";
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
	private File writeExcel(ArrayList<ThiSinh> listTS) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		soCMNDGiaoVien = (String) session.get("soCMND");
		// Blank workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// Create a blank sheet
		HSSFSheet sheet = workbook.createSheet("Danh sách thí sinh đăng ký");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] { "STT", "Số CMND", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email",
				"Địa chỉ", "Nơi sinh", "Năm TN", "Mật khẩu" });

		int stt = 1, i = 2;
		for (ThiSinh ts : listTS) {
			data.put(String.valueOf(i),
					new Object[] { stt, ts.getSoCMND(), ts.getHoTen(), ts.getNgaySinh().toString(),
							ts.isGioiTinh() ? "Nam" : "Nữ", ts.getSoDT(), ts.getEmail(), ts.getDiaChi(),
							ts.getNoiSinh(), ts.getNamTN(), ts.getMatKhau() });
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

		// format Date
		DataFormat format = workbook.createDataFormat();
		CellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat(format.getFormat("dd/mm/yyyy"));
		dateStyle.setBorderTop((short) 1);
		dateStyle.setBorderBottom((short) 1);
		dateStyle.setBorderLeft((short) 1);
		dateStyle.setBorderRight((short) 1);

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
					if (cellnum == 0 ) {
						cell.setCellStyle(cellAlign);
					}
				}

				if (cellnum == 4 && rownum != 1) {
					cell.setCellStyle(dateStyle);
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

		File f = null;
		FileOutputStream out = null;
		try {
			// Write the workbook in file system
			ServletContext context = ServletActionContext.getServletContext();
			String path = context.getRealPath("") + "\\DownloadMatKhau\\";
			f = new File(path);
			if (!f.isDirectory()) {
				f.mkdirs();
			}
			f = new File(path + "DanhSachDuThi_" + soCMNDGiaoVien + ".xls");
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

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getFileDownLoadName() {
		return fileDownLoadName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public long getFileLength() {
		return fileLength;
	}

}
