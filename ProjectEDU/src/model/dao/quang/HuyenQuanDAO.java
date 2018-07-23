package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import model.bean.HuyenQuan;
import model.dao.khoi.ConnectDB;

public class HuyenQuanDAO {

	public HashMap<String, String> getListHuyenSelect(String maTinh) {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaHuyen,TenHuyen from Huyen_Quan where MaTinh=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, maTinh);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.put(rs.getString(1), rs.getString(2));
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
		return list;
	}

	public boolean addListHuyenQuan(List<HuyenQuan> listHuyen) {
		ConnectDB con = new ConnectDB();
		con.openConnection();

		String sqlInsert = "insert into Huyen_Quan(MaHuyen,MaTinh,TenHuyen) values (?,?,?)";
		String sqlUpdate = "update Huyen_Quan set TenHuyen=? where MaHuyen=? and MaTinh=?";
		String sqlCheck = "select * from Huyen_Quan where MaHuyen=? and MaTinh=?";
		PreparedStatement stmtc = null;
		PreparedStatement stmte = null;
		try {
			for (HuyenQuan hq : listHuyen) {
				stmtc = con.getConnect().prepareStatement(sqlCheck);
				stmtc.setString(1, hq.getMaHuyen());
				stmtc.setString(2, hq.getMaTinh());
				if (stmtc.executeQuery().next()) {
					stmte = con.getConnect().prepareStatement(sqlUpdate);
					stmte.setString(1, hq.getTenHuyen());
					stmte.setString(2, hq.getMaHuyen());
					stmte.setString(3, hq.getMaTinh());
				} else {
					stmte = con.getConnect().prepareStatement(sqlInsert);
					stmte.setString(1, hq.getMaHuyen());
					stmte.setString(2, hq.getMaTinh());
					stmte.setString(3, hq.getTenHuyen());
				}
				stmte.executeUpdate();
			}
			stmtc.close();
			stmte.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}
}
