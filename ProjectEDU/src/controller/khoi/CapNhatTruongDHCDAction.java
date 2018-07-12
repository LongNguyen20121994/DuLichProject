package controller.khoi;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import common.ReadExcelFile;
import model.bean.Info;
import model.bean.TruongDHCD;
import model.bo.khoi.TinhThanhPhoBO;
import model.bo.khoi.TruongDHCDBO;

@SuppressWarnings("serial")
public class CapNhatTruongDHCDAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private List<TruongDHCD> list;
	public HttpServletRequest request;
	private Info info;
	private TruongDHCD truongDHCD;
	private HashMap<String, String> listTinh;

	public List<TruongDHCD> getList() {
		return list;
	}

	public void setList(List<TruongDHCD> list) {
		this.list = list;
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

	public TruongDHCD getTruongDHCD() {
		return truongDHCD;
	}

	public void setTruongDHCD(TruongDHCD truongDHCD) {
		this.truongDHCD = truongDHCD;
	}

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}

	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}

	public List<TruongDHCD> readFile(File file) {
		List<TruongDHCD> list = new ArrayList<TruongDHCD>();
		org.apache.poi.ss.usermodel.Workbook workbook;
		try {
			workbook = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				TruongDHCD tr = new TruongDHCD();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						tr.setMaTruong((String) read.getCellValue(nextCell));
						break;
					case 1:
						tr.setTenTruong((String) read.getCellValue(nextCell));
						break;
					case 2:
						tr.setDiaChi((String) read.getCellValue(nextCell));
						break;
					case 3:
						tr.setGhiChu((String) read.getCellValue(nextCell));
						break;
					}
				}
				list.add(tr);
			}
			workbook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String execute() {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		list = readFile(file);
		if (list.isEmpty()) {
			return "input";
		} else {
			if (new TruongDHCDBO().addListTruongDHCD(list)) {
				info = new Info("Thông báo", "Danh sách đã được cập nhật thành công!");
				return "info";
			} else {
				info = new Info("Thông báo", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
				return "info";
			}
		}
	}

	public String themTruongDHCD() {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if (new TruongDHCDBO().insertTruongDHCD(truongDHCD)) {
			info = new Info("Thông báo", "Đã thêm thành công!");
		} else {
			info = new Info("Thông báo", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
		}
		return "info";
	}

	public String showThemTruongDHCD() {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listTinh = new TinhThanhPhoBO().getAllSelect();
		return SUCCESS;
	}
}
