package controller.khoi;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.ReadExcelFile;
import controller.dulich.LoginAction;
import model.bean.Info;
import model.bean.KhoiThiNganhDHCD;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.KhoiThiBO;
import model.bo.khoi.KhoiThiNganhDHCDBO;
import model.bo.khoi.NamTuyenSinhBO;
import model.bo.khoi.NganhDHCDBO;
import model.bo.khoi.XetTuyenBO;

@SuppressWarnings("serial")
public class CapNhatKhoiThiNganhDHCDAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private List<KhoiThiNganhDHCD> list;
	public HttpServletRequest request;
	private Info info;
	private KhoiThiNganhDHCD ktn;
	private HashMap<String, String> listNganh;
	private HashMap<String, String> listKhoiThi;
	private String [] listKT;

	public String[] getListKT() {
		return listKT;
	}

	public void setListKT(String[] listKT) {
		this.listKT = listKT;
	}

	public HashMap<String, String> getListNganh() {
		return listNganh;
	}

	public void setListNganh(HashMap<String, String> listNganh) {
		this.listNganh = listNganh;
	}

	public HashMap<String, String> getListKhoiThi() {
		return listKhoiThi;
	}

	public void setListKhoiThi(HashMap<String, String> listKhoiThi) {
		this.listKhoiThi = listKhoiThi;
	}

	public List<KhoiThiNganhDHCD> getList() {
		return list;
	}

	public void setList(List<KhoiThiNganhDHCD> list) {
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

	public KhoiThiNganhDHCD getKtn() {
		return ktn;
	}

	public void setKtn(KhoiThiNganhDHCD ktn) {
		this.ktn = ktn;
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
					case 0:
						tr.setMaNganh((String) read.getCellValue(nextCell));
						break;
					case 1:
						tr.setDaoTao((String) read.getCellValue(nextCell));
						break;
					case 2:
						tr.setMaKhoi((String) read.getCellValue(nextCell));
						break;
					}
				}
				tr.setMaTruong(maTruong);
				tr.setNamTuyenSinh(namTS);
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
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		list = readFile(file, new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong(), new NamTuyenSinhBO().getNamTuyenSinh());
		if (list.isEmpty()) {
			return "input";
		} else {
			if (new KhoiThiNganhDHCDBO().addListKhoiThiNganhDHCD(list)) {
				info = new Info("Thông báo", "Danh sách đã được cập nhật thành công!");
				return "info";
			} else {
				info = new Info("Thông báo", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
				return "info";
			}
		}
	}

	public String themKhoiThiNganhDHCD() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		ktn.setMaTruong(new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong());
		String [] tmp = ktn.getMaNganh().split("-");
		ktn.setMaNganh(tmp[0]);
		ktn.setDaoTao(tmp[1]);
		boolean checkList = false;
		if (listKT != null) {
			checkList = new KhoiThiNganhDHCDBO().deleteListKhoiThi(listKT, ktn);
		}
		if (new KhoiThiNganhDHCDBO().insertKhoiThiNganhDHCD(ktn)) {
			List<KhoiThiNganhDHCD> lktn = new ArrayList<KhoiThiNganhDHCD>();
			lktn.add(ktn);
			new XetTuyenBO().addListXetTuyen(lktn);
			return SUCCESS;
		} else {
			if(checkList){
				return SUCCESS;
			}
			return "info";
		}
	}

	public String showThemKhoiThiNganhDHCD() {
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
		listKhoiThi = new KhoiThiBO().getAllSelect();
		if(ktn == null){
			ktn = new KhoiThiNganhDHCD();
			ktn.setNamTuyenSinh(new NamTuyenSinhBO().getNamTuyenSinh());
			HttpServletRequest request = ServletActionContext.getRequest();
			if(request.getAttribute("ktn") != null){
				ktn.setMaNganh(request.getAttribute("ktn").toString());
			}
		} else {
			ktn.setMaKhoi(null);
		}
		return SUCCESS;
	}
}
