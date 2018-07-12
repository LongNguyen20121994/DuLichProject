package model.dao.longnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.PhongThiThucTe;
import model.dao.khoi.ConnectDB;

public class PhongThiThucTeDAO {

	public List<PhongThiThucTe> getAll() {
		List<PhongThiThucTe> list = new ArrayList<PhongThiThucTe>();
		ConnectDB con = new ConnectDB();
		String sql = "select IDPhong,MaMon,MaPT,NgayGioThi from PhongThiThucTe";
		con.openConnection();
		try {
			PreparedStatement stmt = con.getConnect().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			PhongThiThucTe pt;
			while(rs.next()){
				pt = new PhongThiThucTe();
				pt.setIDPhong(rs.getString(1));
				pt.setMaMonThi(rs.getString(2));
				pt.setMaPT(rs.getString(3));
				pt.setNgayGioThi(rs.getDate(4));
				list.add(pt);
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

	public PhongThiThucTe getInfo(String iDPhong) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select IDPhong,MaMon,MaPT,NgayGioThi from PhongThiThucTe where IDPhong=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, iDPhong);
    		ResultSet rs = stmt.executeQuery();
    		PhongThiThucTe pt = null;
    		if(rs.next()){
    			pt = new PhongThiThucTe();
    			pt.setIDPhong(rs.getString(1));
    			pt.setMaMonThi(rs.getString(2));
    			pt.setMaPT(rs.getString(3));
    			pt.setNgayGioThi(rs.getDate(4));
    		} 
        	stmt.close();
        	return pt;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}

	public boolean updatePhongThiThucTe(PhongThiThucTe pt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update PhongThiThucTe set MaMon=?, MaPT=?, NgayGioThi=? where IDPhong=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, pt.getMaMonThi());
			stmt.setString(2, pt.getMaPT());
			stmt.setDate(3, pt.getNgayGioThi());
			stmt.setString(4, pt.getIDPhong());
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

	public boolean insertPhongThiThucTe(PhongThiThucTe pt) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select IDPhong from PhongThiThucTe where IDPhong=?";
		String sql = "insert into PhongThiThucTe(IDPhong,MaMon,MaPT,NgayGioThi) values(?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, pt.getIDPhong());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, pt.getIDPhong());
    			stmt.setString(2, pt.getMaMonThi());
    			stmt.setString(3, pt.getMaPT());
    			stmt.setDate(4, pt.getNgayGioThi());
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

	public boolean deleteListPhongThiThucTe(List<String> listIDPhong) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listIDPhong.size() - 1; i++) {
			str += listIDPhong.get(i) + "','";
		}
		str += listIDPhong.get(listIDPhong.size() - 1) + "')";
		String sql = "delete from PhongThiThucTe where IDPhong in " + str;
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
