package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.DLNhanVien;
import model.dao.khoi.ConnectDB;

public class DLNhanVienDAO {
	
	public DLNhanVien getInfo(String soCMND) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,GioiTinh,SoDT,Email,HinhAnh from NhanVien where SoCMND=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, soCMND);
    		ResultSet rs = stmt.executeQuery();
    		DLNhanVien nv = null;
    		if(rs.next()){
    			nv = new DLNhanVien();
    			nv.setSoCMND(rs.getString(1));
    			nv.setHoTen(rs.getString(2));
    			nv.setMatKhau(rs.getString(3));
    			nv.setNgaySinh(rs.getDate(4));
    			nv.setGioiTinh(rs.getBoolean(5));
    			nv.setSoDT(rs.getString(6));
    			nv.setEmail(rs.getString(7));
    			nv.setHinhAnh(rs.getString(8));
    		}
        	stmt.close();
        	return nv;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public boolean insertNhanVien(DLNhanVien nv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select SoCMND from NhanVien where SoCMND=?";
		String sql = "insert into KhachHang(SoCMND,HoTen,MatKhau,NgaySinh,GioiTinh,SoDT,Email,HinhAnh,isAdmin) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, nv.getSoCMND());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, nv.getSoCMND());
    			stmt.setString(2, nv.getHoTen());
    			stmt.setString(3, nv.getMatKhau());
    			stmt.setDate(4, nv.getNgaySinh());
    			stmt.setBoolean(5, nv.isGioiTinh());
    			stmt.setString(6, nv.getSoDT());
    			stmt.setString(7, nv.getEmail());
    			stmt.setString(8, nv.getHinhAnh());
    			stmt.setBoolean(9, nv.isAdmin());
    			check = stmt.executeUpdate();
    		} 
        	stmt.close();
        	if(check > 0){
        		return true;
        	}
        	return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
	}

	public boolean updateNhanVien(DLNhanVien nv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update NhanVien set HoTen=?,NgaySinh=?,GioiTinh=?,"
				+ "SoDT=?,Email=?,HinhAnh=?,isAdmin=? where SoCMND=?";
        PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, nv.getHoTen());
			stmt.setDate(2, nv.getNgaySinh());
			stmt.setBoolean(3, nv.isGioiTinh());
			stmt.setString(4, nv.getSoDT());
			stmt.setString(5, nv.getEmail());
			stmt.setString(6, nv.getHinhAnh());
			stmt.setInt(7, Boolean.compare(nv.isAdmin(), false));
			stmt.setString(8, nv.getSoCMND());
			int check = stmt.executeUpdate();
        	stmt.close();
        	if(check > 0){
        		return true;
        	}
        	return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
	}
}
