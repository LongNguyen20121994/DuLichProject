package model.dao.quang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.ThiSinh;
import model.bean.ThiSinhTHPT;
import model.dao.khoi.ConnectDB;

public class ThiSinhDAO {

	public ThiSinh getInfo(String soCMND) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,NgaySinh,GioiTinh,DiaChi,MatKhau,SoDT,Email,MaXa,"
				+ "MaHuyen,MaTinh,DanToc,DoiTuongUT,NamTN,NoiSinh,HinhAnh,Logined,TrangThai "
				+ "from ThiSinh where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();
			ThiSinh ts = null;
			if (rs.next()) {
				ts = new ThiSinh();
				ts.setSoCMND(rs.getString(1));
				ts.setHoTen(rs.getString(2));
				ts.setNgaySinh(rs.getDate(3));
				ts.setGioiTinh(rs.getBoolean(4));
				ts.setDiaChi(rs.getString(5));
				ts.setMatKhau(rs.getString(6));
				ts.setSoDT(rs.getString(7));
				ts.setEmail(rs.getString(8));
				ts.setMaXa(rs.getString(9));
				ts.setMaHuyen(rs.getString(10));
				ts.setMaTinh(rs.getString(11));
				ts.setDanToc(rs.getString(12));
				ts.setDoiTuongUT(rs.getString(13));
				ts.setNamTN(rs.getInt(14));
				ts.setNoiSinh(rs.getString(15));
				ts.setHinhAnh(rs.getString(16));
				ts.setLogined(rs.getBoolean(17));
				ts.setTrangThai(rs.getBoolean(18));
			}
			stmt.close();
			return ts;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update ThiSinh set MatKhau=?, Logined=? where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, matKhau);
			stmt.setBoolean(2, true);
			stmt.setString(3, soCMND);
			int rs = stmt.executeUpdate();
			stmt.close();
			if (rs > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
		return false;
	}

	public String themListDangKyDT(ArrayList<ThiSinh> list) {
		ConnectDB connectDB = new ConnectDB();
		String listExist = "";
		String listReturn = "";
		PreparedStatement stmtc = null;
		PreparedStatement stmte = null;
		String sqlInsert = "insert into ThiSinh(SoCMND, HoTen, NgaySinh, MaXa, MaHuyen, MaTinh, GioiTinh, SoDT,"
				+ " Email, HinhAnh, DoiTuongUT, DiaChi, NamTN, DanToc, NoiSinh, MatKhau, NguoiDK,TrangThai) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlCheck = "select SoCMND from ThiSinh where SoCMND=?";
		connectDB.openConnection();
		
		try {
			for (ThiSinh tr : list) {
				stmtc = connectDB.getConnect().prepareStatement(sqlCheck);
				stmtc.setString(1, tr.getSoCMND());
				if (stmtc.executeQuery().next()) {
					listExist = listExist + tr.getSoCMND() + ", ";
				} else {
					stmte = connectDB.getConnect().prepareStatement(sqlInsert);
					stmte.setString(1, tr.getSoCMND());
					stmte.setString(2, tr.getHoTen());
					stmte.setDate(3, tr.getNgaySinh());
					stmte.setString(4, tr.getMaXa());
					stmte.setString(5, tr.getMaHuyen());
					stmte.setString(6, tr.getMaTinh());
					stmte.setBoolean(7, tr.isGioiTinh());
					stmte.setString(8, tr.getSoDT());
					stmte.setString(9, tr.getEmail());
					stmte.setString(10, tr.getHinhAnh());
					stmte.setString(11, tr.getDoiTuongUT());
					stmte.setString(12, tr.getDiaChi());
					stmte.setInt(13, tr.getNamTN());
					stmte.setString(14, tr.getDanToc());
					stmte.setString(15, tr.getNoiSinh());
					stmte.setString(16, tr.getMatKhau());
					stmte.setString(17, tr.getNguoiDK());
					stmte.setBoolean(18, tr.isTrangThai());

					if (stmte.executeUpdate() < 1)
						listReturn = listReturn + tr.getSoCMND() + ", ";
					stmte.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			listExist += " ";
		} finally {
			connectDB.closeConnection();
		}
		if (listExist.length() > 2)
			listExist = "Đã tồn tại thí sinh: " + listExist.substring(0, listExist.length() - 2) + ". ";
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listExist + listReturn;
	}

	public boolean isSoCMND(String soCMND) {
		ConnectDB connectDB = new ConnectDB();
		String sql = "Select SoCMND from ThiSinh where SoCMND=?";
		
		try {
			connectDB.openConnection();
			PreparedStatement stmt = connectDB.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			if ((stmt.executeQuery()).next())
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public boolean insertThiSinh(ThiSinh ts) {
		ConnectDB connectDB = new ConnectDB();
		System.out.println(ts);
		String sql = "insert into ThiSinh(SoCMND, HoTen, NgaySinh, MaXa, MaHuyen, MaTinh, GioiTinh, SoDT,"
				+ " Email, HinhAnh, DoiTuongUT, DiaChi, NamTN, DanToc, NoiSinh, MatKhau, NguoiDK, TrangThai) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			connectDB.openConnection();
			PreparedStatement stmt = connectDB.getConnect().prepareStatement(sql);
			stmt.setString(1, ts.getSoCMND());
			stmt.setString(2, ts.getHoTen());
			stmt.setDate(3, ts.getNgaySinh());
			stmt.setString(4, ts.getMaXa());
			stmt.setString(5, ts.getMaHuyen());
			stmt.setString(6, ts.getMaTinh());
			stmt.setBoolean(7, ts.isGioiTinh());
			stmt.setString(8, ts.getSoDT());
			stmt.setString(9, ts.getEmail());
			stmt.setString(10, ts.getHinhAnh());
			stmt.setString(11, ts.getDoiTuongUT());
			stmt.setString(12, ts.getDiaChi());
			stmt.setInt(13, ts.getNamTN());
			stmt.setString(14, ts.getDanToc());
			stmt.setString(15, ts.getNoiSinh());
			stmt.setString(16, ts.getMatKhau());
			stmt.setString(17, ts.getNguoiDK());
			stmt.setBoolean(18, ts.isTrangThai());

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

	public ArrayList<ThiSinh> getListInfoPassWord(String soCMNDGiangVien) {
		ArrayList<ThiSinh> list;
		ConnectDB con;
		PreparedStatement stmt = null;
		con = new ConnectDB();
		con.openConnection();
		String sql = "select ThiSinh.SoCMND, HoTen, NgaySinh, GioiTinh, SoDT,  Email, DiaChi, NoiSinh, NamTN, MatKhau"
				+ " from ThiSinh left join DangKyDuThi on ThiSinh.SoCMND = DangKyDuThi.SoCMND "
				+ " where NguoiDK=? and logined = 'false' and SBD is NULL and (NamTS = ? or NamTS is Null)";
		
		try {
			int namTS = new NamTuyenSinhDAO().getNamTSHienTai();
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMNDGiangVien);
			stmt.setInt(2, namTS);
			ResultSet rs = stmt.executeQuery();
			ThiSinh ts;
			
			list = new ArrayList<ThiSinh>();
			while (rs.next()) {
				ts = new ThiSinh();
				ts.setSoCMND(rs.getString(1));
				ts.setHoTen(rs.getString(2));
				ts.setNgaySinh(rs.getDate(3));
				ts.setGioiTinh(rs.getBoolean(4));
				ts.setSoDT(rs.getString(5));
				ts.setEmail(rs.getString(6));
				ts.setDiaChi(rs.getString(7));
				ts.setNoiSinh(rs.getString(8));
				ts.setNamTN(rs.getInt(9));
				ts.setMatKhau(rs.getString(10));
				list.add(ts);
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

	public Map<String, Object> getInfoHienThi(String soCMND) {
		HashMap<String, Object> result;
		PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "Select HoTen,NgaySinh,GioiTinh,DiaChi,SoDT,Email,ThiSinh.MaXa,ThiSinh.MaHuyen,ThiSinh.MaTinh,"
				+ "ThiSinh.DanToc,ThiSinh.DoiTuongUT,NamTN,NoiSinh,HinhAnh,Xa_Phuong.TenXa,Huyen_Quan.TenHuyen,"
				+ "Tinh_ThanhPho.TenTinh,DanToc.TenDanToc,DoiTuongUT.TenDT from ThiSinh "
				+ "left join Xa_Phuong on ThiSinh.MaXa=Xa_Phuong.MaXa and Xa_Phuong.MaHuyen = ThiSinh.MaHuyen and Xa_Phuong.MaTinh = ThiSinh.MaTinh "
				+ "inner join Huyen_Quan on Huyen_Quan.MaHuyen = ThiSinh.MaHuyen and ThiSinh.MaTinh=Huyen_Quan.MaTinh "
				+ "inner join Tinh_ThanhPho  on Tinh_ThanhPho.MaTinh= ThiSinh.MaTinh "
				+ "inner join DanToc on DanToc.MaDT = ThiSinh.DanToc "
				+ "left join DoiTuongUT on DoiTuongUT.MaDT = ThiSinh.DoiTuongUT where SoCMND=?";

		String sqlTHPT = "Select TruongTHPT.MaTruong,TruongTHPT.MaTinh,TenTruong,TenTinh,Lop,TenKhuVuc,KhuVucUT.MaKV "
				+ "from ThiSinh left join ThiSinh_THPT on ThiSinh.SoCMND = ThiSinh_THPT.SoCMND "
				+ "inner join TruongTHPT on ThiSinh_THPT.MaTruongTHPT = TruongTHPT.MaTruong "
				+ "inner join Tinh_ThanhPho on Tinh_ThanhPho.MaTinh = TruongTHPT.MaTinh and TruongTHPT.MaTinh = ThiSinh_THPT.MaTinhTHPT "
				+ " inner join KhuVucUT on KhuVucUT.MaKV = TruongTHPT.KhuVucUT where ThiSinh.SoCMND = ?";

		try {

			// get info Thi Sinh
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();

			result = new HashMap<String, Object>();
			if (rs.next()) {
				String tenTinh = null;
				String tenHuyen = null;
				String tenXa = null;
				String tenDanToc = null;
				String tenDoiTuongUT = null;
				ThiSinh ts = new ThiSinh();

				ts.setSoCMND(soCMND);
				ts.setHoTen(rs.getString(1));
				ts.setNgaySinh(rs.getDate(2));
				ts.setGioiTinh(rs.getBoolean(3));
				ts.setDiaChi(rs.getString(4));
				ts.setSoDT(rs.getString(5));
				ts.setEmail(rs.getString(6));

				ts.setMaXa(rs.getString(7));
				ts.setMaHuyen(rs.getString(8));
				ts.setMaTinh(rs.getString(9));

				ts.setDanToc(rs.getString(10));
				ts.setDoiTuongUT(rs.getString(11));
				ts.setNamTN(rs.getInt(12));
				ts.setNoiSinh(rs.getString(13));
				ts.setHinhAnh(rs.getString(14));

				tenXa = rs.getString(15);
				tenHuyen = rs.getString(16);
				tenTinh = rs.getString(17);
				tenDanToc = rs.getString(18);
				tenDoiTuongUT = rs.getString(19);

				//System.out.println(ts);
				result.put("thiSinh", ts);
				result.put("tenXa", tenXa);
				result.put("tenHuyen", tenHuyen);
				result.put("tenTinh", tenTinh);
				result.put("tenDanToc", tenDanToc);
				result.put("tenDoiTuongUT", tenDoiTuongUT);

			}

			// Get info ThiSinh - TruongTHP
			stmt = con.getConnect().prepareStatement(sqlTHPT);
			stmt.setString(1, soCMND);
			rs = stmt.executeQuery();
			int lop = 0;
			ArrayList<Object> list = null;

			while (rs.next()) {
				list = new ArrayList<Object>();
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getString(4));
				list.add(rs.getString(6));
				list.add(rs.getString(7));
				lop = rs.getInt(5);
				list.add(lop);

				if (lop == 10)
					result.put("lop10", list);
				if (lop == 11)
					result.put("lop11", list);
				if (lop == 12)
					result.put("lop12", list);
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

	public boolean updateInfo(ThiSinh ts) {
		ConnectDB connectDB = new ConnectDB();
		//System.out.println(ts);
		String sql = "update ThiSinh set HoTen=?, NgaySinh=?, MaXa=?, MaHuyen=?, MaTinh=?, GioiTinh=?, SoDT=?,"
				+ " Email=?, HinhAnh=?, DoiTuongUT=?, DiaChi=?, NamTN=?, DanToc=?, NoiSinh=? where SoCMND =?";
		
		try {
			connectDB.openConnection();
			PreparedStatement stmt = connectDB.getConnect().prepareStatement(sql);
			stmt.setString(15, ts.getSoCMND());
			stmt.setString(1, ts.getHoTen());
			stmt.setDate(2, ts.getNgaySinh());
			stmt.setString(3, ts.getMaXa());
			stmt.setString(4, ts.getMaHuyen());
			stmt.setString(5, ts.getMaTinh());
			stmt.setBoolean(6, ts.isGioiTinh());
			stmt.setString(7, ts.getSoDT());
			stmt.setString(8, ts.getEmail());
			stmt.setString(9, ts.getHinhAnh());
			stmt.setString(10, ts.getDoiTuongUT());
			stmt.setString(11, ts.getDiaChi());
			stmt.setInt(12, ts.getNamTN());
			stmt.setString(13, ts.getDanToc());
			stmt.setString(14, ts.getNoiSinh());

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

	public String deleteListThiSinhDKDTGV(List<String> listSoCMND, int namTS) {
		ConnectDB connectDB = new ConnectDB();
		PreparedStatement stmt = null;
		Savepoint sp = null;
		Connection con;
		String listReturn = "";
		String sqlTSTHPT = "delete from ThiSinh_THPT where SoCMND = ?";
		String sqlChiTietDKDT = "delete from ChiTietDKDT where SoCMND=? and NamTS=?";
		String sqlDKDT = "delete from DangKyDuThi where SoCMND=? and NamTS=?";
		String sqlThiSinh = "delete from ThiSinh where SoCMND=?";
		connectDB.openConnection();
		con = connectDB.getConnect();

		for (String soCMND : listSoCMND) {
			try {
				//System.out.println(soCMND);
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
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
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

	public String themListDangKyDT(ArrayList<ThiSinh> listTS, HashMap<String, ArrayList<ThiSinhTHPT>> listThiSinhTHPT) {
		//System.out.println("dao");
		ConnectDB connectDB = new ConnectDB();
		String listExist = "";
		String listReturn = "";
		String tsTHPT = "";

		String sqlInsert = "insert into ThiSinh(SoCMND, HoTen, NgaySinh, MaXa, MaHuyen, MaTinh, GioiTinh, SoDT,"
				+ " Email, HinhAnh, DoiTuongUT, DiaChi, NamTN, DanToc, NoiSinh, MatKhau, NguoiDK,TrangThai) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlCheck = "select SoCMND from ThiSinh where SoCMND=?";
		String sqlTHPT = "insert into ThiSinh_THPT(SoCMND, MaTinhTHPT, MaTruongTHPT, Lop) values (?,?,?,?)";

		PreparedStatement stmtc = null;
		PreparedStatement stmte = null;
		connectDB.openConnection();
		Connection con = connectDB.getConnect();

		Savepoint sp = null;

		for (ThiSinh ts : listTS) {
			try {
				con.setAutoCommit(false);
				sp = con.setSavepoint("sp1");

				// them thi sinh
				stmtc = con.prepareStatement(sqlCheck);
				stmtc.setString(1, ts.getSoCMND());
				if (stmtc.executeQuery().next()) {
					listExist = listExist + ts.getSoCMND() + ", ";
				} else {
					stmte = connectDB.getConnect().prepareStatement(sqlInsert);
					stmte.setString(1, ts.getSoCMND());
					stmte.setString(2, ts.getHoTen());
					stmte.setDate(3, ts.getNgaySinh());
					stmte.setString(4, ts.getMaXa());
					stmte.setString(5, ts.getMaHuyen());
					stmte.setString(6, ts.getMaTinh());
					stmte.setBoolean(7, ts.isGioiTinh());
					stmte.setString(8, ts.getSoDT());
					stmte.setString(9, ts.getEmail());
					stmte.setString(10, ts.getHinhAnh());
					stmte.setString(11, ts.getDoiTuongUT());
					stmte.setString(12, ts.getDiaChi());
					stmte.setInt(13, ts.getNamTN());
					stmte.setString(14, ts.getDanToc());
					stmte.setString(15, ts.getNoiSinh());
					stmte.setString(16, ts.getMatKhau());
					stmte.setString(17, ts.getNguoiDK());
					stmte.setBoolean(18, ts.isTrangThai());

					if (stmte.executeUpdate() < 1) {
						listReturn = listReturn + ts.getSoCMND() + ", ";
					} else {

						// Them Thi sinh THPT
						for (ThiSinhTHPT tr : listThiSinhTHPT.get(ts.getSoCMND())) {
							stmte = connectDB.getConnect().prepareStatement(sqlTHPT);
							stmte.setString(1, tr.getSoCMND());
							stmte.setString(2, tr.getMaTinhTHPT());
							stmte.setString(3, tr.getMaTruong());
							stmte.setInt(4, tr.getLop());

							if (stmte.executeUpdate() < 1)
								tsTHPT = "fail";
						}

						// check transaction
						if ("".equals(tsTHPT)) {
							stmte.close();
							con.commit();
						} else {
							listReturn += ts.getSoCMND() + ", ";
							stmte.close();
							con.rollback(sp);
						}
					}
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				listReturn += ts.getSoCMND() + ", ";
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

		if (listExist.length() > 2)
			listExist = "Đã tồn tại thí sinh: " + listExist.substring(0, listExist.length() - 2) + ". ";
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listExist + listReturn;
	}

	public String updateListDangKyDT(ArrayList<ThiSinh> listTS,
			HashMap<String, ArrayList<ThiSinhTHPT>> listThiSinhTHPT) {

		//System.out.println("dao");
		ConnectDB connectDB = new ConnectDB();
		String listInsert = "";
		String tsTHPT = "";

		String sqlCheck = "select SoCMND from ThiSinh where SoCMND=? and NguoiDK=?";
		String sqlInsert = "insert into ThiSinh(SoCMND, HoTen, NgaySinh, MaXa, MaHuyen, MaTinh, GioiTinh, SoDT,"
				+ " Email, HinhAnh, DoiTuongUT, DiaChi, NamTN, DanToc, NoiSinh, MatKhau, NguoiDK,TrangThai) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sqlUpdate = "update ThiSinh set HoTen=?, NgaySinh=?, MaXa=?, MaHuyen=?, MaTinh=?, GioiTinh=?, SoDT=?,"
				+ " Email=?, HinhAnh=?, DoiTuongUT=?, DiaChi=?, NamTN=?, DanToc=?, NoiSinh=?,NguoiDK=?,TrangThai=? "
				+ "where SoCMND=?";

		String sqlTHPT = "insert into ThiSinh_THPT(SoCMND, MaTinhTHPT, MaTruongTHPT, Lop) values (?,?,?,?)";
		String sqlUpdateTHPT = "update ThiSinh_THPT set  MaTinhTHPT=?, MaTruongTHPT=? where SoCMND=? and Lop=?";

		PreparedStatement stmtc = null;
		PreparedStatement stmte = null;
		connectDB.openConnection();
		Connection con = connectDB.getConnect();

		Savepoint sp = null;

		for (ThiSinh ts : listTS) {
			try {
				con.setAutoCommit(false);
				sp = con.setSavepoint("sp1");

				//Check thi sinh - người đăng ký
				stmtc = con.prepareStatement(sqlCheck);
				stmtc.setString(1, ts.getSoCMND());
				stmtc.setString(2, ts.getNguoiDK());

				// cap nhat thi sinh
				if (stmtc.executeQuery().next()) {
					stmte = connectDB.getConnect().prepareStatement(sqlUpdate);
					stmte.setString(1, ts.getHoTen());
					stmte.setDate(2, ts.getNgaySinh());
					stmte.setString(3, ts.getMaXa());
					stmte.setString(4, ts.getMaHuyen());
					stmte.setString(5, ts.getMaTinh());
					stmte.setBoolean(6, ts.isGioiTinh());
					stmte.setString(7, ts.getSoDT());
					stmte.setString(8, ts.getEmail());
					stmte.setString(9, ts.getHinhAnh());
					stmte.setString(10, ts.getDoiTuongUT());
					stmte.setString(11, ts.getDiaChi());
					stmte.setInt(12, ts.getNamTN());
					stmte.setString(13, ts.getDanToc());
					stmte.setString(14, ts.getNoiSinh());
					stmte.setString(15, ts.getNguoiDK());
					stmte.setBoolean(16, ts.isTrangThai());
					stmte.setString(17, ts.getSoCMND());

					if (stmte.executeUpdate() < 1) {
						listInsert = listInsert + ts.getSoCMND() + ", ";
					} else {

						// cap nhat Thi sinh THPT
						for (ThiSinhTHPT tr : listThiSinhTHPT.get(ts.getSoCMND())) {
							stmte = connectDB.getConnect().prepareStatement(sqlUpdateTHPT);

							stmte.setString(1, tr.getMaTinhTHPT());
							stmte.setString(2, tr.getMaTruong());
							stmte.setString(3, tr.getSoCMND());
							stmte.setInt(4, tr.getLop());

							if (stmte.executeUpdate() < 1)
								tsTHPT = "fail";
						}

						// check transaction
						if ("".equals(tsTHPT)) {
							stmte.close();
							con.commit();
						} else {
							listInsert += ts.getSoCMND() + ", ";
							stmte.close();
							con.rollback(sp);
						}
					}

					// them thi sinh
				} else {
					stmte = connectDB.getConnect().prepareStatement(sqlInsert);
					stmte.setString(1, ts.getSoCMND());
					stmte.setString(2, ts.getHoTen());
					stmte.setDate(3, ts.getNgaySinh());
					stmte.setString(4, ts.getMaXa());
					stmte.setString(5, ts.getMaHuyen());
					stmte.setString(6, ts.getMaTinh());
					stmte.setBoolean(7, ts.isGioiTinh());
					stmte.setString(8, ts.getSoDT());
					stmte.setString(9, ts.getEmail());
					stmte.setString(10, ts.getHinhAnh());
					stmte.setString(11, ts.getDoiTuongUT());
					stmte.setString(12, ts.getDiaChi());
					stmte.setInt(13, ts.getNamTN());
					stmte.setString(14, ts.getDanToc());
					stmte.setString(15, ts.getNoiSinh());
					stmte.setString(16, ts.getMatKhau());
					stmte.setString(17, ts.getNguoiDK());
					stmte.setBoolean(18, ts.isTrangThai());

					if (stmte.executeUpdate() < 1) {
						listInsert = listInsert + ts.getSoCMND() + ", ";
					} else {

						// Them Thi sinh THPT
						for (ThiSinhTHPT tr : listThiSinhTHPT.get(ts.getSoCMND())) {
							stmte = connectDB.getConnect().prepareStatement(sqlTHPT);
							stmte.setString(1, tr.getSoCMND());
							stmte.setString(2, tr.getMaTinhTHPT());
							stmte.setString(3, tr.getMaTruong());
							stmte.setInt(4, tr.getLop());

							if (stmte.executeUpdate() < 1)
								tsTHPT = "fail";
						}

						// check transaction
						if ("".equals(tsTHPT)) {
							stmte.close();
							con.commit();
						} else {
							listInsert += ts.getSoCMND() + ", ";
							stmte.close();
							con.rollback(sp);
						}
					}
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
				listInsert += ts.getSoCMND() + ", ";
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
		//System.out.println("in: " + listInsert);
		if (listInsert.length() > 2)
			listInsert = "Dữ liệu nhập chưa đúng: " + listInsert.substring(0, listInsert.length() - 2) + ". ";
		//System.out.println("in: " + listInsert);
		return listInsert;
	}

}
