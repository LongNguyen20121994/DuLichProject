package model.bo.quang;

import java.util.Map;

import model.bean.QuanLyCumThi;
import model.dao.quang.QuanLyCumThiDAO;

public class QuanLyCumThiBO {

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().doiMatKhau(soCMND,matKhau);
	}

	public QuanLyCumThi getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().getInfo(soCMND);
	}

	public Map<String, Object> getInfoHienThi(String soCMND) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().getInfoHienThi(soCMND);
	}

}
