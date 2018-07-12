package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.DoiTuongUT;

public class DoiTuongUTDAO {

	public List<DoiTuongUT> getAll() {
		List<DoiTuongUT> list = new ArrayList<DoiTuongUT>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaDT,TenDT,DiemCong,GhiChu from DoiTuongUT";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DoiTuongUT dt;
    		while(rs.next()){
    			dt = new DoiTuongUT();
    			dt.setMaDT(rs.getString(1));
    			dt.setTenDT(rs.getString(2));
    			dt.setDiemCong(rs.getDouble(3));
    			dt.setGhiChu(rs.getString(4));
    			list.add(dt);
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

	public boolean updateDoiTuongUT(DoiTuongUT dt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update DoiTuongUT set TenDT=?,DiemCong=?,GhiChu=? where MaDT=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, dt.getTenDT());
    		stmt.setDouble(2, dt.getDiemCong());
    		stmt.setString(3, dt.getGhiChu());
    		stmt.setString(4, dt.getMaDT());
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

	public boolean insertDoiTuongUT(DoiTuongUT dt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into DoiTuongUT(MaDT,TenDT,DiemCong,GhiChu) values(?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, dt.getMaDT());
    		stmt.setString(2, dt.getTenDT());
    		stmt.setDouble(3, dt.getDiemCong());
    		stmt.setString(4, dt.getGhiChu());
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

	public boolean deleteListDoiTuongUT(List<String> listMaDT) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaDT.size() - 1; i++) {
			str += listMaDT.get(i) + "','";
		}
		str += listMaDT.get(listMaDT.size() - 1) + "')";
		String sql = "delete from DoiTuongUT where MaDT in " + str;
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
