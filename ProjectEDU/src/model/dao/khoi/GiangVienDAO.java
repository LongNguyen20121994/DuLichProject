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
	    				String tieuDe = (trangThai && !logined?"KiÌ�ch hoaÌ£t" : trangThai && logined?"Má»Ÿ khÃ³a" : "KhÃ³a") + " taÌ€i khoaÌ‰n giaÌ‰ng viÃªn trÆ°Æ¡Ì€ng Ä�aÌ£i hoÌ£c - Cao Ä‘ÄƒÌ‰ng";
	    				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
	    				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chÃ o " + rsEmail.getString(1) + "</h2>");
	    				if(trangThai && !logined) {
		    				noiDung.append("<div style='padding:2% 3.5%;'><p>BaÌ£n Ä‘aÌƒ thÆ°Ì£c hiÃªÌ£n Ä‘Äƒng kyÌ� taÌ€i khoaÌ‰n giaÌ‰ng viÃªn trÆ°Æ¡Ì€ng \""+rsEmail.getString(4));
		    				noiDung.append("\", thÃ´ng tin baÌ£n cung cÃ¢Ì�p Ä‘aÌƒ Ä‘Æ°Æ¡Ì£c xaÌ�c thÆ°Ì£c.");
		    				noiDung.append(" HiÃªÌ£n taÌ£i tÃ i khoáº£n cá»§a báº¡n Ä‘Ã£ Ä‘Æ°á»£c kÃ­ch hoáº¡t. </p><p>BaÌ£n coÌ� thÃªÌ‰ sÆ°Ì‰ duÌ£ng taÌ€i khoaÌ‰n cuÌ‰a miÌ€nh Ä‘ÃªÌ‰ : </p>");
		    				noiDung.append("<ul><li>CÃ¢Ì£p nhÃ¢Ì£t chi tiÃªÌ�t caÌ�c ngaÌ€nh Ä‘aÌ€o taÌ£o cuÌ‰a trÆ°Æ¡Ì€ng</li><li>CÃ¢Ì£p nhÃ¢Ì£t khÃ´Ì�i thi cuÌ‰a caÌ�c ngaÌ€nh vaÌ€ hÃªÌ£ sÃ´Ì� mÃ´n thi</li>");
		    				noiDung.append("<li>CÃ¢Ì£p nhÃ¢Ì£t Ä‘iÃªÌ‰m chuÃ¢Ì‰n vaÌ€o caÌ�c ngaÌ€nh</li></ul>");
		    				noiDung.append("<p>BaÌ£n coÌ� traÌ�ch nhiÃªÌ£m baÌ‰o vÃªÌ£ taÌ€i khoaÌ‰n vaÌ€ chiÌ£u hoaÌ€n toaÌ€n traÌ�ch nhiÃªÌ£m vÃªÌ€ caÌ�c taÌ�c vuÌ£ xÆ°Ì‰ lyÌ� khi sÆ°Ì‰ duÌ£ng taÌ€i khoaÌ‰n.<br/>");
		    				noiDung.append("ThÃ´ng tin kÃ­ch hoáº¡t tÃ i khoáº£n</p>");
		    				noiDung.append("<table align='center' style='background-color:#c7dcfc;padding:3%;'>");
		    				noiDung.append("<tr><td>Sá»‘ chá»©ng minh </td>");
		    				noiDung.append("<td> : <b>" + tmp + "</b></td><tr>");
		    				noiDung.append("<tr><td>Máº­t kháº©u </td>");
		    				noiDung.append("<td> : <b><em>" + rsEmail.getString(3) + "</em></b></td><tr></table>");
		    				noiDung.append("<p>BaÌ£n vui loÌ€ng thÆ°Ì£c hiÃªÌ£n Ä‘Äƒng nhÃ¢Ì£p vaÌ€ Ä‘Ã´Ì‰i mÃ¢Ì£t khÃ¢Ì‰u Ä‘ÃªÌ‰ kiÌ�ch hoaÌ£t taÌ€i khoaÌ‰n cuÌ‰a baÌ£n.</p>");
			    			noiDung.append("<p>Click vÃ o <a href='http://localhost:8080/ProjectEDU/login.trip?soCMND="+tmp);
			    			noiDung.append("&&account=3&&matKhau=");
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
				String tieuDe = "QuÃªn máº­t kháº©u tÃ i khoáº£n giáº£ng viÃªn";
				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chÃ o " + rsEmail.getString(1) + "</h2>");
				noiDung.append("<div style='padding:2% 3.5%;'><p>BaÌ£n Ä‘aÌƒ gá»­i má»™t yÃªu cáº§u khÃ´i phá»¥c máº­t kháº©u cá»§a báº¡n. Náº¿u khÃ´ng báº¡n gá»­i yÃªu cáº§u báº¡n cÃ³ thá»ƒ bá»� qua email nÃ y!</p>");
				noiDung.append("<p>Ä�á»ƒ hoÃ n thÃ nh thao tÃ¡c nÃ y báº¡n vui lÃ²ng ");
				noiDung.append("click vÃ o <a href='http://localhost:8080/ProjectEDU/showDoiMatKhau.trip?soCMND=");
    			noiDung.append(soCMND);
    			noiDung.append("&&account=3&&matKhau=");
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
