package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.bean.QuanLyCumThi;
import model.dao.khoi.ConnectDB;

public class QuanLyCumThiDAO {

	public QuanLyCumThi getInfo(String soCMND) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,DonViThi,HinhAnh,Logined,TrangThai from QuanLyCumThi where SoCMND=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, soCMND);
    		ResultSet rs = stmt.executeQuery();
    		QuanLyCumThi gv = null;
    		if(rs.next()){
    			gv = new QuanLyCumThi();
    			gv.setSoCMND(rs.getString(1));
    			gv.setHoTen(rs.getString(2));
    			gv.setMatKhau(rs.getString(3));
    			gv.setNgaySinh(rs.getDate(4));
    			gv.setMaXa(rs.getString(5));
    			gv.setMaHuyen(rs.getString(6));
    			gv.setMaTinh(rs.getString(7));
    			gv.setGioiTinh(rs.getBoolean(8));
    			gv.setSoDT(rs.getString(9));
    			gv.setEmail(rs.getString(10));
    			gv.setDonViThi(rs.getString(11));
    			gv.setHinhAnh(rs.getString(12));
    			gv.setLogined(rs.getBoolean(13));
    			gv.setTrangThai(rs.getBoolean(14));
    		} 
        	stmt.close();
        	return gv;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public boolean doiMatKhau(String soCMND, String matKhau) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update QuanLyCumThi set MatKhau=?, Logined=? where SoCMND=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, matKhau);
    		stmt.setBoolean(2, true);
    		stmt.setString(3, soCMND);
    		int rs = stmt.executeUpdate();
        	stmt.close();
        	if(rs > 0){
        		return true;
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
        return false;
	}
	public static void main(String[] args){
		System.out.println(new QuanLyCumThiDAO().getInfo("123456789"));
	}

	public Map<String, Object> getInfoHienThi(String soCMND) {
		HashMap<String,Object> hm;
		PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql="select SoCMND,HoTen,MatKhau,NgaySinh,QuanLyCumThi.MaXa,QuanLyCumThi.MaHuyen,QuanLyCumThi.MaTinh,GioiTinh,SoDT,"
				+ "Email,QuanLyCumThi.DonViThi,HinhAnh,TenXa,TenHuyen,TenTinh,TenCumThi "
				+ "from QuanLyCumThi left join Xa_Phuong on QuanLyCumThi.MaXa=Xa_Phuong.MaXa and Xa_Phuong.MaHuyen = QuanLyCumThi.MaHuyen and Xa_Phuong.MaTinh = QuanLyCumThi.MaTinh "
				+ "inner join Huyen_Quan on Huyen_Quan.MaHuyen = QuanLyCumThi.MaHuyen and QuanLyCumThi.MaTinh=Huyen_Quan.MaTinh "
				+ "inner join Tinh_ThanhPho on Tinh_ThanhPho.MaTinh= QuanLyCumThi.MaTinh "
				+ "inner join CumThi on CumThi.MaCT = QuanLyCumThi.DonViThi where SoCMND=?";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();

			hm = new HashMap<String, Object>();
			if (rs.next()) {
				QuanLyCumThi qlct = new QuanLyCumThi();
				
				qlct.setSoCMND(rs.getString(1));
				qlct.setHoTen(rs.getString(2));
				qlct.setMatKhau(rs.getString(3));
				qlct.setNgaySinh(rs.getDate(4));
				qlct.setMaXa(rs.getString(5));
				qlct.setMaHuyen(rs.getString(6));
				qlct.setMaTinh(rs.getString(7));
				qlct.setGioiTinh(rs.getBoolean(8));
				qlct.setSoDT(rs.getString(9));
				qlct.setEmail(rs.getString(10));
				qlct.setDonViThi(rs.getString(11));
				qlct.setHinhAnh(rs.getString(12));

				hm.put("qlct", qlct);
				hm.put("tenXa", rs.getString(13));
				hm.put("tenHuyen", rs.getString(14));
				hm.put("tenTinh", rs.getString(15));
				hm.put("tenCumThi", rs.getString(16));
			}
			return hm;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}
}
