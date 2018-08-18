package controller.khoi;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import controller.dulich.LoginAction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.ReadExcelFile;
import model.bean.Info;
import model.bean.KhoiThiNganhDHCD;
import model.bean.NganhDHCD;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.KhoiThiNganhDHCDBO;
import model.bo.khoi.NamTuyenSinhBO;
import model.bo.khoi.NganhBO;
import model.bo.khoi.NganhDHCDBO;
import model.bo.khoi.XetTuyenBO;

@SuppressWarnings("serial")
public class CapNhatNganhDHCDAction extends ActionSupport implements ServletRequestAware {
	private File file;
	private List<NganhDHCD> list;
	public HttpServletRequest request;
	private Info info;
	private NganhDHCD nganh;
	private HashMap<String, String> listNganh;
	private HashMap<String, String> listDaoTao;
	private List<KhoiThiNganhDHCD> listKTN = new ArrayList<KhoiThiNganhDHCD>();
	private String[] listMaNganh;
	private String btnUpdateKhoi;
	private String btnUpdateHS;
	private String btnUpdateDiem;
	
	public String getBtnUpdateDiem() {
		return btnUpdateDiem;
	}

	public void setBtnUpdateDiem(String btnUpdateDiem) {
		this.btnUpdateDiem = btnUpdateDiem;
	}

	public String getBtnUpdateKhoi() {
		return btnUpdateKhoi;
	}

	public void setBtnUpdateKhoi(String btnUpdateKhoi) {
		this.btnUpdateKhoi = btnUpdateKhoi;
	}

	public String getBtnUpdateHS() {
		return btnUpdateHS;
	}

	public void setBtnUpdateHS(String btnUpdateHS) {
		this.btnUpdateHS = btnUpdateHS;
	}

	public String[] getListMaNganh() {
		return listMaNganh;
	}

	public void setListMaNganh(String[] listMaNganh) {
		this.listMaNganh = listMaNganh;
	}

	public HashMap<String, String> getListNganh() {
		return listNganh;
	}

	public void setListNganh(HashMap<String, String> listNganh) {
		this.listNganh = listNganh;
	}

	public HashMap<String, String> getListDaoTao() {
		return listDaoTao;
	}

	public void setListDaoTao(HashMap<String, String> listDaoTao) {
		this.listDaoTao = listDaoTao;
	}

	public List<NganhDHCD> getList() {
		return list;
	}

	public void setList(List<NganhDHCD> list) {
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

	public NganhDHCD getNganh() {
		return nganh;
	}

	public void setNganh(NganhDHCD nganh) {
		this.nganh = nganh;
	}

	public List<NganhDHCD> readFile(File file, String maTruong, int namTS) {
		List<NganhDHCD> list = new ArrayList<NganhDHCD>();
		org.apache.poi.ss.usermodel.Workbook workbook;
		try {
			workbook = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				NganhDHCD tr = new NganhDHCD();
				String khoiThi = "";
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
						tr.setChiTieu(((Double) read.getCellValue(nextCell)).intValue());
						break;
					case 3:
						khoiThi = (String) read.getCellValue(nextCell);
						break;
					case 4:
						tr.setGhiChu((String) read.getCellValue(nextCell));
						break;
					}
				}
				tr.setMaTruong(maTruong);
				list.add(tr);
				if(khoiThi != null){
					String[] tmp = khoiThi.split(",");
					for(String kt : tmp){
						KhoiThiNganhDHCD ktn = new KhoiThiNganhDHCD();
						ktn.setMaTruong(tr.getMaTruong());
						ktn.setMaNganh(tr.getMaNganh());
						ktn.setDaoTao(tr.getDaoTao());
						ktn.setNamTuyenSinh(namTS);
						ktn.setMaKhoi(kt);
						listKTN.add(ktn);
					}
				}
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
		if(file != null){
			list = readFile(file, new GiangVienBO().getInfo((String)session.get("soCMND")).getMaTruong(),
					new NamTuyenSinhBO().getNamTuyenSinh());
			if (list.isEmpty()) {
				return "input";
			} else {
				if (new NganhDHCDBO().addListNganhDHCD(list)) {
					if (new KhoiThiNganhDHCDBO().addListKhoiThiNganhDHCD(listKTN)) {
						if(new XetTuyenBO().addListXetTuyen(listKTN)){
							info = new Info("ThÃ´ng bÃ¡o", "Danh sÃ¡ch Ä‘Ã£ Ä‘Æ°á»£c cáº­p nháº­t thÃ nh cÃ´ng!");
						} else {
							info = new Info("ThÃ´ng bÃ¡o","Danh sÃ¡ch Ä‘Ã£ Ä‘Æ°á»£c cáº­p nháº­t. <br/><small>CÃ³ lá»—i trong quÃ¡ trÃ¬nh cáº­p nháº­t há»‡ sá»‘ xÃ©t tuyá»ƒn! vui lÃ²ng kiá»ƒm tra láº¡i.</small>");
						}
					} else {
						info = new Info("ThÃ´ng bÃ¡o", "Danh sÃ¡ch ngÃ nh Ä‘Ã£ cáº­p nháº­t thÃ nh cÃ´ng!<br/>CÃ³ lá»—i trong quÃ¡ trÃ¬nh cáº­p nháº­t khá»‘i thi cho ngÃ nh!");
					}
				} else {
					info = new Info("ThÃ´ng bÃ¡o", "CÃ³ lá»—i trong quÃ¡ trÃ¬nh thá»±c hiá»‡n. Vui lÃ²ng kiá»ƒm tra láº¡i!");
				}
				return "info";
			}
		} else {
			return "inputerror";
		}
	}

