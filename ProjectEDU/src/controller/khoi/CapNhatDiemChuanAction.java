package controller.khoi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.ReadExcelFile;
import model.bean.Info;
import model.bean.KhoiThiNganhDHCD;
import model.bo.khoi.ChiTietHoSoBO;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.KhoiThiNganhDHCDBO;
import model.bo.khoi.NamTuyenSinhBO;
import model.bo.khoi.NganhDHCDBO;

@SuppressWarnings("serial")
public class CapNhatDiemChuanAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private List<KhoiThiNganhDHCD> listKTNganh;
	private HashMap<String, String> listNganh;
	private int namTS;
	private String maNganh;
	private String[] maKhoi;
	private String[] diemChuan;
 	public HttpServletRequest request;
	private Info info;
	private String fileDownLoadName;
	private InputStream inputStream;
	private long fileLength;
	
	public HashMap<String, String> getListNganh() {
		return listNganh;
	}

	public void setListNganh(HashMap<String, String> listNganh) {
		this.listNganh = listNganh;
	}

	public int getNamTS() {
		return namTS;
	}

	public void setNamTS(int namTS) {
		this.namTS = namTS;
	}

	public String getMaNganh() {
		return maNganh;
	}

	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}

	public String[] getMaKhoi() {
		return maKhoi;
	}

	public void setMaKhoi(String[] maKhoi) {
		this.maKhoi = maKhoi;
	}

	public String[] getDiemChuan() {
		return diemChuan;
	}

	public void setDiemChuan(String[] diemChuan) {
		this.diemChuan = diemChuan;
	}

	public List<KhoiThiNganhDHCD> getListKTNganh() {
		return listKTNganh;
	}

	public void setListKTNganh(List<KhoiThiNganhDHCD> listKTNganh) {
		this.listKTNganh = listKTNganh;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
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
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String showCapNhatDiemChuan(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listNganh = new NganhDHCDBO().getAllTruongSelect(new GiangVienBO()
				.getInfo((String) session.get("soCMND")).getMaTruong());
		namTS = new NamTuyenSinhBO().getNamTuyenSinh();
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getAttribute("maNganh") != null){
			maNganh = (String) request.getAttribute("maNganh");
		}
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listKTNganh = readFile(file, new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong(), new NamTuyenSinhBO().getNamTuyenSinh());
		if (listKTNganh.isEmpty()) {
			return INPUT;
		} else {
			if(new KhoiThiNganhDHCDBO().updateDiemChuan(listKTNganh)){
				new ChiTietHoSoBO().sendMailTrungTuyen(listKTNganh);
				return INPUT;
			} else {
				return INPUT;
			}
		}
	}
	
	public List<KhoiThiNganhDHCD> readFile(File file, String maTruong, int namTS) {
		List<KhoiThiNganhDHCD> list = new ArrayList<KhoiThiNganhDHCD>();
		org.apache.poi.ss.usermodel.Workbook workbook;
		try {
			workbook = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				KhoiThiNganhDHCD tr = new KhoiThiNganhDHCD();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 1:
						tr.setMaNganh((String) read.getCellValue(nextCell));
						break;
					case 3:
						tr.setDaoTao((String) read.getCellValue(nextCell));
						break;
					case 4:
						tr.setMaKhoi((String) read.getCellValue(nextCell));
						break;
					case 6:
						tr.setDiemChuan((Double) read.getCellValue(nextCell));
						break;
					default: break;
					}
				}
				tr.setMaTruong(maTruong);
				tr.setNamTuyenSinh(namTS);
				list.add(tr);
				System.out.println(tr);
			}
			workbook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	public String capNhatDiemChuan(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		String maTruong = new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong();
		String[] tmp = maNganh.split("-");
		String maNganh = tmp[0];
		String daoTao = tmp[1];
		int namTuyenSinh = namTS;
		KhoiThiNganhDHCD ktn;
		if(diemChuan != null){
			List<KhoiThiNganhDHCD> listKTN = new ArrayList<KhoiThiNganhDHCD>();
			for(int i=0;i<diemChuan.length;i++){
				if(!StringUtils.isBlank(diemChuan[i])){
					ktn = new KhoiThiNganhDHCD();
					ktn.setMaTruong(maTruong);
					ktn.setMaNganh(maNganh);
					ktn.setDaoTao(daoTao);
					ktn.setNamTuyenSinh(namTuyenSinh);
					ktn.setDiemChuan(Double.parseDouble(diemChuan[i]));
					ktn.setMaKhoi(maKhoi[i]);
					listKTN.add(ktn);
				}
			}
			if(new KhoiThiNganhDHCDBO().updateDiemChuan(listKTN)){
				new ChiTietHoSoBO().sendMailTrungTuyen(listKTN);
				return INPUT;
			} else {
				return INPUT;
			}
		}
		return "info";
	}

	public String downloadDanhSachKhoiThiNganhDHCD() throws FileNotFoundException{
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		ArrayList<KhoiThiNganhDHCD> listKTN = new KhoiThiNganhDHCDBO().getAllKhoiThiNganhDHCD(new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong());
		if (listKTN.size() > 0) {
			File f = writeExcel(listKTN);
			if (f.exists()) {
				fileDownLoadName = (String)session.get("soCMND") + ".xls";
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
	}
	
	@SuppressWarnings("resource")
	private File writeExcel(ArrayList<KhoiThiNganhDHCD> listKTN) {
		// Blank workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// Create a blank sheet
		HSSFSheet sheet = workbook.createSheet("Danh sách thí sinh đăng ký");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("00001", new Object[] { "STT", "Mã Ngành", "Tên ngành", "Hệ đào tạo", "Mã Khối", "Năm tuyển sinh", "Điểm chuẩn"});
		int stt = 1, i = 2;
		for (KhoiThiNganhDHCD ktn : listKTN) {
			String key;
			if (i < 10)
				key = "0000" + i;
			else if (i < 100)
				key = "000" + i;
			else if (i < 1000)
				key = "00" + i;
			else if (i < 10000)
				key = "0" + i;
			else
				key = "" + i;
			data.put(key,new Object[] { stt, ktn.getMaNganh(), ktn.getMaTruong(), ktn.getDaoTao(), 
					ktn.getMaKhoi(), ktn.getNamTuyenSinh(), ktn.getDiemChuan()});
			i++;
			stt++;
		}
		// format cell
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
				Cell cell = row.createCell(cellnum++);
				cell.setCellStyle(cellBorder);

				if (rownum == 1) {
					cell.setCellStyle(cellTieuDe);
				}

				if (cellnum == 4 && rownum != 1) {
					cell.setCellStyle(dateStyle);
				}

				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
				else if (obj instanceof Double)
					cell.setCellValue((Double) obj);
			}
			sheet.autoSizeColumn(rownum);
		}
		File f = null;
		try {
			// Write the workbook in file system
			ServletContext context = ServletActionContext.getServletContext();
			String path = context.getRealPath("") + "\\DownloadDanhSachNganh\\";
			f = new File(path);
			if (!f.isDirectory()) {
				f.mkdirs();
			}
			f = new File(path + "Danh Sach Thi Sinh Du Thi.xls");

			// write file
			FileOutputStream out = new FileOutputStream(f);
			workbook.write(out);
			out.close();
			System.out.println(f.getAbsolutePath());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return f;
	}
}
