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
import model.bean.HuyenQuan;
import model.bean.Info;
import model.bean.TinhThanhPho;
import model.bean.XaPhuong;
import model.bo.khoi.HuyenQuanBO;
import model.bo.khoi.TinhThanhPhoBO;
import model.bo.khoi.XaPhuongBO;

@SuppressWarnings("serial")
public class CapNhatTinhHuyenXaAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private List<TinhThanhPho> listTinh;
	private List<HuyenQuan> listHuyen;
	private List<XaPhuong> listXa;
 	public HttpServletRequest request;
	private Info info;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<TinhThanhPho> getListTinh() {
		return listTinh;
	}

	public void setListTinh(List<TinhThanhPho> listTinh) {
		this.listTinh = listTinh;
	}

	public List<HuyenQuan> getListHuyen() {
		return listHuyen;
	}

	public void setListHuyen(List<HuyenQuan> listHuyen) {
		this.listHuyen = listHuyen;
	}

	public List<XaPhuong> getListXa() {
		return listXa;
	}

	public void setListXa(List<XaPhuong> listXa) {
		this.listXa = listXa;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public void readFile(File file) {
		listTinh = new ArrayList<TinhThanhPho>();
		listHuyen = new ArrayList<HuyenQuan>();
		listXa = new ArrayList<XaPhuong>();
		org.apache.poi.ss.usermodel.Workbook workbook;
		try {
			workbook = WorkbookFactory.create(file);
//	Đọc danh sách tỉnh
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				TinhThanhPho ttp = new TinhThanhPho();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						ttp.setMaTinh((String) read.getCellValue(nextCell));
						break;
					case 1:
						ttp.setTenTinh((String) read.getCellValue(nextCell));
						break;
					}
				}
				listTinh.add(ttp);
			}
//		Đọc danh sách huyện
			//Sheet secondSheet = workbook.getSheetAt(1);
			org.apache.poi.ss.usermodel.Sheet secondSheet = workbook.getSheetAt(1);
			iterator = secondSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				HuyenQuan hq = new HuyenQuan();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						hq.setMaTinh((String) read.getCellValue(nextCell));
						break;
					case 1:
						hq.setMaHuyen((String) read.getCellValue(nextCell));
						break;
					case 2:
						hq.setTenHuyen((String) read.getCellValue(nextCell));
						break;
					}
				}
				listHuyen.add(hq);
			}
			
//		Đọc xã phường
			//Sheet thirdSheet = workbook.getSheetAt(2);
			org.apache.poi.ss.usermodel.Sheet thirdSheet = workbook.getSheetAt(2);
			iterator = thirdSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				XaPhuong xp = new XaPhuong();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						xp.setMaTinh((String) read.getCellValue(nextCell));
						break;
					case 1:
						xp.setMaHuyen((String) read.getCellValue(nextCell));
						break;
					case 2:
						xp.setMaXa((String) read.getCellValue(nextCell));
						break;
					case 3:
						xp.setTenXa((String) read.getCellValue(nextCell));
						break;
					}
				}
				listXa.add(xp);
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
		if (listTinh.isEmpty() || listHuyen.isEmpty() || listXa.isEmpty()) {
			return "input";
		} else {
			new TinhThanhPhoBO().addListTinhTP(listTinh);
			new HuyenQuanBO().addListHuyenQuan(listHuyen);
			new XaPhuongBO().addListXaPhuong(listXa);
		}
		info = new Info("Thông báo", "Danh sách đã được cập nhật thành công!");
		return "info";
	}

	public String showAddTinhHuyenXa() {
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
