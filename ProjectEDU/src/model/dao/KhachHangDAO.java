package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.DLKhachHang;
import model.dao.khoi.ConnectDB;

public class KhachHangDAO {

	public DLKhachHang getInfo(String makh) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKH,HoTen,Email,SoDT,DiaChi,GhiChu,MatKhau from KhachHang where MaKH=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, makh);
    		ResultSet rs = stmt.executeQuery();
    		DLKhachHang gv = null;
    		if(rs.next()){
    			gv = new DLKhachHang();
    			gv.setMaKH(rs.getString(1));
    			gv.setHoTen(rs.getString(2));
    			gv.setEmail(rs.getString(3));
    			gv.setSoDT(rs.getString(4));
    			gv.setDiaChi(rs.getString(5));
    			gv.setGhiChu(rs.getString(6));
    			gv.setMatKhau(rs.getString(7));
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
	
	public String getMaxRecord() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKH from KhachHang ORDER BY MaKH DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLKhachHang tur = null;
    		if(rs.next()){
    			tur = new DLKhachHang();
    			tur.setMaKH(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaKH();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}

	public boolean insertKhachHang(DLKhachHang tour) {		
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into KhachHang(MaKH,HoTen,Email,SoDT,DiaChi,GhiChu,MatKhau) values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
        try {
        	stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, tour.getMaKH());
			stmt.setString(2, tour.getHoTen());
			stmt.setString(3, tour.getEmail());
			stmt.setString(4, tour.getSoDT());
			stmt.setString(5, tour.getDiaChi());
			stmt.setString(6, tour.getGhiChu());
			stmt.setString(7, "");
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
	
	public boolean updateKhachHang(String maKh, String pass) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update KhachHang set MatKhau=? where MaKH=?";
        PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, pass);
			stmt.setString(2, maKh);
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
