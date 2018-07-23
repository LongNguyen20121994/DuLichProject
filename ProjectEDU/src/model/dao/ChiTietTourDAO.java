package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.DLChiTietTour;
import model.dao.khoi.ConnectDB;

public class ChiTietTourDAO {
	
	public boolean insertChiTietTour(DLChiTietTour ctTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into ChiTietTour(MaChiTietTour,MaTour,MaKS,NgayKhoiHanh,DacDiem,GiaVeNguoiLon,GiaVeTreEm,GiaVeTreNho,GiaVeSoSinh,SoCho,SoChoDaDat) "+
		" values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ctTour.getMaChiTietTour());
			stmt.setString(2, ctTour.getMaTour());
			stmt.setString(3, ctTour.getMaKS());
			stmt.setDate(4, ctTour.getNgayKhoiHanh());
			stmt.setString(5, ctTour.getDacDiem());
			stmt.setDouble(6, ctTour.getGiaVeNguoiLon());
			stmt.setDouble(7, ctTour.getGiaVeTreEm());
			stmt.setDouble(8, ctTour.getGiaVeTreNho());
			stmt.setDouble(9, ctTour.getGiaVeSoSinh());
			stmt.setInt(10, ctTour.getSoCho());
			stmt.setInt(11, ctTour.getSoCho());
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
		String sql = "select MaChiTietTour from ChiTietTour ORDER BY MaChiTietTour DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLChiTietTour tur = null;
    		if(rs.next()){
    			tur = new DLChiTietTour();
    			tur.setMaChiTietTour(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaChiTietTour();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public boolean updateChiTietTour(DLChiTietTour ctTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update ChiTietTour set MaTour=?,MaKS=?,NgayKhoiHanh=?,DacDiem=?,GiaVeNguoiLon=?,GiaVeTreEm=?,"
				+ "GiaVeTreNho=?,GiaVeSoSinh=?,SoCho=?,SoChoDaDat=? where MaChiTietTour=?";
        PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ctTour.getMaTour());
			stmt.setString(2, ctTour.getMaKS());
			stmt.setDate(3, ctTour.getNgayKhoiHanh());
			stmt.setString(4, ctTour.getDacDiem());
			stmt.setDouble(5, ctTour.getGiaVeNguoiLon());
			stmt.setDouble(6, ctTour.getGiaVeTreEm());
			stmt.setDouble(7, ctTour.getGiaVeTreNho());
			stmt.setDouble(8, ctTour.getGiaVeSoSinh());
			stmt.setInt(9, ctTour.getSoCho());
			stmt.setInt(10, ctTour.getSoCho());
			stmt.setString(11, ctTour.getMaChiTietTour());
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
