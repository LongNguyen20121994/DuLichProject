package model.dao.quang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.ChiTietDKDT;
import model.bean.DangKyDuThi;
import model.bean.MonThi;
import model.dao.khoi.ConnectDB;

public class ChiTietDKDTDAO {

	public boolean validMonThi(String soCMND, int namTS, String maMon) {
		ConnectDB con = new ConnectDB();
		PreparedStatement stmt = null;
		con.openConnection();
		String sql = "Select SoCMND from ChiTietDKDT where SoCMND =? and NamTS=? and MaMon=?";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			stmt.setInt(2, namTS);
			stmt.setString(3, maMon);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			stmt.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
		return false;
	}

	public ArrayList<MonThi> getAllMonThi(String soCMND, int namTS) {
		ArrayList<MonThi> list;
		ConnectDB con = new ConnectDB();
		PreparedStatement stmt = null;
		con.openConnection();
		String sql = "Select ChiTietDKDT.MaMon,TenMonThi from ChiTietDKDT inner join MonThi on MonThi.MaMon = ChiTietDKDT.MaMon"
				+ " where SoCMND =? and NamTS=?";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			stmt.setInt(2, namTS);

			ResultSet rs = stmt.executeQuery();
			MonThi mt;

			list = new ArrayList<MonThi>();
			while (rs.next()) {
				mt = new MonThi();
				mt.setMaMonThi(rs.getString(1));
				mt.setTenMonThi(rs.getString(2));

				list.add(mt);
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

	public boolean deleteListMonDKDT(String soCMND, int namTS, String[] listMT) {
		String str = "('";
		PreparedStatement stmt = null;
		for (int i = 0; i < listMT.length - 1; i++) {
			str += listMT[i] + "','";
		}
		str += listMT[listMT.length - 1] + "')";
		ConnectDB con = new ConnectDB();
		con.openConnection();

		String sql = "delete from ChiTietDKDT where SoCMND=? and NamTS=? and MaMon in " + str;
		String sqlCheck = " Select SoCMND from ChiTietDKDT where SoCMND =? and NamTS=?";
		String sqlDelDKDT = "delete from DangKyDuThi where SoCMND=? and NamTS=?";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			stmt.setInt(2, namTS);

			// Xóa môn
			if (stmt.executeUpdate() > 0) {
				stmt.close();

				// kiểm tra còn môn
				stmt = con.getConnect().prepareStatement(sqlCheck);
				stmt.setString(1, soCMND);
				stmt.setInt(2, namTS);

				if (!stmt.executeQuery().next()) {

					// Xóa DKDT
					stmt = con.getConnect().prepareStatement(sqlDelDKDT);
					stmt.setString(1, soCMND);
					stmt.setInt(2, namTS);
					stmt.executeUpdate();
				}
				return true;
			}
			stmt.close();
			return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}

	public boolean insertChiTietDKDT(DangKyDuThi dkdt, ChiTietDKDT ctDKDT) {
		ConnectDB conDB = new ConnectDB();
		conDB.openConnection();
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = "insert ChiTietDKDT(SoCMND,NamTS,MaMon) values(?,?,?)";
		String sqlIn = "insert into DangKyDuThi(SoCMND,MaCT,NamTS) values(?,?,?)";
		String sqlCheck = "Select MaCT from DangKyDuThi where SoCMND =? and NamTS=? ";

		try {

			// check đã đăng ký dự thi chưa ?
			con = conDB.getConnect();
			stmt = con.prepareStatement(sqlCheck);
			stmt.setString(1, dkdt.getSoCMND());
			stmt.setInt(2, dkdt.getNamTS());

			if (stmt.executeQuery().next()) {
				stmt.close();

				// Đăng ký chi tiết dự thi
				stmt = con.prepareStatement(sql);
				stmt.setString(1, ctDKDT.getSoCMND());
				stmt.setInt(2, ctDKDT.getNamTS());
				stmt.setString(3, ctDKDT.getMaMonThi());

			} else {
				stmt.close();

				// Đăng ký dự thi
				stmt = con.prepareStatement(sqlIn);
				stmt.setString(1, dkdt.getSoCMND());
				stmt.setString(2, dkdt.getMaCumThi());
				stmt.setInt(3, dkdt.getNamTS());
				int rs = stmt.executeUpdate();

				if (rs == 1) {
					stmt.close();
					// Đăng ký chi tiết dự thi
					stmt = con.prepareStatement(sql);
					stmt.setString(1, ctDKDT.getSoCMND());
					stmt.setInt(2, ctDKDT.getNamTS());
					stmt.setString(3, ctDKDT.getMaMonThi());
				}

			}
			int rs = stmt.executeUpdate();
			if (rs == 1) {
				return true;
			}
			stmt.close();
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println(e.getMessage());
			}
			return false;
		} finally {
			conDB.closeConnection();
		}
	}

	public String themListCTDKDT(ArrayList<ChiTietDKDT> listCTDKDT) {
		ConnectDB connectDB = new ConnectDB();
		PreparedStatement stmte = null;
		String listReturn = "";
		String sqlInsert = "insert into ChiTietDKDT(SoCMND, NamTS, MaMon) values (?,?,?)";
		connectDB.openConnection();

		try {
			for (ChiTietDKDT ct : listCTDKDT) {
				stmte = connectDB.getConnect().prepareStatement(sqlInsert);
				stmte.setString(1, ct.getSoCMND());
				stmte.setInt(2, ct.getNamTS());
				stmte.setString(3, ct.getMaMonThi());

				if (stmte.executeUpdate() < 1)
					listReturn = listReturn + ct.getSoCMND() + "Mã môn: " + ct.getMaMonThi() + ", ";
				stmte.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}

	public String updateListCTDKDT(ArrayList<ChiTietDKDT> listCTDKDT) {
		PreparedStatement stmte = null;
		ConnectDB connectDB = new ConnectDB();
		String listReturn = "";
		String sqlInsert = "insert into ChiTietDKDT(SoCMND, NamTS, MaMon) values (?,?,?)";
		String sqlNamTS = "Select top 1 NamTS from NamTuyenSinh order by NamTS DESC";
		String sqlCheck = "Select SoCMND from ChiTietDKDT where SoCMND =? and NamTS=? and MaMon=?";
		String sqlDel;
		String str = "('";
		String soCMND;
		String maMon;
		connectDB.openConnection();

		try {
			int namTS = 0;
			stmte = connectDB.getConnect().prepareStatement(sqlNamTS);
			ResultSet rs = stmte.executeQuery();

			if (rs.next()) {
				namTS = rs.getInt(1);
			}
			stmte.close();
			int len = listCTDKDT.size();

			for (int i = 0; i < len; i++) {
				soCMND = listCTDKDT.get(i).getSoCMND();
				maMon = listCTDKDT.get(i).getMaMonThi();
				str += maMon + "','";

				// check chi tiết Exist
				stmte = connectDB.getConnect().prepareStatement(sqlCheck);
				stmte.setString(1, soCMND);
				stmte.setInt(2, namTS);
				stmte.setString(3, maMon);

				if (!stmte.executeQuery().next()) {
					// insert chi tiết
					stmte = connectDB.getConnect().prepareStatement(sqlInsert);

					stmte.setString(1, soCMND);
					stmte.setInt(2, namTS);
					stmte.setString(3, maMon);

					if (stmte.executeUpdate() < 1) {
						listReturn = listReturn + soCMND + "Mã môn: " + maMon + ", ";
					}
					stmte.close();
				}

				// Xóa chi tiết
				if (i < (len - 1)) {
					if (!soCMND.equals(listCTDKDT.get(i + 1).getSoCMND())) {
						str = str.substring(0, (str.length() - 3)) + "')";
						sqlDel = "delete from ChiTietDKDT where SoCMND=? and NamTS=? and MaMon not in " + str;
						// System.out.println(soCMND + namTS + sqlDel);
						stmte = connectDB.getConnect().prepareStatement(sqlDel);
						stmte.setString(1, soCMND);
						stmte.setInt(2, namTS);

						stmte.executeUpdate();
						stmte.close();
						str = "('";
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}

	public String updateListDiemThi(String soCMNDCT, ArrayList<ChiTietDKDT> listThiSinhDiem) {
		ConnectDB connectDB = new ConnectDB();
		Connection con;
		String listChuaThi = "";
		String listDangKy = "";
		String listError = "";
		String listCT = "";
		String sqlUpdate = "update ChiTietDKDT set DiemThi = ? where SoCMND = ? and MaMon = ? and NamTS = ?";
		String sqlNamTS = "Select top 1 NamTS from NamTuyenSinh order by NamTS DESC";
		String sqlCheck = "Select MaCT, IDPhong from ChiTietDKDT inner join DangKyDuThi "
				+ "on DangKyDuThi.SoCMND = ChiTietDKDT.SoCMND and ChiTietDKDT.NamTS = DangKyDuThi.NamTS"
				+ "  where DangKyDuThi.SoCMND =? and DangKyDuThi.NamTS=? and MaMon=?";
		String sqlCheckCT = "Select DonViThi from QuanLyCumThi where SoCMND = ? ";
		PreparedStatement stmte = null;
		connectDB.openConnection();
		con = connectDB.getConnect();

		String soCMND;
		String maMon;
		String iDPhong;
		String cumThiDK;
		String cumThiCT;
		double diem;

		try {
			con.setAutoCommit(false);

			// get namTS
			int namTS = 0;
			stmte = con.prepareStatement(sqlNamTS);
			ResultSet rs = stmte.executeQuery();
			int kq = 0;

			if (rs.next()) {
				namTS = rs.getInt(1);
			}
			stmte.close();
			int len = listThiSinhDiem.size();

			for (int i = 0; i < len; i++) {
				//System.out.println(listThiSinhDiem.get(i));
				soCMND = listThiSinhDiem.get(i).getSoCMND();
				maMon = listThiSinhDiem.get(i).getMaMonThi();
				diem = listThiSinhDiem.get(i).getDiemThi();

				// check chi tiết Exist
				stmte = con.prepareStatement(sqlCheck);
				stmte.setString(1, soCMND);
				stmte.setInt(2, namTS);
				stmte.setString(3, maMon);

				rs = stmte.executeQuery();
				if (rs.next()) {
					cumThiDK = rs.getString(1);
					iDPhong = rs.getString(2);
					stmte.close();

					// check thi
					if (iDPhong != null && !"".equals(iDPhong)) {
						stmte = con.prepareStatement(sqlCheckCT);
						stmte.setString(1, soCMNDCT);
						rs = stmte.executeQuery();
						rs.next();
						cumThiCT = rs.getString(1);
						stmte.close();
						
						// check cum thi
						if (cumThiCT.equals(cumThiDK)) {
							
							// update chi tiết
							stmte = con.prepareStatement(sqlUpdate);
							stmte.setDouble(1, diem);
							stmte.setString(2, soCMND);
							stmte.setString(3, maMon);
							stmte.setInt(4, namTS);

							//System.out.println(namTS + ", " + soCMND+ ", " + maMon+ ", " + namTS +", "+diem);
							kq = stmte.executeUpdate();
							if (kq < 1) {
								listError = listError + soCMND + " mã môn: " + maMon + ", ";
							}
							stmte.close();
						} else {
							listCT = listCT + soCMND + " mã môn: " + maMon + ", ";
						}
					} else {
						listChuaThi = listChuaThi + soCMND + " mã môn: " + maMon + ", ";
					}
				} else {
					listDangKy = listDangKy + soCMND + " mã môn: " + maMon + ", ";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Loi: " +e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			return "Lỗi nhập liệu, vui lòng thử lại.";
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			connectDB.closeConnection();
		}
		
		if (listError.length() > 2)
			listError = "Dữ liệu nhập chưa đúng: " + listError.substring(0, listError.length() - 2) + ". <br/>";
		if (listDangKy.length() > 2)
			listDangKy = "Chưa đăng ký thi: " + listDangKy.substring(0, listDangKy.length() - 2) + ". <br/>";
		if (listChuaThi.length() > 2)
			listChuaThi = "Chưa thi: " + listChuaThi.substring(0, listChuaThi.length() - 2) + ". <br/>";
		if (listCT.length() > 2)
			listCT = "Cụm thi chỉ có thể quản thí thi sinh đăng ký cụm thi đó: "
					+ listCT.substring(0, listCT.length() - 2) + ". ";
		listError = listError + listDangKy + listChuaThi + listCT;
		return listError;
	}

	/**
	 * Get list đăng ký dự thi by Cum Thi
	 * @param soCMND
	 * @return
	 */
	public ArrayList<ArrayList<String>> getListDKDTByCumThi(String soCMND) {
		ConnectDB connectDB = new ConnectDB();
		PreparedStatement stmt = null;
		String maCT;
		ArrayList<ArrayList<String>> list = null;
		String sqlSelect = "Select DangKyDuThi.SoCMND, ThiSinh.HoTen, SBD, IDPhong, MonThi.MaMon, TenMonThi from "
				+ "ThiSinh inner join DangKyDuThi on ThiSinh.SoCMND = DangKyDuThi.SoCMND "
				+ "inner join ChiTietDKDT on ChiTietDKDT.SoCMND = DangKyDuThi.SoCMND and ChiTietDKDT.NamTS = DangKyDuThi.NamTS "
				+ "inner join MonThi on ChiTietDKDT.MaMon = MonThi.MaMon "
				+ "inner join CumThi on CumThi.MaCT = DangKyDuThi.MaCT "
				+ "where DangKyDuThi.MaCT = ? and DangKyDuThi.NamTS = ? order by MaMon ASC, ThiSinh.SoCMND ASC";
		String sqlCumThi = "Select DonViThi from QuanLyCumThi where SoCMND=?";
		connectDB.openConnection();
		Connection con = connectDB.getConnect();

		//get cum thi
		try {
			int namTS = new NamTuyenSinhDAO().getNamTSHienTai();
			stmt = con.prepareStatement(sqlCumThi);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				maCT = rs.getString(1);
				stmt.close();
				
				stmt = con.prepareStatement(sqlSelect);
				stmt.setString(1, maCT);
				stmt.setInt(2, namTS);
				rs = stmt.executeQuery();
				
				list = new ArrayList<ArrayList<String>>();
				ArrayList<String> item;
				
				//read row
				while(rs.next()){
					item = new ArrayList<String>();
					item.add(rs.getString(1));
					item.add(rs.getString(2));
					item.add(rs.getString(3));
					item.add(rs.getString(4));
					item.add(rs.getString(5));
					item.add(rs.getString(6));
					list.add(item);
				}
				stmt.close();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			connectDB.closeConnection();
		}
		return list;
	}

}
