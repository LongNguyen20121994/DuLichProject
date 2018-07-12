package model.dao.longnt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.QuanLyCumThi;
import model.dao.khoi.ConnectDB;

public class QuanLyCumThiDAO {

	public QuanLyCumThi getInfo(String soCMND) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,DonViThi,HinhAnh,Logined,TrangThai from QuanLyCumThi where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();
			QuanLyCumThi gv = null;
			if (rs.next()) {
				gv = new QuanLyCumThi();
				gv.setSoCMND(rs.getString(1));
				gv.setHoTen(rs.getString(2));
				gv.setMatKhau(rs.getString(3));
				gv.setNgaySinh(rs.getDate(4));
				gv.setMaXa(rs.getString(5));
				gv.setMaHuyen(rs.getString(6));
				gv.setMaTinh(rs.getString(7));
				gv.setGioiTinh(rs.getBoolean(8));
				gv.setSoDT(rs.getString(9));
				gv.setEmail(rs.getString(10));
				gv.setDonViThi(rs.getString(11));
				gv.setHinhAnh(rs.getString(12));
				gv.setLogined(rs.getBoolean(13));
				gv.setTrangThai(rs.getBoolean(14));
			}
			stmt.close();
			return gv;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update QuanLyCumThi set MatKhau=?, Logined=? where SoCMND=?";
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(new QuanLyCumThiDAO().getInfo("123456789"));
	}

	public HashMap<String, Object> getInfoHienThi(String soCMND) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,QuanLyCumThi.MaXa,QuanLyCumThi.MaHuyen,QuanLyCumThi.MaTinh,GioiTinh,SoDT,"
				+ "Email,QuanLyCumThi.DonViThi,HinhAnh,TenXa,TenHuyen,TenTinh,TenCumThi "
				+ "from QuanLyCumThi left join Xa_Phuong on QuanLyCumThi.MaXa=Xa_Phuong.MaXa and Xa_Phuong.MaHuyen = QuanLyCumThi.MaHuyen and Xa_Phuong.MaTinh = QuanLyCumThi.MaTinh "
				+ "inner join Huyen_Quan on Huyen_Quan.MaHuyen = QuanLyCumThi.MaHuyen and QuanLyCumThi.MaTinh=Huyen_Quan.MaTinh "
				+ "inner join Tinh_ThanhPho on Tinh_ThanhPho.MaTinh= QuanLyCumThi.MaTinh "
				+ "inner join CumThi on CumThi.MaCT = QuanLyCumThi.DonViThi where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				QuanLyCumThi qlct = new QuanLyCumThi();

				qlct.setSoCMND(rs.getString(1));
				qlct.setHoTen(rs.getString(2));
				qlct.setMatKhau(rs.getString(3));
				qlct.setNgaySinh(rs.getDate(4));
				qlct.setMaXa(rs.getString(5));
				qlct.setMaHuyen(rs.getString(6));
				qlct.setMaTinh(rs.getString(7));
				qlct.setGioiTinh(rs.getBoolean(8));
				qlct.setSoDT(rs.getString(9));
				qlct.setEmail(rs.getString(10));
				qlct.setDonViThi(rs.getString(11));
				qlct.setHinhAnh(rs.getString(12));

				hm.put("qlct", qlct);
				hm.put("tenXa", rs.getString(13));
				hm.put("tenHuyen", rs.getString(14));
				hm.put("tenTinh", rs.getString(15));
				hm.put("tenCumThi", rs.getString(16));
			}
			return hm;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean insertQuanLyCumThi(QuanLyCumThi qlct) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select SoCMND from QuanLyCumThi where SoCMND=?";
		String sql = "insert into QuanLyCumThi(SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,DonViThi,HinhAnh) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sqlCheck);
			stmt.setString(1, qlct.getSoCMND());
			ResultSet rs = stmt.executeQuery();
			int check = 0;
			if (!rs.next()) {
				stmt = con.getConnect().prepareStatement(sql);
				stmt.setString(1, qlct.getSoCMND());
				stmt.setString(2, qlct.getHoTen());
				stmt.setString(3, qlct.getMatKhau());
				stmt.setDate(4, qlct.getNgaySinh());
				stmt.setString(5, qlct.getMaXa());
				stmt.setString(6, qlct.getMaHuyen());
				stmt.setString(7, qlct.getMaTinh());
				stmt.setBoolean(8, qlct.isGioiTinh());
				stmt.setString(9, qlct.getSoDT());
				stmt.setString(10, qlct.getEmail());
				stmt.setString(11, qlct.getDonViThi());
				stmt.setString(12, qlct.getHinhAnh());
				check = stmt.executeUpdate();
			}
			stmt.close();
			if (check > 0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}

	public boolean updateInfo(QuanLyCumThi qlct) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update QuanLyCumThi set HoTen=?,NgaySinh=?,MaXa=?,MaHuyen=?,MaTinh=?,GioiTinh=?,SoDT=?,Email=?,"
				+ "DonViThi=?,HinhAnh=? where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(11, qlct.getSoCMND());
			stmt.setString(1, qlct.getHoTen());
			stmt.setDate(2, qlct.getNgaySinh());
			stmt.setString(3, qlct.getMaXa());
			stmt.setString(4, qlct.getMaHuyen());
			stmt.setString(5, qlct.getMaTinh());
			stmt.setBoolean(6, qlct.isGioiTinh());
			stmt.setString(7, qlct.getSoDT());
			stmt.setString(8, qlct.getEmail());
			stmt.setString(9, qlct.getDonViThi());
			stmt.setString(10, qlct.getHinhAnh());

			if (stmt.executeUpdate() == 1)
				return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public ArrayList<QuanLyCumThi> getInfoQuanLyCumThhi(String soCMND) {
		ArrayList<QuanLyCumThi> list = new ArrayList<QuanLyCumThi>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND, HoTen,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,DonViThi from QuanLyCumThi";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			// stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();
			QuanLyCumThi qlct;
			while (rs.next()) {
				qlct = new QuanLyCumThi();
				qlct.setSoCMND(rs.getString(1));
				qlct.setHoTen(rs.getString(2));
				qlct.setNgaySinh(rs.getDate(3));
				qlct.setMaXa(rs.getString(4));
				qlct.setMaHuyen(rs.getString(5));
				qlct.setMaTinh(rs.getString(6));
				qlct.setGioiTinh(rs.getBoolean(7));
				qlct.setSoDT(rs.getString(8));
				qlct.setEmail(rs.getString(9));
				qlct.setDonViThi(rs.getString(10));
				list.add(qlct);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
		return list;
	}

	public String deleteListQuanLyCumThi(List<String> listSoCMND) {
		ConnectDB connectDB = new ConnectDB();
		Connection con;
		String listReturn = "";
		String sql = "delete from QuanLyCumThi where SoCMND = ?";
		PreparedStatement stmt = null;
		Savepoint sp = null;

		connectDB.openConnection();
		con = connectDB.getConnect();

		for (String soCMND : listSoCMND) {
			try {
				System.out.println(soCMND);
				con.setAutoCommit(false);
				sp = con.setSavepoint("sp1");

				stmt = connectDB.getConnect().prepareStatement(sql);
				stmt.setString(1, soCMND);
				stmt.executeUpdate();
				stmt.close();

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
			} finally {
				try {
					con.setAutoCommit(true);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		connectDB.closeConnection();

		if (listReturn.length() > 2)
			listReturn = "Không thể xóa quản lý cụm thi: "
					+ listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}
}