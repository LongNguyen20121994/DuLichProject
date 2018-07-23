package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import model.dao.khoi.ConnectDB;

public class CumThiDAO {

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> hm;
		PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "Select MaCT,TenCumThi from CumThi";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			hm = new HashMap<String, String>();
			while (rs.next()) {
				hm.put(rs.getString(1), rs.getString(2));
			}
			stmt.close();
			return hm;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

}
