package model.dao;

import java.sql.PreparedStatement;
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
			stmt.setInt(11, ctTour.getSoChoDaDat());
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
