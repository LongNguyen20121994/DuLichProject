package model.dao.longnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.DangKyDuThi;
import model.dao.khoi.ConnectDB;

public class DangKyDuThiDAO {

	public DangKyDuThi getInfo(String soCMND, int namTS) {

		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "Select SoCMND, MaCT, SBD, NamTS, NgayDK from DangKyDuThi where SoCMND =? and NamTS=? ";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			stmt.setInt(2, namTS);

			DangKyDuThi dkdt = null;
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				dkdt = new DangKyDuThi();
				dkdt.setSoCMND(rs.getString(1));
				dkdt.setMaCumThi(rs.getString(2));
				dkdt.setSoBD(rs.getString(3));
				dkdt.setNgayDK(rs.getDate(4));
				dkdt.setNamTS(rs.getInt(5));
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
	
	public DangKyDuThi getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new DangKyDuThiDAO().getInfo(soCMND);
	}

	public boolean insertDangKy(DangKyDuThi dkdt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into DangKyDuThi(SoCMND,MaCT,SBD,NamTS,NgayDK) values(?,?,?,?,?)";
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
				stmt.setString(3, dkdt.getSoBD());
				stmt.setInt(4, dkdt.getNamTS());
				stmt.setDate(5, dkdt.getNgayDK());

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

	public String themListDangKyDuThi(ArrayList<DangKyDuThi> list) {
		ConnectDB connectDB = new ConnectDB();
		String listExist = "";
		String listReturn = "";
		String sqlInsert = "insert into DangKyDuThi(SoCMND, MaCT, SBD, NamTS, NgayDK values (?,?,?,?,?)";
		String sqlCheck = "select SoCMND from DangKyDuThi where SoCMND=?";
		PreparedStatement stmtc = null;
		PreparedStatement stmte = null;
		connectDB.openConnection();
		try {
			for (DangKyDuThi tr : list) {
				stmtc = connectDB.getConnect().prepareStatement(sqlCheck);
				stmtc.setString(1, tr.getSoCMND());
				if (stmtc.executeQuery().next()) {
					listExist = listExist + tr.getSoCMND() + ", ";
				} else {
					stmte = connectDB.getConnect().prepareStatement(sqlInsert);
					stmte.setString(1, tr.getSoCMND());
					stmte.setString(2, tr.getMaCumThi());
					stmte.setString(3, tr.getSoBD());
					stmte.setInt(4, tr.getNamTS());
					stmte.setDate(5, tr.getNgayDK());

					if (stmte.executeUpdate() < 1)
						listReturn = listReturn + tr.getSoCMND() + ", ";
					stmte.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "True";
		} finally {
			connectDB.closeConnection();
		}
		if (listExist.length() > 2)
			listExist = "Đã tồn tại thí sinh: "
					+ listExist.substring(0, listExist.length() - 2) + ". ";
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: "
					+ listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listExist + listReturn;
	}

	public Map<String, Object> getInfoHienThi(String soCMND) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "Select SoCMND,MaCT,SBD,NamTS,NgayDK from DangKyDuThi where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			// get info danh sách số báo danh
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				DangKyDuThi ts = new DangKyDuThi();

				ts.setSoCMND(soCMND);
				ts.setMaCumThi(rs.getString(1));
				ts.setSoBD(rs.getString(2));
				ts.setNamTS(rs.getInt(3));
				ts.setNgayDK(rs.getDate(4));

				System.out.println(ts);
				result.put("dangKyDuThi", ts);
			}
			stmt.close();
			return result;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean validCumThi(String soCMND, String maCumThi, int namTS) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlIsDK = "Select SoCMND from DangKyDuThi where SoCMND =? and NamTS=?";
		String sqlIsCT = "Select SoCMND from DangKyDuThi where SoCMND =? and NamTS=? and MaCT=?";
		PreparedStatement stmt = null;
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
				} else {
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

	public String updateListDangKy(ArrayList<DangKyDuThi> listDangKyDT) {
		ConnectDB connectDB = new ConnectDB();
		String listReturn = "";
		String sqlUpdate = "update DangKyDuThi set MaCT=? where SoCMND=? and NamTS=?";
		PreparedStatement stmte = null;
		connectDB.openConnection();
		try {
			for (DangKyDuThi dk : listDangKyDT) {
				stmte = connectDB.getConnect().prepareStatement(sqlUpdate);
				stmte.setInt(1, dk.getNamTS());
				stmte.setString(2, dk.getSoCMND());
				stmte.setString(3, dk.getMaCumThi());

				if (stmte.executeUpdate() < 1)
					listReturn = listReturn + dk.getSoCMND() + "Mã cụm thi: "
							+ dk.getMaCumThi() + ", ";
				stmte.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			listReturn = " ";
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: "
					+ listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}

	public String insertListDangKy(ArrayList<DangKyDuThi> listDangKyDT) {
		ConnectDB connectDB = new ConnectDB();
		String listReturn = "";
		String sqlInsert = "insert into DangKyDuThi(SoCMND, NamTS, MaCT) values (?,?,?)";
		PreparedStatement stmte = null;
		connectDB.openConnection();
		try {
			for (DangKyDuThi dk : listDangKyDT) {
				stmte = connectDB.getConnect().prepareStatement(sqlInsert);
				stmte.setString(1, dk.getSoCMND());
				stmte.setInt(2, dk.getNamTS());
				stmte.setString(3, dk.getMaCumThi());

				if (stmte.executeUpdate() < 1)
					listReturn = listReturn + dk.getSoCMND() + "Mã cụm thi: "
							+ dk.getMaCumThi() + ", ";
				stmte.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			listReturn = " ";
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: "
					+ listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}

	public ArrayList<DangKyDuThi> getListInfoPassWord(String soCMNDQuanLyCumThi) {
		ArrayList<DangKyDuThi> list = new ArrayList<DangKyDuThi>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND, MaCT, SBD, NamTS, NgayDK from DangKyDuThi";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMNDQuanLyCumThi);
			ResultSet rs = stmt.executeQuery();
			DangKyDuThi dkdt;

			while (rs.next()) {
				dkdt = new DangKyDuThi();
				dkdt.setSoCMND(rs.getString(1));
				dkdt.setMaCumThi(rs.getString(2));
				dkdt.setSoBD(rs.getString(3));
				dkdt.setNgayDK(rs.getDate(4));
				dkdt.setNamTS(rs.getInt(5));
				list.add(dkdt);
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

	public ArrayList<ArrayList<String>> getListInfoDKDT(
			String soCMNDQuanLyCumThi) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select DangKyDuThi.SoCMND, DangKyDuThi.MaCT, MaMon "
				+ "from DangKyDuThi inner join ChiTietDKDT on DangKyDuThi.SoCMND =  ChiTietDKDT.SoCMND and DangKyDuThi.NamTS =  ChiTietDKDT.NamTS "
				+ "where DangKyDuThi.NamTS = ? and MaCT = ?";
		String sqlMaCT = "Select DonViThi from QuanLyCumThi where SoCMND = ?";
		PreparedStatement stmt = null;
		try {
			int namTS = new NamTuyenSinhDAO().getNamTuyenSinh();

			// get MaCT
			stmt = con.getConnect().prepareStatement(sqlMaCT);
			stmt.setString(1, soCMNDQuanLyCumThi);
			ResultSet rs = stmt.executeQuery();

			String maCT = null;
			if (rs.next())
				maCT = rs.getString(1);
			// end get MaCT

			rs.close();
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setInt(1, namTS);
			stmt.setString(2, maCT);
			rs = stmt.executeQuery();
			ArrayList<String> thiSinh;

			while (rs.next()) {
				thiSinh = new ArrayList<String>();
				thiSinh.add(rs.getString(1));
				thiSinh.add(rs.getString(2));
				thiSinh.add(rs.getString(3));
				System.out.print("df" + rs.getString(3));
				list.add(thiSinh);
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

	public List<DangKyDuThi> getListInfoSoBaoDanh(String soCMND, int namTS) {
		ArrayList<DangKyDuThi> list = new ArrayList<DangKyDuThi>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND, MaCT, SBD, NamTS, NgayDK from DangKyDuThi where NamTS = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setInt(1, namTS);
			ResultSet rs = stmt.executeQuery();
			DangKyDuThi dkdt;

			while (rs.next()) {
				dkdt = new DangKyDuThi();
				dkdt.setSoCMND(rs.getString(1));
				dkdt.setMaCumThi(rs.getString(2));
				dkdt.setSoBD(rs.getString(3));
				dkdt.setNamTS(rs.getInt(4));
				dkdt.setNgayDK(rs.getDate(5));
				list.add(dkdt);
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

	public String updateListSoBaoDanh(ArrayList<DangKyDuThi> listSBD) {

		// System.out.println("dao");
		ConnectDB connectDB = new ConnectDB();
		String listInsert = "";
		String tsTHPT = "";

		String sqlCheck = "select SoCMND from DangKyDuThi where SoCMND=? ";
		//String sqlInsert = "insert into DangKyDuThi(SoCMND, MaCT, SBD, NamTS, NgayDK) values (?,?,?,?,?)";
		String sqlUpdate = "update DangKyDuThi set SoCMND=?, MaCT=?, SBD=?, NamTS=?, NgayDK=? where SoCMND=?";

		PreparedStatement stmtc = null;
		PreparedStatement stmte = null;
		connectDB.openConnection();
		Connection con = connectDB.getConnect();

		Savepoint sp = null;

		for (DangKyDuThi dkdt : listSBD) {
			try {
				con.setAutoCommit(false);
				sp = con.setSavepoint("sp1");

				// Check
				stmtc = con.prepareStatement(sqlCheck);
				stmtc.setString(1, dkdt.getSoCMND());

				// cap nhat so bao danh
				if (stmtc.executeQuery().next()) {
					stmte = connectDB.getConnect().prepareStatement(sqlUpdate);
					stmte.setString(1, dkdt.getMaCumThi());
					stmte.setString(2, dkdt.getSoBD());
					stmte.setInt(3, dkdt.getNamTS());
					stmte.setDate(4, dkdt.getNgayDK());
					stmte.setString(5, dkdt.getSoCMND());

					if (stmte.executeUpdate() < 1) {
						listInsert = listInsert + dkdt.getSoCMND() + ", ";
					} else {

						// check transaction
						if ("".equals(tsTHPT)) {
							stmte.close();
							con.commit();
						} else {
							listInsert += dkdt.getSoCMND() + ", ";
							stmte.close();
							con.rollback(sp);
						}
					}

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				listInsert += dkdt.getSoCMND() + ", ";
				;
				try {
					con.rollback(sp);
				} catch (SQLException e1) {
					System.out.println(e.getMessage());
				}
			} finally {
				try {
					con.setAutoCommit(true);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		connectDB.closeConnection();
		// System.out.println("in: " + listInsert);
		if (listInsert.length() > 2)
			listInsert = "Dữ liệu nhập chưa đúng: "
					+ listInsert.substring(0, listInsert.length() - 2) + ". ";
		// System.out.println("in: " + listInsert);
		return listInsert;
	}

	public boolean updateInfo(DangKyDuThi dk) {
		ConnectDB connectDB = new ConnectDB();
		System.out.println(dk);
		String sql = "update DangKyDuThi set MaCT=?, SBD=?, NamTS=?, NgayDK=? where SoCMND =?";
		try {
			connectDB.openConnection();
			PreparedStatement stmt = connectDB.getConnect().prepareStatement(
					sql);
			stmt.setString(15, dk.getSoCMND());
			stmt.setString(1, dk.getMaCumThi());
			stmt.setString(2, dk.getSoBD());
			stmt.setInt(3, dk.getNamTS());
			stmt.setDate(4, dk.getNgayDK());

			if (stmt.executeUpdate() == 1)
				return true;
			stmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			connectDB.closeConnection();
		}
		return false;
	}

	public String deleteListSoBaoDanh(List<String> listSoCMND, int namTS) {
		ConnectDB connectDB = new ConnectDB();
		Connection con;
		String listReturn = "";
		String sqlTSTHPT = "delete from ThiSinh_THPT where SoCMND = ?";
		String sqlChiTietDKDT = "delete from ChiTietDKDT where SoCMND=? and NamTS=?";
		String sqlDKDT = "delete from DangKyDuThi where SoCMND=? and NamTS=?";
		String sqlThiSinh = "delete from ThiSinh where SoCMND=?";
		PreparedStatement stmt = null;
		Savepoint sp = null;

		connectDB.openConnection();
		con = connectDB.getConnect();

		for (String soCMND : listSoCMND) {
			try {
				System.out.println(soCMND);
				con.setAutoCommit(false);
				sp = con.setSavepoint("sp1");

				// delete Thi sinh THPT
				stmt = connectDB.getConnect().prepareStatement(sqlTSTHPT);
				stmt.setString(1, soCMND);
				stmt.executeUpdate();
				stmt.close();
				
				// Delete Chi tiết DKDT
				stmt = connectDB.getConnect().prepareStatement(sqlChiTietDKDT);
				stmt.setString(1, soCMND);
				stmt.setInt(2, namTS);
				stmt.executeUpdate();
				stmt.close();
				
				// Delete Dang ky du thi
				stmt = connectDB.getConnect().prepareStatement(sqlDKDT);
				stmt.setString(1, soCMND);
				stmt.setInt(2, namTS);
				stmt.executeUpdate();
				stmt.close();
				
				// Delete Thi Sinh
				stmt = connectDB.getConnect().prepareStatement(sqlThiSinh);
				stmt.setString(1, soCMND);

				if (stmt.executeUpdate() != 1) {
					listReturn = listReturn + soCMND + ", ";
				}
				con.commit();
				stmt.close();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				listReturn = soCMND + ", ";
				try {
					con.rollback(sp);
				} catch (SQLException e1) {
					System.out.println(e.getMessage());
				}
			}finally{
				try {
					con.setAutoCommit(true);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		connectDB.closeConnection();

		if (listReturn.length() > 2)
			listReturn = "Không thể xóa thí sinh: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}
}
