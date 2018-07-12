package model.dao.longnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.ChiTietKhoiThi;
import model.bean.MonThi;
import model.dao.khoi.ConnectDB;

public class ChiTietKhoiThiDAO {

	public ArrayList<MonThi> getAllMonThi(String maKhoi) {
		ArrayList<MonThi> list = new ArrayList<MonThi>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select ChiTietKhoiThi.MaMon,TenMonThi from ChiTietKhoiThi inner join MonThi on ChiTietKhoiThi.MaMon = MonThi.MaMon where MaKhoi=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, maKhoi);
			ResultSet rs = stmt.executeQuery();
			MonThi mt;
			while (rs.next()) {
				mt = new MonThi();
				mt.setMaMonThi(rs.getString(1));
				mt.setTenMonThi(rs.getString(2));
				list.add(mt);
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

	public static void main(String[] args) {
		ChiTietKhoiThi ct = new ChiTietKhoiThi();
		ct.setMaKhoi("A");
		ct.setMaMon("01");
		System.out.println(new ChiTietKhoiThiDAO().insertChiTietKhoiThi(ct));
	}

	public boolean validMonThi(String maKhoi, String maMon) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select * from ChiTietKhoiThi where MaKhoi=? and MaMon=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, maKhoi);
			stmt.setString(2, maMon);
			if (stmt.executeQuery().next()) {
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

	public boolean deleteListMonThi(String[] listMT, String maKhoi) {
		String str = "('";
        for (int i = 0; i < listMT.length - 1; i++) {
            str += listMT[i] + "','";
        }
        str += listMT[listMT.length - 1] + "')";
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "delete from ChiTietKhoiThi where MaKhoi=? and MaMon in " + str;
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, maKhoi);
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

	public boolean insertChiTietKhoiThi(ChiTietKhoiThi ct) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into ChiTietKhoiThi(MaMon,MaKhoi) values(?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ct.getMaMon());
			stmt.setString(2, ct.getMaKhoi());
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
