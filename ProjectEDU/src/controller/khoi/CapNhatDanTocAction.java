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
import controller.dulich.LoginAction;
import model.bean.DanToc;
import model.bean.Info;
import model.bo.khoi.DanTocBO;

@SuppressWarnings("serial")
public class CapNhatDanTocAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private List<DanToc> listDanToc;
 	public HttpServletRequest request;
	private Info info;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public List<DanToc> getListDanToc() {
		return listDanToc;
	}

	public void setListDanToc(List<DanToc> listDanToc) {
		this.listDanToc = listDanToc;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public void readFile(File file) {
		listDanToc = new ArrayList<DanToc>();
		org.apache.poi.ss.usermodel.Workbook workbook;
		try {
			workbook = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				DanToc dt = new DanToc();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						dt.setMaDT((String) read.getCellValue(nextCell));
						break;
					case 1:
						dt.setTenDT((String) read.getCellValue(nextCell));
						break;
					case 2:
						dt.setGhiChu((String) read.getCellValue(nextCell));
						break;
					}
				}
				listDanToc.add(dt);
			}
			workbook.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
		readFile(file);
		if (listDanToc.isEmpty()) {
			return "input";
		} else {
			new DanTocBO().addListDanToc(listDanToc);
		}
		info = new Info("Thông báo", "Danh sách đã được cập nhật thành công!");
		return "info";
	}

	public String showAddDanToc() {
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
