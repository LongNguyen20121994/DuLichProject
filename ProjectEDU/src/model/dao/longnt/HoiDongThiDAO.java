package model.dao.longnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.HoiDongThi;
import model.dao.khoi.ConnectDB;

public class HoiDongThiDAO {
	public HoiDongThi getInfo(String maHDT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaHDT,MaCT,TenHDT from HoiDongThi where MaHDT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maHDT);
    		ResultSet rs = stmt.executeQuery();
    		HoiDongThi hdt = null;
    		if(rs.next()){
    			hdt = new HoiDongThi();
    			hdt.setMaHDT(rs.getString(1));
    			hdt.setMaCT(rs.getString(2));
    			hdt.setTenHDT(rs.getString(3));
    		} 
        	stmt.close();
        	return hdt;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}

	public boolean insertHoiDongThi(HoiDongThi hdt,String cumThi) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaHDT from HoiDongThi where MaHDT=?";
		String sql = "insert into HoiDongThi(MaHDT,MaCT,TenHDT) values(?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, hdt.getMaHDT());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, hdt.getMaHDT());
    			stmt.setString(2, cumThi);
    			stmt.setString(3, hdt.getTenHDT());
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
	
	public List<HoiDongThi> getAll() {
		List<HoiDongThi> list = new ArrayList<HoiDongThi>();
		ConnectDB con = new ConnectDB();
		String sql = "select MaHDT,MaCT,TenHDT from HoiDongThi";
		con.openConnection();
		try {
			PreparedStatement stmt = con.getConnect().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			HoiDongThi hdt;
			while(rs.next()){
				hdt = new HoiDongThi();
				hdt.setMaHDT(rs.getString(1));
				hdt.setMaCT(rs.getString(2));
				hdt.setTenHDT(rs.getString(3));
				list.add(hdt);
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

	public boolean updateHoiDongThi(HoiDongThi hdt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update HoiDongThi set MaCT=?,TenHDT=? where MaHDT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, hdt.getMaCT());
			stmt.setString(2, hdt.getTenHDT());
			stmt.setString(3, hdt.getMaHDT());
			if(stmt.executeUpdate() > 0){
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

	public boolean deleteListHoiDongThi(List<String> listMaHDT) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaHDT.size() - 1; i++) {
			str += listMaHDT.get(i) + "','";
		}
		str += listMaHDT.get(listMaHDT.size() - 1) + "')";
		String sql = "delete from HoiDongThi where MaHDT in " + str;
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

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaHDT,TenHDT from HoiDongThi";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			list.put(rs.getString(1), rs.getString(1) + " - " + rs.getString(2));
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
