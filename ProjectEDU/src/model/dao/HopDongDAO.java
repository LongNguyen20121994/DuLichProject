package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.DLHopDong;
import model.dao.khoi.ConnectDB;

public class HopDongDAO {
	
	public boolean insertHopDong(DLHopDong hd) {

		// get date now
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);

		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into HopDong(MaHopDong,TenHopDong,NgayKy,MaKH,MaNV,MaChiTietTour,GiaTien,SoTienDatCoc,DieuKhoan) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
        try {
        	stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, hd.getMaHopDong());
			stmt.setString(2, hd.getTenHopDong());
			stmt.setDate(3, date);
			stmt.setString(4, hd.getMaKH());
			stmt.setString(5, "184142854");
			stmt.setString(6, hd.getMaCtTour());
			stmt.setDouble(7, hd.getGiaTien());
			stmt.setDouble(8, hd.getSoTienDc());
			stmt.setString(9, hd.getDieuKhoan());
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
	public String getMaxRecord() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaHopDong from HopDong ORDER BY MaHopDong DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLHopDong tur = null;
    		if(rs.next()){
    			tur = new DLHopDong();
    			tur.setMaHopDong(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaHopDong();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
}
