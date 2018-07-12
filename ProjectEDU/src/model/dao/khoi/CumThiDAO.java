package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.CumThi;

public class CumThiDAO {
	
	public boolean insertCumThi(CumThi ct) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaCT from CumThi where MaCT=?";
		String sql = "insert into CumThi(MaCT,TenCumThi) values(?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, ct.getMaCT());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, ct.getMaCT());
    			stmt.setString(2, ct.getTenCumThi());
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
	
	public CumThi getInfo(String maCT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaCT,TenCumThi from CumThi where MaCT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maCT);
    		ResultSet rs = stmt.executeQuery();
    		CumThi ct = null;
    		if(rs.next()){
    			ct = new CumThi();
    			ct.setMaCT(rs.getString(1));
    			ct.setTenCumThi(rs.getString(2));
    		} 
        	stmt.close();
        	return ct;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public List<CumThi> getAll() {
		List<CumThi> list = new ArrayList<CumThi>();
		ConnectDB con = new ConnectDB();
		String sql = "select MaCT, TenCumThi from CumThi";
		con.openConnection();
		try {
			PreparedStatement stmt = con.getConnect().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			CumThi ct;
			while(rs.next()){
				ct = new CumThi();
				ct.setMaCT(rs.getString(1));
				ct.setTenCumThi(rs.getString(2));
				list.add(ct);
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

	public boolean updateCumThi(CumThi ct) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update CumThi set TenCumThi=? where MaCT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ct.getTenCumThi());
			stmt.setString(2, ct.getMaCT());
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
	
	public boolean deleteListCumThi(List<String> listCT) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listCT.size() - 1; i++) {
			str += listCT.get(i) + "','";
		}
		str += listCT.get(listCT.size() - 1) + "')";
		String sql = "delete from CumThi where MaCT in " + str;
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
}