	public String themNganhDHCD() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(nganh != null){
			nganh.setMaTruong(new GiangVienBO().getInfo((String)session.get("soCMND")).getMaTruong());
			System.out.println(nganh);
			if (new NganhDHCDBO().insertNganhDHCD(nganh)) {
				//info = new Info("ThÃ´ng bÃ¡o", "Ä�Ã£ thÃªm thÃ nh cÃ´ng!");
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("ktn",nganh.getMaNganh()+"-"+nganh.getDaoTao());
				return SUCCESS;
			} else {
				info = new Info("ThÃ´ng bÃ¡o", "CÃ³ lá»—i trong quÃ¡ trÃ¬nh thá»±c hiá»‡n. Vui lÃ²ng kiá»ƒm tra láº¡i!");
			}
		} else {
			return "inputerror";
		}
		return "info";
	}

	public String showThemNganhDHCD() {
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listNganh = new NganhBO().getAllSelect();
		listDaoTao = new HashMap<String,String>();
		listDaoTao.put("DHCQ", "Ä�áº¡i há»�c chÃ­nh quy");
		listDaoTao.put("DHLT", "Ä�áº¡i há»�c liÃªn thÃ´ng");
		listDaoTao.put("CDCQ", "Cao Ä‘áº³ng chÃ­nh quy");
		listDaoTao.put("CDLT", "Cao Ä‘áº³ng liÃªn thÃ´ng");
		listDaoTao.put("TCCN", "Trung cáº¥p chuyÃªn nghiá»‡p");
		return SUCCESS;
	}
	
	public String showListNganhDHCD() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		list = new NganhDHCDBO().getAllByMaTruong(new GiangVienBO().getInfo((String)session.get("soCMND")).getMaTruong());
		return SUCCESS;
	}
	public String capNhatListNganhDHCD(){		
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnUpdateKhoi != null){
			if(listMaNganh != null && listMaNganh.length == 1){
				ServletActionContext.getRequest().setAttribute("ktn", listMaNganh[0]);
				return "updateKhoi";
			} else {
				if(listMaNganh == null || (listMaNganh != null && listMaNganh.length == 0)){
					info = new Info("","<font style='color:red'>Vui lÃ²ng chá»�n ngÃ nh cáº§n thao tÃ¡c</font>");
				} else {
					info = new Info("","<font style='color:red'>Chá»©c nÄƒng chá»‰ Ã¡p dá»¥ng cho má»™t ngÃ nh</font>");
				}
				return SUCCESS;
			}
		} else {
			if(btnUpdateHS != null){
				if(listMaNganh != null && listMaNganh.length == 1){
					ServletActionContext.getRequest().setAttribute("maNganh", listMaNganh[0]);
					return "updateHS";
				} else {
					if(listMaNganh == null || (listMaNganh != null && listMaNganh.length == 0)){
						info = new Info("","<font style='color:red'>Vui lÃ²ng chá»�n ngÃ nh cáº§n thao tÃ¡c</font>");
					} else {
						info = new Info("","<font style='color:red'>Chá»©c nÄƒng chá»‰ Ã¡p dá»¥ng cho má»™t ngÃ nh</font>");
					}
					return SUCCESS;
				}
			} else {
				if(btnUpdateDiem != null){
					if(listMaNganh != null && listMaNganh.length == 1){
						ServletActionContext.getRequest().setAttribute("maNganh", listMaNganh[0]);
						return "updateDiem";
					} else {
						if(listMaNganh == null || (listMaNganh != null && listMaNganh.length == 0)){
							info = new Info("","<font style='color:red'>Vui lÃ²ng chá»�n ngÃ nh cáº§n thao tÃ¡c</font>");
						} else {
							info = new Info("","<font style='color:red'>Chá»©c nÄƒng chá»‰ Ã¡p dá»¥ng cho má»™t ngÃ nh</font>");
						}
						return SUCCESS;
					}
				} else {
					if(listMaNganh != null && listMaNganh.length > 0){
						new NganhDHCDBO().deleteListNganhByMa(listMaNganh,new GiangVienBO().getInfo((String)session.get("soCMND")).getMaTruong());
						info = new Info("","<font style='color:blue'>Ä�Ã£ xÃ³a cÃ¡c ngÃ nh vá»«a chá»�n</font>");
						return SUCCESS;
					} else {
						info = new Info("","<font style='color:red'>Vui lÃ²ng chá»�n ngÃ nh cáº§n thao tÃ¡c</font>");
						return SUCCESS;
					}
				}
			}
		}
	}
}
