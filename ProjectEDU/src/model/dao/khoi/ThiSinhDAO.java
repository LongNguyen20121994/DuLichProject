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
	    				String tieuDe = (trangThai && !logined?"KiÌ�ch hoaÌ£t" : trangThai && logined?"Má»Ÿ khÃ³a" : "KhÃ³a") + " taÌ€i khoaÌ‰n Ä‘Äƒng kyÌ� dÆ°Ì£ thi";
	    				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
	    				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chÃ o " + rsEmail.getString(1) + "</h2>");
	    				if(trangThai && !logined) {
		    				noiDung.append("<div style='padding:2% 3.5%;'><p>BaÌ£n Ä‘aÌƒ thÆ°Ì£c hiÃªÌ£n Ä‘Äƒng kyÌ� dÆ°Ì£ thi kyÌ€ thi tuyÃªÌ‰n sinh sÄƒÌ�p tÆ¡Ì�i, thÃ´ng tin baÌ£n cung cÃ¢Ì�p Ä‘aÌƒ Ä‘Æ°Æ¡Ì£c xaÌ�c thÆ°Ì£c.");
		    				noiDung.append("<br/>HiÃªÌ£n taÌ£i tÃ i khoáº£n cá»§a báº¡n Ä‘Ã£ Ä‘Æ°á»£c kÃ­ch hoáº¡t. </p><p>BaÌ£n coÌ� traÌ�ch nhiÃªÌ£m baÌ‰o vÃªÌ£ taÌ€i khoaÌ‰n vaÌ€ chiÌ£u hoaÌ€n toaÌ€n ");
		    				noiDung.append("traÌ�ch nhiÃªÌ£m vÃªÌ€ caÌ�c thÃ´ng tin thay Ä‘Ã´Ì‰i<br/>BaÌ£n coÌ� thÃªÌ‰ sÆ°Ì‰ duÌ£ng taÌ€i khoaÌ‰n cuÌ‰a miÌ€nh Ä‘ÃªÌ‰ : </p>");
		    				noiDung.append("<ul><li>Ä�iÃªÌ€u chiÌ‰nh thÃ´ng tin Ä‘Äƒng kyÌ� dÆ°Ì£ thi.</li><li>Xem Ä‘iÃªÌ‰m sau khi coÌ� kÃªÌ�t quaÌ‰.</li>");
		    				noiDung.append("<li>Ä�Äƒng kyÌ� xeÌ�t tuyÃªÌ‰n vaÌ€o caÌ�c trÆ°Æ¡Ì€ng Ä�aÌ£i hoÌ£c - Cao Ä‘ÄƒÌ‰ng</li><li>SÆ°Ì‰ duÌ£ng taÌ€i khoaÌ‰n Ä‘ÃªÌ‰ Ä‘Äƒng kyÌ� dÆ°Ì£ thi Æ¡Ì‰ caÌ�c nÄƒm tiÃªÌ�p theo</li></ul>");
		    				noiDung.append("<p>ThÃ´ng tin kÃ­ch hoáº¡t tÃ i khoáº£n</p>");
		    				noiDung.append("<table align='center' style='background-color:#c7dcfc;padding:3%;'>");
		    				noiDung.append("<tr><td>Sá»‘ chá»©ng minh </td>");
		    				noiDung.append("<td> : <b>" + tmp + "</b></td><tr>");
		    				noiDung.append("<tr><td>Máº­t kháº©u </td>");
		    				noiDung.append("<td> : <b><em>" + rsEmail.getString(3) + "</em></b></td><tr></table>");
		    				noiDung.append("<p>BaÌ£n vui loÌ€ng thÆ°Ì£c hiÃªÌ£n Ä‘Äƒng nhÃ¢Ì£p vaÌ€ Ä‘Ã´Ì‰i mÃ¢Ì£t khÃ¢Ì‰u Ä‘ÃªÌ‰ kiÌ�ch hoaÌ£t taÌ€i khoaÌ‰n cuÌ‰a baÌ£n.</p>");
		    				noiDung.append("<p>Click vÃ o <a href='http://localhost:8080/ProjectEDU/login.trip?soCMND=");
			    			noiDung.append(tmp);
			    			noiDung.append("&&account=1&&matKhau=");
			    			noiDung.append(rsEmail.getString(3));
			    			noiDung.append("'>Ä‘Ã¢y</a> Ä‘á»ƒ Ä‘á»•i máº­t kháº©u cá»§a báº¡n.<br/>");
		    				noiDung.append("HoÄƒÌ£c click vÃ o <a href='http://localhost:8080/ProjectEDU/showLogin.trip'>Ä‘Ã¢y</a> ");
							noiDung.append("Ä‘á»ƒ Ä‘Äƒng nhÃ¢Ì£p.</p></div></div>");
	    				} else {
	    					if(trangThai && logined) {
	    						noiDung.append("<div style='padding:2% 3.5%;'><p>TÃ i khoáº£n cá»§a báº¡n Ä‘Ã£ Ä‘Æ°á»£c má»Ÿ khÃ³a. Báº¡n cÃ³ thá»ƒ Ä‘Äƒng nháº­p Ä‘á»ƒ thá»±c hiá»‡n cÃ¡c tÃ¡c vá»¥ báº±ng máº­t kháº©u cÅ© cá»§a báº¡n.</p></div></div>");
	    					} else {
	    						noiDung.append("<div style='padding:2% 3.5%;'><p>TÃ i khoáº£n cá»§a báº¡n Ä‘Ã£ bá»‹ khÃ³a. Vui lÃ²ng liÃªn há»‡ quáº£n trá»‹ viÃªn Ä‘á»ƒ biáº¿t thÃªm chi tiáº¿t.</p></div></div>");
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
				String tieuDe = "QuÃªn máº­t kháº©u tÃ i khoáº£n Ä‘Äƒng kÃ½ dá»± thi";
				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chÃ o " + rsEmail.getString(1) + "</h2>");
				noiDung.append("<div style='padding:2% 3.5%;'><p>BaÌ£n Ä‘aÌƒ gá»­i má»™t yÃªu cáº§u khÃ´i phá»¥c máº­t kháº©u cá»§a báº¡n. Náº¿u khÃ´ng báº¡n gá»­i yÃªu cáº§u báº¡n cÃ³ thá»ƒ bá»� qua email nÃ y!</p>");
				noiDung.append("<p>Ä�á»ƒ hoÃ n thÃ nh thao tÃ¡c nÃ y báº¡n vui lÃ²ng ");
				noiDung.append("click vÃ o <a href='http://localhost:8080/ProjectEDU/showDoiMatKhau.trip?soCMND=");
    			noiDung.append(soCMND);
    			noiDung.append("&&account=1&&matKhau=");
    			noiDung.append(rsEmail.getString(3));
    			noiDung.append("&&logined=true'>Ä‘Ã¢y</a> Ä‘á»ƒ Ä‘á»•i máº­t kháº©u cá»§a báº¡n.</div>");
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
