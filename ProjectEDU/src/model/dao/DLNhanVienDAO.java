package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Library;
import common.SendEmail;
import model.bean.DLNhanVien;
import model.dao.khoi.ConnectDB;

public class DLNhanVienDAO {
	public boolean deleteListNhanVien(List<String> listMaNV) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaNV.size() - 1; i++) {
			str += listMaNV.get(i) + "','";
		}
		str += listMaNV.get(listMaNV.size() - 1) + "')";
		String sql = "delete from NhanVien where SoCMND in " + str;
		PreparedStatement stmt = null;
		connectDB.openConnection();
		try {
			stmt = connectDB.getConnect().prepareStatement(sql);
			if (stmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectDB.closeConnection();
		}
		return false;
	}
	public List<DLNhanVien> getAll() {
		List<DLNhanVien> list = new ArrayList<DLNhanVien>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,GioiTinh,SoDT,Email,HinhAnh,isAdmin from NhanVien";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLNhanVien mt;
    		while(rs.next()){
    			mt = new DLNhanVien();
    			mt.setSoCMND(rs.getString(1));
    			mt.setHoTen(rs.getString(2));
    			mt.setMatKhau(rs.getString(3));
    			mt.setNgaySinh(rs.getDate(4));
    			mt.setGioiTinh(rs.getBoolean(5));
    			mt.setSoDT(rs.getString(6));
    			mt.setEmail(rs.getString(7));
    			mt.setHinhAnh(rs.getString(8));
    			mt.setAdmin(rs.getBoolean(9));
    			list.add(mt);
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
	
	public DLNhanVien getInfo(String soCMND, int so) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,GioiTinh,SoDT,Email,HinhAnh from NhanVien where SoCMND=?";
		if(so == 1) {
			sql += " and isAdmin = 1";
		}
		if(so == 0) {
			sql += " and isAdmin = 0";
		}
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, soCMND);
    		ResultSet rs = stmt.executeQuery();
    		DLNhanVien nv = null;
    		if(rs.next()){
    			nv = new DLNhanVien();
    			nv.setSoCMND(rs.getString(1));
    			nv.setHoTen(rs.getString(2));
    			nv.setMatKhau(rs.getString(3));
    			nv.setNgaySinh(rs.getDate(4));
    			nv.setGioiTinh(rs.getBoolean(5));
    			nv.setSoDT(rs.getString(6));
    			nv.setEmail(rs.getString(7));
    			nv.setHinhAnh(rs.getString(8));
    		}
        	stmt.close();
        	return nv;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public boolean insertNhanVien(DLNhanVien nv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select SoCMND from NhanVien where SoCMND=?";
		String sql = "insert into KhachHang(SoCMND,HoTen,MatKhau,NgaySinh,GioiTinh,SoDT,Email,HinhAnh,isAdmin) values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, nv.getSoCMND());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, nv.getSoCMND());
    			stmt.setString(2, nv.getHoTen());
    			stmt.setString(3, nv.getMatKhau());
    			stmt.setDate(4, nv.getNgaySinh());
    			stmt.setBoolean(5, nv.isGioiTinh());
    			stmt.setString(6, nv.getSoDT());
    			stmt.setString(7, nv.getEmail());
    			stmt.setString(8, nv.getHinhAnh());
    			stmt.setBoolean(9, nv.isAdmin());
    			check = stmt.executeUpdate();
    		} 
        	stmt.close();
        	if(check > 0){
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

	public boolean updateNhanVien(DLNhanVien nv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update NhanVien set HoTen=?,NgaySinh=?,GioiTinh=?,"
				+ "SoDT=?,Email=?,HinhAnh=?,isAdmin=? where SoCMND=?";
        PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, nv.getHoTen());
			stmt.setDate(2, nv.getNgaySinh());
			stmt.setBoolean(3, nv.isGioiTinh());
			stmt.setString(4, nv.getSoDT());
			stmt.setString(5, nv.getEmail());
			stmt.setString(6, nv.getHinhAnh());
			stmt.setInt(7, Boolean.compare(nv.isAdmin(), false));
			stmt.setString(8, nv.getSoCMND());
			int check = stmt.executeUpdate();
        	stmt.close();
        	if(check > 0){
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
	
	public boolean doiMatKhau(String soCMND, String matKhau) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update NhanVien set MatKhau=? where SoCMND=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, matKhau);
			stmt.setString(2, soCMND);
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

	public boolean quenMatKhau(String soCMND, String email) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlEmail = "select HoTen,Email,MatKhau from NhanVien where SoCMND=? and Email=?";
        PreparedStatement stmtEmail = null;
        try {
        	stmtEmail = con.getConnect().prepareStatement(sqlEmail);
        	stmtEmail.setString(1, soCMND);
        	stmtEmail.setString(2, email);
    		ResultSet rsEmail = stmtEmail.executeQuery();
    		if(rsEmail.next()){
				String tieuDe = "Quên mật khẩu tài khoản Nhân viên";
				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chào " + rsEmail.getString(1) + "</h2>");
				noiDung.append("<div style='padding:2% 3.5%;'><p>Bạn đã gửi một yêu cầu khôi phục mật khẩu của bạn. Nếu không bạn gửi yêu cầu bạn có thể bỏ qua email này!</p>");
				noiDung.append("<p>Để hoàn thành thao tác này bạn vui lòng ");
				noiDung.append("click vào <a href='http://localhost:8080/ProjectEDU/showDoiMatKhau.trip?soCMND=");
    			noiDung.append(soCMND);
    			noiDung.append("&&account=5&&matKhau=");
    			noiDung.append(rsEmail.getString(3));
    			noiDung.append("&&logined=true'>đây</a> để đổi mật khẩu của bạn.</div>");
				email = Library.xoaDau(rsEmail.getString(1)) + "<" + rsEmail.getString(2) + ">";
				new SendEmail(email, tieuDe, noiDung.toString()).start();
	        	return true;
    		}
        	if(stmtEmail != null){
        		stmtEmail.close();
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
        return false;
	}
}
