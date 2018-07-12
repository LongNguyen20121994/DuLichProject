package controller.khoi;

import java.io.File;
import java.util.ArrayList;
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
import model.bean.Nganh;
import model.bo.khoi.NganhBO;

@SuppressWarnings("serial")
public class CapNhatNganhAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private List<Nganh> list;
	public HttpServletRequest request;
	private Info info;
	private Nganh nganh;

	public List<Nganh> getList() {
		return list;
	}

	public void setList(List<Nganh> list) {
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

	public Nganh getNganh() {
		return nganh;
	}

	public void setNganh(Nganh nganh) {
		this.nganh = nganh;
	}

	public List<Nganh> readFile(File file) {
		List<Nganh> list = new ArrayList<Nganh>();
		org.apache.poi.ss.usermodel.Workbook workbook;
		try {
			workbook = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Nganh tr = new Nganh();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						tr.setMaNganh((String) read.getCellValue(nextCell));
						break;
					case 1:
						tr.setTenNganh((String) read.getCellValue(nextCell));
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
			if (new NganhBO().addListNganh(list)) {
				info = new Info("Thông báo", "Danh sách đã được cập nhật thành công!");
				return "info";
			} else {
				info = new Info("Thông báo", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
				return "info";
			}
		}
	}

	public String themNganh() {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if (new NganhBO().insertNganh(nganh)) {
			info = new Info("Thông báo", "Đã thêm thành công!");
		} else {
			info = new Info("Thông báo", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
		}
		return "info";
	}

	public String showThemNganh() {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		return SUCCESS;
	}
}
