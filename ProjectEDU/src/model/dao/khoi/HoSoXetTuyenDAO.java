package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.HoSoXetTuyen;

public class HoSoXetTuyenDAO {

	public boolean insertHoSoXetTuyen(HoSoXetTuyen hs) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into HoSoXetTuyen(MaHS,SoCMND,NamTS,MaDotXT,MaTruong) values (?,?,?,?,?)";
        String sqlCheck = "select * from HoSoXetTuyen where SoCMND=? and NamTS=? and MaDotXT=? and MaTruong=?";
		PreparedStatement stmt = null;
        try {
    		int check = 0;
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, hs.getSoCMND());
			stmt.setInt(2, hs.getNamTS());
			stmt.setString(3, hs.getMaDotXT());
			stmt.setString(4, hs.getMaTruong());
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()){
				stmt = con.getConnect().prepareStatement(sql);
				stmt.setString(1, hs.getMaHS());
				stmt.setString(2, hs.getSoCMND());
				stmt.setInt(3, hs.getNamTS());
				stmt.setString(4, hs.getMaDotXT());
				stmt.setString(5, hs.getMaTruong());
				check = stmt.executeUpdate();
			} else {
				return true;
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

	public boolean deleteHoSoXetTuyen(HoSoXetTuyen hs) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
        String sql = "delete from HoSoXetTuyen where SoCMND=? and NamTS=? and MaDotXT=? and MaTruong=?";
		PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, hs.getSoCMND());
			stmt.setInt(2, hs.getNamTS());
			stmt.setString(3, hs.getMaDotXT());
			stmt.setString(4, hs.getMaTruong());
			if(stmt.executeUpdate() > 0){
				return true;
			}
        	stmt.close();
        	return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
	}

	public boolean isValidKhoiThi(String soCMND, String khoiThi) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
        String sql = "select MaMon from ChiTietKhoiThi where MaKhoi=? and MaMon not in(select MaMon from ChiTietDKDT where SoCMND=?)";
		PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, khoiThi);
			stmt.setString(2, soCMND);
			if(!stmt.executeQuery().next()){
				return true;
			}
        	stmt.close();
        	return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
	}

	public List<HoSoXetTuyen> getAllBySoCMND(String soCMND) {
		List<HoSoXetTuyen> list = new ArrayList<HoSoXetTuyen>();
		ConnectDB con = new ConnectDB();
		String sql = "select MaHS,NamTS,TenDotXT,hs.MaTruong,TenTruong,NgayNop,TrangThai "
				+ "from HoSoXetTuyen as hs inner join DotXetTuyen as d on hs.MaDotXT=d.MaDotXT "
				+ "inner join TruongDH_CD as t on hs.MaTruong=t.MaTruong where SoCMND=? "
				+ "and NamTS=(select top 1 NamTS from NamTuyenSinh order by NamTS desc)";
		con.openConnection();
		try {
			PreparedStatement stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs = stmt.executeQuery();
			HoSoXetTuyen hs;
			while(rs.next()){
				hs = new HoSoXetTuyen();
				hs.setMaHS(rs.getString(1));
				hs.setNamTS(rs.getInt(2));
				hs.setMaDotXT(rs.getString(3));
				hs.setSoCMND(rs.getString(4));
				hs.setMaTruong(rs.getString(5));
				hs.setNgayNop(rs.getDate(6));
				hs.setTrangThai(rs.getBoolean(7));
				list.add(hs);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeConnection();
		}
		return list;
	}
}
