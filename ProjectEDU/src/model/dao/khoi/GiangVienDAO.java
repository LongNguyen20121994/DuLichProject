package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Library;
import common.SendEmail;
import model.bean.GiangVien;

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
    		if(rs.next()){
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
        	if(rs > 0){
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
		String sql = "insert into GiangVien(SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,MaTruong,HinhAnh) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, gv.getSoCMND());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
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

	public List<GiangVien> getListGiangVienByTrangThai(boolean trangThai, boolean logined) {
		ArrayList<GiangVien> list = new ArrayList<GiangVien>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND, HoTen, NgaySinh, GioiTinh, SoDT, Email from GiangVien where TrangThai=? and Logined=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setBoolean(1, trangThai);
			stmt.setBoolean(2, logined);
			ResultSet rs = stmt.executeQuery();
			GiangVien ts;

			while (rs.next()) {
				ts = new GiangVien();
				ts.setSoCMND(rs.getString(1));
				ts.setHoTen(rs.getString(2));
				ts.setNgaySinh(rs.getDate(3));
				ts.setGioiTinh(rs.getBoolean(4));
				ts.setSoDT(rs.getString(5));
				ts.setEmail(rs.getString(6));
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

	public boolean kichHoatTaiKhoan(String[] listSoCMND, boolean trangThai, boolean logined) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update GiangVien set TrangThai=? where SoCMND=?";
		String sqlEmail = "select HoTen,Email,MatKhau,TenTruong from GiangVien as gv "
				+ "inner join TruongDH_CD as tr on gv.MaTruong = tr.MaTruong where SoCMND=?";
        PreparedStatement stmt = null;
        PreparedStatement stmtEmail = null;
        try {
        	int rs = 0;
        	for(String tmp : listSoCMND){
	    		stmt = con.getConnect().prepareStatement(sql);
	    		stmt.setBoolean(1, trangThai);
	    		stmt.setString(2, tmp);
	    		int check = stmt.executeUpdate();
	    		if(check > 0){
	    			stmtEmail = con.getConnect().prepareStatement(sqlEmail);
	    			stmtEmail.setString(1, tmp);
	    			ResultSet rsEmail = stmtEmail.executeQuery();
	    			if(rsEmail.next()){
	    				String tieuDe = (trangThai && !logined?"Kích hoạt" : trangThai && logined?"Mở khóa" : "Khóa") + " tài khoản giảng viên trường Đại học - Cao đẳng";
	    				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
	    				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chào " + rsEmail.getString(1) + "</h2>");
	    				if(trangThai && !logined) {
		    				noiDung.append("<div style='padding:2% 3.5%;'><p>Bạn đã thực hiện đăng ký tài khoản giảng viên trường \""+rsEmail.getString(4));
		    				noiDung.append("\", thông tin bạn cung cấp đã được xác thực.");
		    				noiDung.append(" Hiện tại tài khoản của bạn đã được kích hoạt. </p><p>Bạn có thể sử dụng tài khoản của mình để : </p>");
		    				noiDung.append("<ul><li>Cập nhật chi tiết các ngành đào tạo của trường</li><li>Cập nhật khối thi của các ngành và hệ số môn thi</li>");
		    				noiDung.append("<li>Cập nhật điểm chuẩn vào các ngành</li></ul>");
		    				noiDung.append("<p>Bạn có trách nhiệm bảo vệ tài khoản và chịu hoàn toàn trách nhiệm về các tác vụ xử lý khi sử dụng tài khoản.<br/>");
		    				noiDung.append("Thông tin kích hoạt tài khoản</p>");
		    				noiDung.append("<table align='center' style='background-color:#c7dcfc;padding:3%;'>");
		    				noiDung.append("<tr><td>Số chứng minh </td>");
		    				noiDung.append("<td> : <b>" + tmp + "</b></td><tr>");
		    				noiDung.append("<tr><td>Mật khẩu </td>");
		    				noiDung.append("<td> : <b><em>" + rsEmail.getString(3) + "</em></b></td><tr></table>");
		    				noiDung.append("<p>Bạn vui lòng thực hiện đăng nhập và đổi mật khẩu để kích hoạt tài khoản của bạn.</p>");
			    			noiDung.append("<p>Click vào <a href='http://localhost:8080/ProjectEDU/login.trip?soCMND="+tmp);
			    			noiDung.append("&&account=3&&matKhau=");
			    			noiDung.append(rsEmail.getString(3));
			    			noiDung.append("'>đây</a> để đổi mật khẩu của bạn.<br/>");
		    				noiDung.append("Hoặc click vào <a href='http://localhost:8080/ProjectEDU/showLogin.trip'>đây</a> ");
							noiDung.append("để đăng nhập.</p></div></div>");
	    				} else {
	    					if(trangThai && logined) {
	    						noiDung.append("<div style='padding:2% 3.5%;'><p>Tài khoản của bạn đã được mở khóa. Bạn có thể đăng nhập để thực hiện các tác vụ bằng mật khẩu cũ của bạn.</p></div></div>");
	    					} else {
	    						noiDung.append("<div style='padding:2% 3.5%;'><p>Tài khoản của bạn đã bị khóa. Vui lòng liên hệ quản trị viên để biết thêm chi tiết.</p></div></div>");
	    					}
	    				}
						String email = Library.xoaDau(rsEmail.getString(1)) + "<" + rsEmail.getString(2) + ">";
						new SendEmail(email, tieuDe, noiDung.toString()).start();
						SendEmail.sleep(500);
	    			}
	    		}
	    		rs += check;
    		}
        	stmt.close();
        	if(rs > 0){
        		return true;
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (InterruptedException e) {
        	System.out.println(e.getMessage());
            return false;
		} finally {
            con.closeConnection();
        }
        return false;
	}

	public boolean xoaTaiKhoan(String[] listSoCMND) {
		StringBuilder str = new StringBuilder("('");
        for (int i = 0; i < listSoCMND.length - 1; i++) {
            str.append(listSoCMND[i]);
            str.append("','");
        }
        str.append(listSoCMND[listSoCMND.length - 1]);
        str.append("')");
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "delete from GiangVien where SoCMND in " + str.toString();
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		int rs = stmt.executeUpdate();
        	stmt.close();
        	if(rs > 0){
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
		String sqlEmail = "select HoTen,Email,MatKhau from GiangVien where SoCMND=? and Email=?";
        PreparedStatement stmtEmail = null;
        try {
        	stmtEmail = con.getConnect().prepareStatement(sqlEmail);
        	stmtEmail.setString(1, soCMND);
        	stmtEmail.setString(2, email);
    		ResultSet rsEmail = stmtEmail.executeQuery();
    		if(rsEmail.next()){
				String tieuDe = "Quên mật khẩu tài khoản giảng viên";
				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chào " + rsEmail.getString(1) + "</h2>");
				noiDung.append("<div style='padding:2% 3.5%;'><p>Bạn đã gửi một yêu cầu khôi phục mật khẩu của bạn. Nếu không bạn gửi yêu cầu bạn có thể bỏ qua email này!</p>");
				noiDung.append("<p>Để hoàn thành thao tác này bạn vui lòng ");
				noiDung.append("click vào <a href='http://localhost:8080/ProjectEDU/showDoiMatKhau.trip?soCMND=");
    			noiDung.append(soCMND);
    			noiDung.append("&&account=3&&matKhau=");
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

	public boolean updateGiangVien(GiangVien gv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update GiangVien set HoTen=?,NgaySinh=?,MaXa=?,MaHuyen=?,MaTinh=?,GioiTinh=?,"
				+ "SoDT=?,Email=?,MaTruong=?,HinhAnh=? where SoCMND=?";
        PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, gv.getHoTen());
			stmt.setDate(2, gv.getNgaySinh());
			stmt.setString(3, gv.getMaXa());
			stmt.setString(4, gv.getMaHuyen());
			stmt.setString(5, gv.getMaTinh());
			stmt.setBoolean(6, gv.isGioiTinh());
			stmt.setString(7, gv.getSoDT());
			stmt.setString(8, gv.getEmail());
			stmt.setString(9, gv.getMaTruong());
			stmt.setString(10, gv.getHinhAnh());
			stmt.setString(11, gv.getSoCMND());
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
}
