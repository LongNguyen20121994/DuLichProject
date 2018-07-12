package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.bean.QuanTriVien;
import model.dao.khoi.ConnectDB;

public class QuanTriVienDAO {

	public QuanTriVien getInfo(String soCMND) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,HinhAnh,Logined,TrangThai from QuanTriVien where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();
			QuanTriVien gv = null;
			if (rs.next()) {
				gv = new QuanTriVien();
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
				gv.setHinhAnh(rs.getString(11));
				gv.setLogined(rs.getBoolean(12));
				gv.setTrangThai(rs.getBoolean(13));
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
		String sql = "update QuanTriVien set MatKhau=?, Logined=? where SoCMND=?";
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

	public boolean insertQuanTriVien(QuanTriVien qtv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select SoCMND from QuanTriVien where SoCMND=?";
		String sql = "insert into QuanTriVien(SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,HinhAnh) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sqlCheck);
			stmt.setString(1, qtv.getSoCMND());
			ResultSet rs = stmt.executeQuery();
			int check = 0;
			if (!rs.next()) {
				stmt = con.getConnect().prepareStatement(sql);
				stmt.setString(1, qtv.getSoCMND());
				stmt.setString(2, qtv.getHoTen());
				stmt.setString(3, qtv.getMatKhau());
				stmt.setDate(4, qtv.getNgaySinh());
				stmt.setString(5, qtv.getMaXa());
				stmt.setString(6, qtv.getMaHuyen());
				stmt.setString(7, qtv.getMaTinh());
				stmt.setBoolean(8, qtv.isGioiTinh());
				stmt.setString(9, qtv.getSoDT());
				stmt.setString(10, qtv.getEmail());
				stmt.setString(11, qtv.getHinhAnh());
				check = stmt.executeUpdate();
			}
			stmt.close();
			if (check > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}

	public Map<String, Object> getInfoHienThi(String soCMND) {
		HashMap<String, Object> hm;
		PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = " select SoCMND,HoTen,MatKhau,NgaySinh,QuanTriVien.MaXa,QuanTriVien.MaHuyen,QuanTriVien.MaTinh,GioiTinh,SoDT, "
				+ "Email,HinhAnh,TenXa,TenHuyen,TenTinh from QuanTriVien left join Xa_Phuong on QuanTriVien.MaXa=Xa_Phuong.MaXa and"
				+ " Xa_Phuong.MaHuyen = QuanTriVien.MaHuyen and Xa_Phuong.MaTinh = QuanTriVien.MaTinh "
				+ "inner join Huyen_Quan on Huyen_Quan.MaHuyen = QuanTriVien.MaHuyen and QuanTriVien.MaTinh=Huyen_Quan.MaTinh "
				+ "inner join Tinh_ThanhPho on Tinh_ThanhPho.MaTinh= QuanTriVien.MaTinh where SoCMND=?";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();

			hm = new HashMap<String, Object>();
			if (rs.next()) {
				QuanTriVien qtv = new QuanTriVien();

				qtv.setSoCMND(rs.getString(1));
				qtv.setHoTen(rs.getString(2));
				qtv.setMatKhau(rs.getString(3));
				qtv.setNgaySinh(rs.getDate(4));
				qtv.setMaXa(rs.getString(5));
				qtv.setMaHuyen(rs.getString(6));
				qtv.setMaTinh(rs.getString(7));
				qtv.setGioiTinh(rs.getBoolean(8));
				qtv.setSoDT(rs.getString(9));
				qtv.setEmail(rs.getString(10));
				qtv.setHinhAnh(rs.getString(11));

				hm.put("qtv", qtv);
				hm.put("tenXa", rs.getString(12));
				hm.put("tenHuyen", rs.getString(13));
				hm.put("tenTinh", rs.getString(14));
			}
			return hm;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}
}
