package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.bean.GiangVien;
import model.dao.khoi.ConnectDB;

public class GiangVienDAO {

	public GiangVien getInfo(String soCMND) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,MaTruong,HinhAnh,Logined,TrangThai from GiangVien where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();
			GiangVien gv = null;
			if (rs.next()) {
				gv = new GiangVien();
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
				gv.setMaTruong(rs.getString(11));
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
		String sql = "update GiangVien set MatKhau=?, Logined=? where SoCMND=?";
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

	public boolean insertGiangVien(GiangVien gv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select SoCMND from GiangVien where SoCMND=?";
		String sql = "insert into GiangVien(SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,"
				+ "SoDT,Email,MaTruong,HinhAnh) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sqlCheck);
			stmt.setString(1, gv.getSoCMND());
			ResultSet rs = stmt.executeQuery();
			int check = 0;
			if (!rs.next()) {
				stmt = con.getConnect().prepareStatement(sql);
				stmt.setString(1, gv.getSoCMND());
				stmt.setString(2, gv.getHoTen());
				stmt.setString(3, gv.getMatKhau());
				stmt.setDate(4, gv.getNgaySinh());
				stmt.setString(5, gv.getMaXa());
				stmt.setString(6, gv.getMaHuyen());
				stmt.setString(7, gv.getMaTinh());
				stmt.setBoolean(8, gv.isGioiTinh());
				stmt.setString(9, gv.getSoDT());
				stmt.setString(10, gv.getEmail());
				stmt.setString(11, gv.getMaTruong());
				stmt.setString(12, gv.getHinhAnh());
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

	public Map<String, Object> getInfoHienThi(String soCMND) {
		HashMap<String, Object> hm;
		PreparedStatement stmt = null;
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,GiangVien.MaXa,GiangVien.MaHuyen,GiangVien.MaTinh,GioiTinh,SoDT,"
				+ "Email,GiangVien.MaTruong,HinhAnh, TenXa,TenHuyen,TenTinh,TenTruong "
				+ "from GiangVien left join Xa_Phuong on GiangVien.MaXa=Xa_Phuong.MaXa and Xa_Phuong.MaHuyen = GiangVien.MaHuyen and Xa_Phuong.MaTinh = GiangVien.MaTinh "
				+ "inner join Huyen_Quan on Huyen_Quan.MaHuyen = GiangVien.MaHuyen and GiangVien.MaTinh=Huyen_Quan.MaTinh "
				+ "inner join Tinh_ThanhPho on Tinh_ThanhPho.MaTinh= GiangVien.MaTinh "
				+ "inner join TruongDH_CD on TruongDH_CD.MaTruong = GiangVien.MaTruong where SoCMND=?";

		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();

			hm = new HashMap<String, Object>();
			if (rs.next()) {
				GiangVien gv = new GiangVien();
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
				gv.setMaTruong(rs.getString(11));
				gv.setHinhAnh(rs.getString(12));

				hm.put("giangVien", gv);
				hm.put("tenXa", rs.getString(13));
				hm.put("tenHuyen", rs.getString(14));
				hm.put("tenTinh", rs.getString(15));
				hm.put("tenTruongDHCD", rs.getString(16));
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
