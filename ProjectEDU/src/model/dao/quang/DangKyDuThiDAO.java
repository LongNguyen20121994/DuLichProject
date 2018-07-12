package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.bean.DangKyDuThi;
import model.dao.khoi.ConnectDB;

public class DangKyDuThiDAO {

	public DangKyDuThi getInfo(String soCMND, int namTS) {
		PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "Select MaCT, SBD, NgayDK from DangKyDuThi where SoCMND =? and NamTS=? ";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			stmt.setInt(2, namTS);

			DangKyDuThi dkdt = null;
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				dkdt = new DangKyDuThi();
				dkdt.setMaCumThi(rs.getString(1));
				dkdt.setSoBD(rs.getString(2));
				dkdt.setNgayDK(rs.getDate(3));
				dkdt.setSoBD(soCMND);
				dkdt.setNamTS(namTS);
			}
			stmt.close();
			return dkdt;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean insertDangKy(DangKyDuThi dkdt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into DangKyDuThi(SoCMND,MaCT,NamTS) values(?,?,?)";
		String sqlCheck = "Select MaCT from DangKyDuThi where SoCMND =? and NamTS=? ";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sqlCheck);
			stmt.setString(1, dkdt.getSoCMND());
			stmt.setInt(2, dkdt.getNamTS());
			if (stmt.executeQuery().next()) {
				return false;
			} else {
				stmt = con.getConnect().prepareStatement(sql);
				stmt.setString(1, dkdt.getSoCMND());
				stmt.setString(2, dkdt.getMaCumThi());
				stmt.setInt(3, dkdt.getNamTS());

				int rs = stmt.executeUpdate();
				if (rs == 1) {
					return true;
				}
				stmt.close();
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}

	public boolean validCumThi(String soCMND, String maCumThi, int namTS) {
		ConnectDB con = new ConnectDB();
		PreparedStatement stmt = null;
		con.openConnection();
		String sqlIsDK = "Select SoCMND from DangKyDuThi where SoCMND =? and NamTS=?";
		String sqlIsCT = "Select SoCMND from DangKyDuThi where SoCMND =? and NamTS=? and MaCT=?";

		try {
			stmt = con.getConnect().prepareStatement(sqlIsDK);
			stmt.setString(1, soCMND);
			stmt.setInt(2, namTS);
			ResultSet rs = stmt.executeQuery();
			
			// check is DKDT ?
			if (rs.next()) {
				stmt = con.getConnect().prepareStatement(sqlIsCT);
				stmt.setString(1, soCMND);
				stmt.setInt(2, namTS);
				stmt.setString(3, maCumThi);
				rs = stmt.executeQuery();
				
				// check is MaCT ?
				if (rs.next()) {
					stmt.close();
					return false;
				}else{
					stmt.close();
					return true;
				}
			} else {
				stmt.close();
				return false;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}

	public String insertListDangKy(ArrayList<DangKyDuThi> listDangKyDT) {
		ConnectDB connectDB = new ConnectDB();
		PreparedStatement stmte = null;
		String listReturn = "";
		String sqlInsert = "insert into DangKyDuThi(SoCMND, NamTS, MaCT) values (?,?,?)";
		connectDB.openConnection();
		
		try {
			for (DangKyDuThi dk : listDangKyDT) {

				stmte = connectDB.getConnect().prepareStatement(sqlInsert);
				stmte.setString(1, dk.getSoCMND());
				stmte.setInt(2, dk.getNamTS());
				stmte.setString(3, dk.getMaCumThi());

				if (stmte.executeUpdate() < 1)
					listReturn = listReturn + dk.getSoCMND() + "Mã cụm thi: " + dk.getMaCumThi() + ", ";
				stmte.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			listReturn = " ";
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}

	public String updateListDangKy(ArrayList<DangKyDuThi> listDangKyDT) {
		ConnectDB connectDB = new ConnectDB();
		PreparedStatement stmte = null;
		connectDB.openConnection();
		String listReturn = "";
		String sqlUpdate = "update DangKyDuThi set MaCT=? where SoCMND=? and NamTS=?";
		String sqlNamTS = "Select top 1 NamTS from NamTuyenSinh order by NamTS DESC";

		try {
			
			int namTS = 0;
			stmte = connectDB.getConnect().prepareStatement(sqlNamTS);
			ResultSet rs = stmte.executeQuery();
			
			if (rs.next()) {
				namTS = rs.getInt(1);
			}
			stmte.close();
			
			for (DangKyDuThi dk : listDangKyDT) {
				//System.out.println(dk);
				stmte = connectDB.getConnect().prepareStatement(sqlUpdate);
				
				stmte.setString(1, dk.getMaCumThi());
				stmte.setString(2, dk.getSoCMND());
				stmte.setInt(3, namTS);
				
				if (stmte.executeUpdate() < 1)
					listReturn = listReturn + dk.getSoCMND() + "Mã cụm thi: " + dk.getMaCumThi() + ", ";
				stmte.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			listReturn = " ";
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}

}
