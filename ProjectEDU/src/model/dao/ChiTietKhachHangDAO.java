package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.DLChiTietKhachHang;
import model.dao.khoi.ConnectDB;

public class ChiTietKhachHangDAO {

	public String getMaxRecord() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaChiTiet from ChiTietKhachHang ORDER BY MaChiTiet DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLChiTietKhachHang tur = null;
    		if(rs.next()){
    			tur = new DLChiTietKhachHang();
    			tur.setMaChiTiet(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaChiTiet();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public boolean insertKhachHang(DLChiTietKhachHang kh) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into ChiTietKhachHang(MaChiTiet,MaKH,HoTen,NgaySinh,GioiTinh,LoaiKH) values(?,?,?,?,?,?)";
		PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, kh.getMaChiTiet());
			stmt.setString(2, kh.getMaKH());
			stmt.setString(3, kh.getHoTen());
			stmt.setDate(4, kh.getNgaySinh());
			stmt.setBoolean(5, kh.isGioiTinh());
			stmt.setInt(6, kh.getLoaiKH());
			if (stmt.executeUpdate() > 0) {
				stmt.close();
				return true;
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			con.closeConnection();
		}
		return false;
	}
}
