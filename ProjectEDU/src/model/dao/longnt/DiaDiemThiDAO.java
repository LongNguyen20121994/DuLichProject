package model.dao.longnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.DiaDiemThi;
import model.dao.khoi.ConnectDB;

public class DiaDiemThiDAO {
	public DiaDiemThi getInfo(String maDDT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaDDT,MaHDT,TenDDT,DiaChi from DiaDiemThi where MaDDT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maDDT);
    		ResultSet rs = stmt.executeQuery();
    		DiaDiemThi ddt = null;
    		if(rs.next()){
    			ddt = new DiaDiemThi();
    			ddt.setMaDDT(rs.getString(1));
    			ddt.setMaHDT(rs.getString(2));
    			ddt.setTenDDT(rs.getString(3));
    			ddt.setDiaChi(rs.getString(4));
    		} 
        	stmt.close();
        	return ddt;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}

	public boolean insertDiaDiemThi(DiaDiemThi ddt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaDDT from DiaDiemThi where MaDDT=?";
		String sql = "insert into DiaDiemThi(MaDDT,MaHDT,TenDDT,DiaChi) values(?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, ddt.getMaDDT());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, ddt.getMaDDT());
    			stmt.setString(2, ddt.getMaHDT());
    			stmt.setString(3, ddt.getTenDDT());
    			stmt.setString(4, ddt.getDiaChi());
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

	public List<DiaDiemThi> getAll() {
		List<DiaDiemThi> list = new ArrayList<DiaDiemThi>();
		ConnectDB con = new ConnectDB();
		String sql = "select MaDDT,MaHDT,TenDDT,DiaChi from DiaDiemThi";
		con.openConnection();
		try {
			PreparedStatement stmt = con.getConnect().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DiaDiemThi ddt;
			while(rs.next()){
				ddt = new DiaDiemThi();
				ddt.setMaDDT(rs.getString(1));
				ddt.setMaHDT(rs.getString(2));
				ddt.setTenDDT(rs.getString(3));
				ddt.setDiaChi(rs.getString(4));
				list.add(ddt);
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

	public boolean updateDiaDiemThi(DiaDiemThi ddt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update DiaDiemThi set MaHDT=?, TenDDT=?, DiaChi=? where MaDDT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ddt.getMaHDT());
			stmt.setString(2, ddt.getTenDDT());
			stmt.setString(3, ddt.getDiaChi());
			stmt.setString(4, ddt.getMaDDT());
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

	public boolean deleteListDiaDiemThi(List<String> listMaDDT) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaDDT.size() - 1; i++) {
			str += listMaDDT.get(i) + "','";
		}
		str += listMaDDT.get(listMaDDT.size() - 1) + "')";
		String sql = "delete from DiaDiemThi where MaDDT in " + str;
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
		String sql = "select MaDDT,TenDDT from DiaDiemThi";
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
