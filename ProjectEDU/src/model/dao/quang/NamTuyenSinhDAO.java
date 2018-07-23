package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.dao.khoi.ConnectDB;

public class NamTuyenSinhDAO {

	public int getNamTSHienTai() {
		ConnectDB con = new ConnectDB();
		PreparedStatement stmt = null;
		con.openConnection();
		String sql = "Select top 1 NamTS from NamTuyenSinh order by NamTS DESC";

		try {
			int namTS = 0;
			stmt = con.getConnect().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				namTS = rs.getInt(1);
			}
			stmt.close();
			return namTS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		} finally {
			con.closeConnection();
		}
	}

}
