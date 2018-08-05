package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Library;
import common.SendEmail;
import model.bean.ThiSinh;

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
    		if(rs.next()){
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
		String sql = "update ThiSinh set MatKhau=?, Logined=? where SoCMND=?";
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
	public static void main(String[] args){
		System.out.println(new ThiSinhDAO().getInfo("123456789"));
	}

	public List<ThiSinh> getListThiSinhByTrangThai(boolean trangThai, boolean logined) {
		ArrayList<ThiSinh> list = new ArrayList<ThiSinh>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND, HoTen, NgaySinh, GioiTinh, SoDT,"
				+ " Email, DiaChi, NoiSinh, NamTN, MatKhau from ThiSinh where TrangThai=? and Logined=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setBoolean(1, trangThai);
			stmt.setBoolean(2, logined);
			ResultSet rs = stmt.executeQuery();
			ThiSinh ts;

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

	public boolean kichHoatTaiKhoan(String[] listSoCMND, boolean trangThai, boolean logined) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update ThiSinh set TrangThai=? where SoCMND=?";
		String sqlEmail = "select HoTen,Email,MatKhau from ThiSinh where SoCMND=?";
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
	    				String tieuDe = (trangThai && !logined?"Kích hoạt" : trangThai && logined?"Mở khóa" : "Khóa") + " tài khoản đăng ký dự thi";
	    				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
	    				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chào " + rsEmail.getString(1) + "</h2>");
	    				if(trangThai && !logined) {
		    				noiDung.append("<div style='padding:2% 3.5%;'><p>Bạn đã thực hiện đăng ký dự thi kỳ thi tuyển sinh sắp tới, thông tin bạn cung cấp đã được xác thực.");
		    				noiDung.append("<br/>Hiện tại tài khoản của bạn đã được kích hoạt. </p><p>Bạn có trách nhiệm bảo vệ tài khoản và chịu hoàn toàn ");
		    				noiDung.append("trách nhiệm về các thông tin thay đổi<br/>Bạn có thể sử dụng tài khoản của mình để : </p>");
		    				noiDung.append("<ul><li>Điều chỉnh thông tin đăng ký dự thi.</li><li>Xem điểm sau khi có kết quả.</li>");
		    				noiDung.append("<li>Đăng ký xét tuyển vào các trường Đại học - Cao đẳng</li><li>Sử dụng tài khoản để đăng ký dự thi ở các năm tiếp theo</li></ul>");
		    				noiDung.append("<p>Thông tin kích hoạt tài khoản</p>");
		    				noiDung.append("<table align='center' style='background-color:#c7dcfc;padding:3%;'>");
		    				noiDung.append("<tr><td>Số chứng minh </td>");
		    				noiDung.append("<td> : <b>" + tmp + "</b></td><tr>");
		    				noiDung.append("<tr><td>Mật khẩu </td>");
		    				noiDung.append("<td> : <b><em>" + rsEmail.getString(3) + "</em></b></td><tr></table>");
		    				noiDung.append("<p>Bạn vui lòng thực hiện đăng nhập và đổi mật khẩu để kích hoạt tài khoản của bạn.</p>");
		    				noiDung.append("<p>Click vào <a href='http://localhost:8080/ProjectEDU/login.trip?soCMND=");
			    			noiDung.append(tmp);
			    			noiDung.append("&&account=1&&matKhau=");
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
		String sql = "delete from ThiSinh where SoCMND in " + str.toString();
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
		String sqlEmail = "select HoTen,Email,MatKhau from ThiSinh where SoCMND=? and Email=?";
        PreparedStatement stmtEmail = null;
        try {
        	stmtEmail = con.getConnect().prepareStatement(sqlEmail);
        	stmtEmail.setString(1, soCMND);
        	stmtEmail.setString(2, email);
    		ResultSet rsEmail = stmtEmail.executeQuery();
    		if(rsEmail.next()){
				String tieuDe = "Quên mật khẩu tài khoản đăng ký dự thi";
				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chào " + rsEmail.getString(1) + "</h2>");
				noiDung.append("<div style='padding:2% 3.5%;'><p>Bạn đã gửi một yêu cầu khôi phục mật khẩu của bạn. Nếu không bạn gửi yêu cầu bạn có thể bỏ qua email này!</p>");
				noiDung.append("<p>Để hoàn thành thao tác này bạn vui lòng ");
				noiDung.append("click vào <a href='http://localhost:8080/ProjectEDU/showDoiMatKhau.trip?soCMND=");
    			noiDung.append(soCMND);
    			noiDung.append("&&account=1&&matKhau=");
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
