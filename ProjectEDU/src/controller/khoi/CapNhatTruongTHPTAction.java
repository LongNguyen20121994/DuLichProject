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
import model.bean.KhuVucUT;
import model.bean.TruongTHPT;
import model.bo.khoi.KhuVucUTBO;
import model.bo.khoi.TinhThanhPhoBO;
import model.bo.khoi.TruongTHPTBO;

@SuppressWarnings("serial")
public class CapNhatTruongTHPTAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private List<TruongTHPT> list;
	private TruongTHPT truong;
	private HashMap<String, String> listTinh;
	private List<KhuVucUT> listKV;
	public HttpServletRequest request;
	private Info info;

	public List<TruongTHPT> readFile(File file) {
		List<TruongTHPT> list = new ArrayList<TruongTHPT>();
		org.apache.poi.ss.usermodel.Workbook workbook;
		try {
			workbook = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				TruongTHPT tr = new TruongTHPT();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						tr.setMaTinh((String) read.getCellValue(nextCell));
						break;
					case 1:
						tr.setMaTruong((String) read.getCellValue(nextCell));
						break;
					case 2:
						tr.setTenTruong((String) read.getCellValue(nextCell));
						break;
					case 3:
						tr.setDiaChi((String) read.getCellValue(nextCell));
						break;
					case 4:
						tr.setKhuVucUT((String) read.getCellValue(nextCell));
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<TruongTHPT> getList() {
		return list;
	}

	public void setList(List<TruongTHPT> list) {
		this.list = list;
	}

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}

	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}

	public List<KhuVucUT> getListKV() {
		return listKV;
	}

	public void setListKV(List<KhuVucUT> listKV) {
		this.listKV = listKV;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public TruongTHPT getTruong() {
		return truong;
	}

	public void setTruong(TruongTHPT truong) {
		this.truong = truong;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String execute() throws Exception {
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
			if (new TruongTHPTBO().addListTruongTHPT(list)) {
				info = new Info("Thông báo", "Danh sách đã được cập nhật thành công!");
				return "info";
			} else {
				info = new Info("Thông báo", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
				return "info";
			}
		}
	}
	
	public String themTruongTHPT() throws Exception {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if (new TruongTHPTBO().insertTruongTHPT(truong)) {
			info = new Info("Thông báo", "Đã thêm thành công!");
		} else {
			info = new Info("Thông báo", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
		}
		return "info";
	}
	
	public String showThemTruongTHPT(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listTinh = new TinhThanhPhoBO().getAllSelect();
		listKV = new KhuVucUTBO().getAllSelect();
		return SUCCESS;
	}

}
