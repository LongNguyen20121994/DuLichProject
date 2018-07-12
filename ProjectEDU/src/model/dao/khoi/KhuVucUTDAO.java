package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.KhuVucUT;

public class KhuVucUTDAO {

	public List<KhuVucUT> getAllSelect() {
		List<KhuVucUT> list = new ArrayList<KhuVucUT>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKV,TenKhuVuc,GhiChu,DiemCong from KhuVucUT";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		KhuVucUT kv;
    		while(rs.next()){
    			kv = new KhuVucUT();
    			kv.setMaKV(rs.getString(1));
    			kv.setTenKhuVuc(rs.getString(2));
    			kv.setGhiChu(rs.getString(3));
    			kv.setDiemCong(rs.getDouble(4));
    			list.add(kv);
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

	public boolean updateKhuVucUT(KhuVucUT kv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update KhuVucUT set TenKhuVuc=?,DiemCong=?,GhiChu=? where MaKV=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, kv.getTenKhuVuc());
    		stmt.setDouble(2, kv.getDiemCong());
    		stmt.setString(3, kv.getGhiChu());
    		stmt.setString(4, kv.getMaKV());
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

	public boolean insertKhuVucUT(KhuVucUT kv) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into KhuVucUT(MaKV,TenKhuVuc,DiemCong,GhiChu) values(?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, kv.getMaKV());
    		stmt.setString(2, kv.getTenKhuVuc());
    		stmt.setDouble(3, kv.getDiemCong());
    		stmt.setString(4, kv.getGhiChu());
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

	public boolean deleteListKhuVucUT(List<String> listMaKV) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaKV.size() - 1; i++) {
			str += listMaKV.get(i) + "','";
		}
		str += listMaKV.get(listMaKV.size() - 1) + "')";
		String sql = "delete from KhuVucUT where MaKV in " + str;
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
