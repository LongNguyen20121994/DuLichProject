package model.bo.quang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.ThiSinh;
import model.bean.ThiSinhTHPT;
import model.dao.quang.ThiSinhDAO;

public class ThiSinhBO {

	private ThiSinhDAO thiSinhDAO = new ThiSinhDAO();

	public boolean isSoCMND(String soCMND){
		return thiSinhDAO.isSoCMND(soCMND);
	}
	
	public ThiSinh getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new ThiSinhDAO().getInfo(soCMND);
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new ThiSinhDAO().doiMatKhau(soCMND, matKhau);
	}

	public boolean insertThiSinh(ThiSinh ts) {
		if ("-1".equals(ts.getMaXa()))
			ts.setMaXa(null);
		if ("-1".equals(ts.getDoiTuongUT()))
			ts.setDoiTuongUT(null);
		return thiSinhDAO.insertThiSinh(ts);
	}

	public ArrayList<ThiSinh> getListInfoPassWord(String soCMNDGiangVien) {
		// TODO Auto-generated method stub
		return thiSinhDAO.getListInfoPassWord(soCMNDGiangVien);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getInfoHienThi(String soCMND) {
		Map<String, Object> hm = thiSinhDAO.getInfoHienThi(soCMND);
		if (hm != null) {
			ArrayList<Object> lop10 = (ArrayList<Object>) hm.get("lop10");
			ArrayList<Object> lop11 = (ArrayList<Object>) hm.get("lop11");
			ArrayList<Object> lop12 = (ArrayList<Object>) hm.get("lop12");
			String maKhuVucUT;
			String tenKhuVucUT;

			if (lop10 != null && lop11 != null && lop12 != null) {
				if (lop10.get(5).equals(lop11.get(6))) {
					tenKhuVucUT = (String) lop10.get(5);
					maKhuVucUT = (String) lop10.get(5);
				} else {
					if (lop10.get(5).equals(lop12.get(5))) {
						tenKhuVucUT = (String) lop10.get(4);
						maKhuVucUT = (String) lop10.get(5);
					} else {
						if (lop11.get(5).equals(lop12.get(5))) {
							tenKhuVucUT = (String) lop11.get(4);
							maKhuVucUT = (String) lop11.get(5);
						} else {
							tenKhuVucUT = (String) lop12.get(4);
							maKhuVucUT = (String) lop12.get(5);
						}
					}
				}
				hm.put("maKhuVucUT", maKhuVucUT);
				hm.put("tenKhuVucUT", tenKhuVucUT);
			}
			return hm;
		} else
			return null;
	}

	public boolean updateInfo(ThiSinh ts) {
		if ("-1".equals(ts.getMaXa()))
			ts.setMaXa(null);
		if ("-1".equals(ts.getDoiTuongUT()))
			ts.setDoiTuongUT(null);
		return thiSinhDAO.updateInfo(ts);
	}

	public List<ThiSinh> getListInfoThiSinhGV(String soCMND) {

		// slipt holot và tên
		List<ThiSinh> list = thiSinhDAO.getListInfoPassWord(soCMND);
		if (list != null) {
			String[] part;
			String hoLot;
			for (ThiSinh ts : list) {
				part = ts.getHoTen().split(" ");
				hoLot = ts.getHoTen().substring(0, ts.getHoTen().length() - part[part.length - 1].length());
				ts.setHoTen(hoLot);
				ts.setMatKhau(part[part.length - 1]);
			}
		}
		return list;
	}

	public String deleteListThiSinhDKDTGV(List<String> listMaTS,int namTS) {

		if (listMaTS != null) {
			return thiSinhDAO.deleteListThiSinhDKDTGV(listMaTS,namTS);
		} else {
			return "fail";
		}
	}

	public String themListDangKyDT(ArrayList<ThiSinh> listTSDK,
			HashMap<String, ArrayList<ThiSinhTHPT>> listThiSinhTHPT) {
		ArrayList<ThiSinhTHPT> listTSTHPT;
		ArrayList<ThiSinh> listTS = new ArrayList<ThiSinh>();
		
		if (listTSDK != null && listThiSinhTHPT != null) {
			for (ThiSinh ts : listTSDK) {
				if (ts.getSoCMND() != null && !ts.getSoCMND().isEmpty())
					listTS.add(ts);
			}
			for (ArrayList<ThiSinhTHPT> arraylist : listThiSinhTHPT.values()) {
				listTSTHPT = new ArrayList<ThiSinhTHPT>();
				for (ThiSinhTHPT tsthpt : arraylist) {
					if (tsthpt.getMaTinhTHPT() != null && tsthpt.getMaTruong() != null && !tsthpt.getMaTinhTHPT().isEmpty()
							&& !tsthpt.getMaTruong().isEmpty())
						listTSTHPT.add(tsthpt);
				}
				arraylist = listTSTHPT;
			}
			
			return thiSinhDAO.themListDangKyDT(listTS,listThiSinhTHPT);
		} else {
			return "fail";
		}
	}

	public String updateListDangKyDT(ArrayList<ThiSinh> listTSDK,
			HashMap<String, ArrayList<ThiSinhTHPT>> listThiSinhTHPT) {
		ArrayList<ThiSinh> listTS;
		ArrayList<ThiSinhTHPT> listTSTHPT;
		listTS = new ArrayList<ThiSinh>();
		
		if (listTSDK != null && listThiSinhTHPT != null) {
			for (ThiSinh ts : listTSDK) {
				if (ts.getSoCMND() != null && !ts.getSoCMND().isEmpty())
					listTS.add(ts);
			}
			for (ArrayList<ThiSinhTHPT> arraylist : listThiSinhTHPT.values()) {
				listTSTHPT = new ArrayList<ThiSinhTHPT>();
				for (ThiSinhTHPT tsthpt : arraylist) {
					if (tsthpt.getMaTinhTHPT() != null && tsthpt.getMaTruong() != null && !tsthpt.getMaTinhTHPT().isEmpty()
							&& !tsthpt.getMaTruong().isEmpty())
						listTSTHPT.add(tsthpt);
				}
				arraylist = listTSTHPT;
			}
			
			return thiSinhDAO.updateListDangKyDT(listTS,listThiSinhTHPT);
		} else {
			return "fail";
		}
	}

}
