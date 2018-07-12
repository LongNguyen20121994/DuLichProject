package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.MonThi;

public class MonThiDAO {

	public boolean insertMonThi(MonThi mt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaMon from MonThi where MaMon=?";
		String sql = "insert into MonThi(MaMon,TenMonThi) values (?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, mt.getMaMonThi());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, mt.getMaMonThi());
    			stmt.setString(2, mt.getTenMonThi());
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

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaMon,TenMonThi from MonThi";
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

	public List<MonThi> getAll() {
		List<MonThi> list = new ArrayList<MonThi>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaMon,TenMonThi from MonThi";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		MonThi mt;
    		while(rs.next()){
    			mt = new MonThi();
    			mt.setMaMonThi(rs.getString(1));
    			mt.setTenMonThi(rs.getString(2));
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

	public boolean deleteListMonThi(List<String> listMaMT) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaMT.size() - 1; i++) {
			str += listMaMT.get(i) + "','";
		}
		str += listMaMT.get(listMaMT.size() - 1) + "')";
		String sql = "delete from MonThi where MaMon in " + str;
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

	public boolean updateMonThi(MonThi mt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update MonThi set TenMonThi=? where MaMon=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, mt.getTenMonThi());
    		stmt.setString(2, mt.getMaMonThi());
    		if(stmt.executeUpdate() > 0) {
    			return true;
    		}
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.closeConnection();
        }
		return false;
	}

}
