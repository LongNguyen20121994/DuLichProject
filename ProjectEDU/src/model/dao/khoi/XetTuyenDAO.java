package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.KhoiThiNganhDHCD;
import model.bean.XetTuyen;

public class XetTuyenDAO {

	public boolean addListXetTuyen(List<KhoiThiNganhDHCD> listKTN) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlInsert = "insert into XetTuyen(MaNganh,MaTruong,DaoTao,MaKhoi,NamTuyenSinh,MaMon,HeSo) values (?,?,?,?,?,?,?)";
		String sqlCheck = "select * from XetTuyen where MaNganh=? and MaTruong=? and DaoTao=? and MaKhoi=? and NamTuyenSinh=? and MaMon=?";
		String sqlMaMon = "select MaMon from ChiTietKhoiThi where MaKhoi=?";
		PreparedStatement stmt = null;
		PreparedStatement stmtMon = null;
		try {
			con.getConnect().setAutoCommit(false);
			for (KhoiThiNganhDHCD ktn : listKTN) {
				stmtMon = con.getConnect().prepareStatement(sqlMaMon);
				stmtMon.setString(1, ktn.getMaKhoi());
				ResultSet rs = stmtMon.executeQuery();
				while(rs.next()){
					stmt = con.getConnect().prepareStatement(sqlCheck);
					stmt.setString(1, ktn.getMaNganh());
					stmt.setString(2, ktn.getMaTruong());
					stmt.setString(3, ktn.getDaoTao());
					stmt.setString(4, ktn.getMaKhoi());
					stmt.setInt(5, ktn.getNamTuyenSinh());
					stmt.setString(6, rs.getString(1));
					if (!stmt.executeQuery().next()) {
						stmt = con.getConnect().prepareStatement(sqlInsert);
						stmt.setString(1, ktn.getMaNganh());
						stmt.setString(2, ktn.getMaTruong());
						stmt.setString(3, ktn.getDaoTao());
						stmt.setString(4, ktn.getMaKhoi());
						stmt.setInt(5, ktn.getNamTuyenSinh());
						stmt.setString(6, rs.getString(1));
						stmt.setInt(7, 1);
						stmt.executeUpdate();
					} 
				}
			}
			con.getConnect().commit();
			if(stmtMon != null){
				stmtMon.close();
			}
			if(stmt != null){
				stmt.close();
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.getConnect().rollback();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			return false;
		} finally {
			try {
				con.getConnect().setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			con.closeConnection();
		}
	}
	
	public ArrayList<XetTuyen> getAllMonThiNganh(KhoiThiNganhDHCD ktn){
		ConnectDB con = new ConnectDB();
		con.openConnection();
		ArrayList<XetTuyen> arr = new ArrayList<XetTuyen>();
		String sql = "select MaNganh,MaTruong,DaoTao,MaKhoi,NamTuyenSinh, xt.MaMon,HeSo,TenMonThi "
				+ "from XetTuyen as xt inner join MonThi as mt on xt.MaMon = mt.MaMon "
				+ "where MaNganh=? and MaTruong=? and DaoTao=? and NamTuyenSinh=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ktn.getMaNganh());
			stmt.setString(2, ktn.getMaTruong());
			stmt.setString(3, ktn.getDaoTao());
			stmt.setInt(4, ktn.getNamTuyenSinh());
			ResultSet rs = stmt.executeQuery();
			XetTuyen xt;
			while (rs.next()) {
				xt = new XetTuyen();
				xt.setMaNganh(rs.getString(1));
				xt.setMaTruong(rs.getString(2));
				xt.setDaoTao(rs.getString(3));
				xt.setMaKhoi(rs.getString(4));
				xt.setNamTuyenSinh(rs.getInt(5));
				xt.setMaMonThi(rs.getString(6) + " - " + rs.getString(8));
				xt.setHeSo(rs.getInt(7));
				arr.add(xt);
			} 
			stmt.close();
			return arr;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean updateListXetTuyenNganhDHCD(List<XetTuyen> listXT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlUpdate = "update XetTuyen set HeSo=? where MaNganh=? and MaTruong=? and DaoTao=? and MaKhoi=? "
				+ "and NamTuyenSinh=? and MaMon=?";
		PreparedStatement stmt = null;
		try {
			con.getConnect().setAutoCommit(false);
			for (XetTuyen xt : listXT) {
				stmt = con.getConnect().prepareStatement(sqlUpdate);
				stmt.setInt(1, xt.getHeSo());
				stmt.setString(2, xt.getMaNganh());
				stmt.setString(3, xt.getMaTruong());
				stmt.setString(4, xt.getDaoTao());
				stmt.setString(5, xt.getMaKhoi());
				stmt.setInt(6, xt.getNamTuyenSinh());
				stmt.setString(7, xt.getMaMonThi());
				stmt.executeUpdate();
			}
			con.getConnect().commit();
			if(stmt != null){
				stmt.close();
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.getConnect().rollback();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			return false;
		} finally {
			try {
				con.getConnect().setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			con.closeConnection();
		}
	}

}
