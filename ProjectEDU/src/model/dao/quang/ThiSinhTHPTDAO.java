package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.bean.ThiSinhTHPT;
import model.dao.khoi.ConnectDB;

public class ThiSinhTHPTDAO {

	public String themListThiSinhTHPT(ArrayList<ThiSinhTHPT> list) {
		ConnectDB connectDB = new ConnectDB();
		String listReturn = "";
		PreparedStatement stmtExecute = null;
		connectDB.openConnection();
		String sqlInsert = "insert into ThiSinh_THPT(SoCMND, MaTinhTHPT, MaTruongTHPT, Lop) values (?,?,?,?)";

		try {
			for (ThiSinhTHPT tr : list) {
				stmtExecute = connectDB.getConnect().prepareStatement(sqlInsert);
				stmtExecute.setString(1, tr.getSoCMND());
				stmtExecute.setString(2, tr.getMaTinhTHPT());
				stmtExecute.setString(3, tr.getMaTruong());
				stmtExecute.setInt(4, tr.getLop());

				if (stmtExecute.executeUpdate() < 1)
					listReturn += tr.getSoCMND() + ", Lop: " + tr.getLop() + "; ";
				stmtExecute.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu đã tồn tại hoặc dữ liệu nhập chưa đúng: "
					+ listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}

	public ArrayList<ThiSinhTHPT> getListUpdate(String soCMND) {
		ArrayList<ThiSinhTHPT> list = new ArrayList<ThiSinhTHPT>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "Select ThiSinh.SoCMND, MaTruongTHPT, MaTinhTHPT, Lop "
				+ "from ThiSinh inner join ThiSinh_THPT on ThiSinh.SoCMND = ThiSinh_THPT.SoCMND "
				+ "where ThiSinh.SoCMND=? Order by Lop ASC";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();
			ThiSinhTHPT tsthpt = null;

			while (rs.next()) {
				tsthpt = new ThiSinhTHPT();
				tsthpt.setSoCMND(rs.getString(1));
				tsthpt.setMaTruong(rs.getString(2));
				tsthpt.setMaTinhTHPT(rs.getString(3));
				tsthpt.setLop(rs.getInt(4));

				list.add(tsthpt);
			}

			stmt.close();
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean updateInfo(ArrayList<ThiSinhTHPT> list) {
		ConnectDB connectDB = new ConnectDB();
		connectDB.openConnection();
		String sqlInsert = "insert into ThiSinh_THPT(SoCMND, MaTinhTHPT, MaTruongTHPT, Lop) values (?,?,?,?)";
		String sqlDelete = "Delete from ThiSinh_THPT where SoCMND =? and Lop = ?";
		PreparedStatement stmtExecute = null;

		try {
			for (ThiSinhTHPT tr : list) {
				stmtExecute = connectDB.getConnect().prepareStatement(sqlDelete);
				stmtExecute.setString(1, tr.getSoCMND());
				stmtExecute.setInt(2, tr.getLop());
				stmtExecute.executeUpdate();

				stmtExecute = connectDB.getConnect().prepareStatement(sqlInsert);
				stmtExecute.setString(1, tr.getSoCMND());
				stmtExecute.setString(2, tr.getMaTinhTHPT());
				stmtExecute.setString(3, tr.getMaTruong());
				stmtExecute.setInt(4, tr.getLop());

				if (stmtExecute.executeUpdate() < 1)
					return false;
				stmtExecute.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			connectDB.closeConnection();
		}
		return true;
	}

	public boolean isComplete(String soCMND) {
		ConnectDB con = new ConnectDB();
		PreparedStatement stmt = null;
		con.openConnection();
		String sql = "Select count(SoCMND) from ThiSinh_THPT where SoCMND =? group by SoCMND";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				if (rs.getInt(1) == 3) {
					stmt.close();
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
		return false;
	}

}
