package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Library;
import common.SendEmail;
import model.bean.HoSoXetTuyen;
import model.bean.KhoiThiNganhDHCD;
import model.bean.Nganh;

public class ChiTietHoSoDAO {

	public boolean updateChiTietHoSo(HoSoXetTuyen hs, String[] ktNganh) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into ChiTietHoSo(MaHS,MaNganh,MaTruong,DaoTao,MaKhoi,NamTuyenSinh) values (?,?,?,?,?,?)";
        String sqlMaHS = "SELECT MaHS from HoSoXetTuyen where SoCMND=? and NamTS=? and MaDotXT=? and MaTruong=?";
		PreparedStatement stmt = null;
        try {
    		int check = 0;
    		stmt = con.getConnect().prepareStatement(sqlMaHS);
    		stmt.setString(1, hs.getSoCMND());
    		stmt.setInt(2, hs.getNamTS());
    		stmt.setString(3, hs.getMaDotXT());
    		stmt.setString(4, hs.getMaTruong());
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			String maHS = rs.getString(1);
    			stmt = con.getConnect().prepareStatement("delete from ChiTietHoSo where MaHS=?");
    			stmt.setString(1, maHS);
    			stmt.executeUpdate();
    			if(ktNganh != null){
	    			for(String s : ktNganh){
	    				String [] tmp = s.split("-");
						stmt = con.getConnect().prepareStatement(sql);
						stmt.setString(1, maHS);
						stmt.setString(2, tmp[0]);
						stmt.setString(3, hs.getMaTruong());
						stmt.setString(4, tmp[1]);
						stmt.setString(5, tmp[2]);
						stmt.setInt(6, hs.getNamTS());
						check += stmt.executeUpdate();
	    			}
    			} else {
    				return true;
    			}
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

	public ArrayList<String> getAllHoSo(HoSoXetTuyen ct) {
		ArrayList<String> list = new ArrayList<String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select ct.MaNganh,TenNganh,MaKhoi,DaoTao from ChiTietHoSo as ct inner join HoSoXetTuyen as hs "
				+ "on ct.MaHS = hs.MaHS inner join Nganh as ndh on ct.MaNganh = ndh.MaNganh "
				+ "where SoCMND=? and NamTS=? and MaDotXT=? and hs.MaTruong=?";
		String sqlMon = "select TenMonThi from ChiTietKhoiThi as ct inner join MonThi as mt "
				+ "on ct.MaMon=mt.MaMon where MaKhoi=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, ct.getSoCMND());
    		stmt.setInt(2, ct.getNamTS());
    		stmt.setString(3, ct.getMaDotXT());
    		stmt.setString(4, ct.getMaTruong());
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			StringBuffer tenMT = new StringBuffer(rs.getString(3) + " (");
				PreparedStatement stmtKT = con.getConnect().prepareStatement(sqlMon);
				stmtKT.setString(1, rs.getString(3));
				ResultSet rsMon = stmtKT.executeQuery();
				int i = 1;
				while(rsMon.next()){
					if(i==1){
						tenMT.append(rsMon.getString(1));
					} else {
						tenMT.append(" - ");
						tenMT.append(rsMon.getString(1));
					}
					i++;
				}
    			list.add(rs.getString(1) + "--" + rs.getString(2) +"(" + rs.getString(4) + ")--" + tenMT.append(")").toString()
    			+ "--" + rs.getString(1) + "-" + rs.getString(4) + "-" + rs.getString(3));
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
	
	public boolean sendMailTrungTuyen(List<KhoiThiNganhDHCD> listKTN){
		ConnectDB con = new ConnectDB();
		con.openConnection();
		StringBuilder sql = new StringBuilder();
		sql.append("select ct.MaHS,ts.SoCMND,HoTen,Email,ct.MaNganh,TenNganh,ct.DaoTao,ct.MaTruong,TenTruong, ");
		sql.append("	ct.MaKhoi,TenDotXT,ct.NamTuyenSinh,TongDiem,DiemChuan ");
		sql.append("from ChiTietHoSo as ct inner join Nganh as n on ct.MaNganh=n.MaNganh ");
		sql.append("inner join TruongDH_CD as tr on ct.MaTruong=tr.MaTruong ");
		sql.append("inner join HoSoXetTuyen as hs on ct.MaHS=hs.MaHS ");
		sql.append("inner join ThiSinh as ts on hs.SoCMND=ts.SoCMND ");
		sql.append("inner join DotXetTuyen as dxt on hs.MaDotXT=dxt.MaDotXT ");
		sql.append("inner join KhoiThi_NganhDHCD as ktn on ct.MaNganh=ktn.MaNganh and ct.MaTruong = ktn.MaTruong ");
		sql.append("	and ct.DaoTao = ktn.DaoTao and ct.MaKhoi=ktn.MaKhoi and ct.NamTuyenSinh=ktn.NamTuyenSinh ");
		sql.append("where ct.MaNganh=? and ct.MaTruong=? and ct.DaoTao=? and ct.MaKhoi=? and ct.NamTuyenSinh=? and SendMail='false' and TrungTuyen='true'");
		PreparedStatement stmt = null;
		try {
			for (KhoiThiNganhDHCD ktn : listKTN) {
				stmt = con.getConnect().prepareStatement(sql.toString());
				stmt.setString(1, ktn.getMaNganh());
				stmt.setString(2, ktn.getMaTruong());
				stmt.setString(3, ktn.getDaoTao());
				stmt.setString(4, ktn.getMaKhoi());
				stmt.setInt(5, ktn.getNamTuyenSinh());
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					String tieuDe = "Thông báo trúng tuyển";
    				StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
    				noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Xin chào " + rs.getString(3) + "</h2>");
    				noiDung.append("<div style='padding:2% 3.5%;'><p>Chúc mừng bạn đã trúng tuyển trong đợt xét tuyển <b>"+rs.getString(11));
    				noiDung.append("</b>. Của <b>" + rs.getString(9) + "</b></p><p>Chi tiết thông tin trúng tuyển như sau</p>");
    				noiDung.append("<table align='center' style='background-color:#c7dcfc;padding:3%;'>");
    				noiDung.append("<tr><td>Số chứng minh </td>");
    				noiDung.append("<td> : <b>" + rs.getString(2) + "</b></td><tr>");
    				noiDung.append("<tr><td>Họ tên </td>");
    				noiDung.append("<td> : <b>" + rs.getString(3) + "</b></td><tr>");
    				noiDung.append("<tr><td>Ngành </td>");
    				noiDung.append("<td> : <b>" + rs.getString(5) + " - " + rs.getString(6) + "</b></td><tr>");
    				noiDung.append("<tr><td>Hệ đào tạo </td>");
    				String tmp = ("DHCQ".equals(rs.getString(7))?"Đại học chính quy":("DHLT".equals(rs.getString(7))?"Đại học liên thông"
    						:("CDCQ".equals(rs.getString(7))?"Cao đẳng chính quy":("CDLT".equals(rs.getString(7))?"Cao đẳng liên thông":"Trung cấp chuyên nghiệp"))));
    				noiDung.append("<td> : <b>" + tmp + "</b></td><tr>");
    				noiDung.append("<tr><td>Trường </td>");
    				noiDung.append("<td> : <b>" + rs.getString(8) + " - " + rs.getString(9) + "</b></td><tr>");
    				noiDung.append("<tr><td>Khối thi </td>");
    				noiDung.append("<td> : <b>" + rs.getString(10) + "</b></td><tr>");
    				noiDung.append("<tr><td>Năm tuyển sinh </td>");
    				noiDung.append("<td> : <b>" + rs.getInt(12) + "</b></td><tr>");
    				noiDung.append("<tr><td>Tổng điểm </td>");
    				noiDung.append("<td> : <b>" + rs.getInt(13) + "</b></td><tr>");
    				noiDung.append("<tr><td>Điểm chuẩn </td>");
    				noiDung.append("<td> : <b>" + rs.getInt(14) + "</b></td><tr></table>");
    				
    				noiDung.append("<p>Tổng điểm đã bao gồm các loại điểm ưu tiên và nhân hệ số (nếu có).</p>");
    				noiDung.append("<p>Giấy báo nhập học sẽ được gửi tới địa chỉ đăng ký của bạn trong thời gian sớm nhất.</p></div></div>");
					String email = Library.xoaDau(rs.getString(1)) + "<" + rs.getString(4) + ">";
					new SendEmail(email, tieuDe, noiDung.toString()).start();
					SendEmail.sleep(500);
					PreparedStatement udStmt = con.getConnect().prepareStatement("update ChiTietHoSo set SendMail='true' where MaHS=? and MaNganh=? and MaTruong=? and DaoTao=? and MaKhoi=? and NamTuyenSinh=?");
					udStmt.setString(1, rs.getString(1));
					udStmt.setString(2, ktn.getMaNganh());
					udStmt.setString(3, ktn.getMaTruong());
					udStmt.setString(4, ktn.getDaoTao());
					udStmt.setString(5, ktn.getMaKhoi());
					udStmt.setInt(6, ktn.getNamTuyenSinh());
					udStmt.executeUpdate();
					udStmt.close();
				}
				stmt.close();
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}
	
	public List<Nganh> getAllNganhByMaHS(String maHS) {
		ArrayList<Nganh> list = new ArrayList<Nganh>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select ct.MaNganh,TenNganh,MaKhoi,DaoTao from ChiTietHoSo as ct inner join HoSoXetTuyen as hs "
				+ "on ct.MaHS = hs.MaHS inner join Nganh as ndh on ct.MaNganh = ndh.MaNganh "
				+ "where ct.MaHS=?";
		String sqlMon = "select TenMonThi from ChiTietKhoiThi as ct inner join MonThi as mt "
				+ "on ct.MaMon=mt.MaMon where MaKhoi=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maHS);
    		ResultSet rs = stmt.executeQuery();
    		Nganh n;
    		while(rs.next()){
    			StringBuffer tenMT = new StringBuffer(rs.getString(3) + " (");
				PreparedStatement stmtKT = con.getConnect().prepareStatement(sqlMon);
				stmtKT.setString(1, rs.getString(3));
				ResultSet rsMon = stmtKT.executeQuery();
				int i = 1;
				while(rsMon.next()){
					if(i==1){
						tenMT.append(rsMon.getString(1));
					} else {
						tenMT.append(" - ");
						tenMT.append(rsMon.getString(1));
					}
					i++;
				}
				n = new Nganh();
				n.setMaNganh(rs.getString(1));
				n.setTenNganh(rs.getString(2) +"(" + rs.getString(4) + ")");
				n.setGhiChu(tenMT.append(")").toString());
				list.add(n);
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
}
