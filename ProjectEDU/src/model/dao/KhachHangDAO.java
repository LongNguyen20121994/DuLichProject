package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.DLKhachHang;
import model.dao.khoi.ConnectDB;

public class KhachHangDAO {

	public String getMaxRecord() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKH from KhachHang ORDER BY MaKH DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLKhachHang tur = null;
    		if(rs.next()){
    			tur = new DLKhachHang();
    			tur.setMaKH(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaKH();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}

	public boolean insertKhachHang(DLKhachHang tour) {		
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into KhachHang(MaKH,HoTen,Email,SoDT,DiaChi,GhiChu,MatKhau) values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
        try {
        	stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, tour.getMaKH());
			stmt.setString(2, tour.getHoTen());
			stmt.setString(3, tour.getEmail());
			stmt.setString(4, tour.getSoDT());
			stmt.setString(5, tour.getDiaChi());
			stmt.setString(6, tour.getGhiChu());
			stmt.setString(7, "123456");
			if (stmt.executeUpdate() > 0) {
				stmt.close();
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
	
	/*public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTinh,TenTinh from Tinh";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			list.put(rs.getString(1), rs.getString(2));
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

	public boolean addListTinh(List<DLTinh> listTinh) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		
		String sqlInsert = "insert into Tinh(MaTinh,TenTinh) values (?,?)";
		String sqlUpdate = "update Tinh set TenTinh=? where MaTinh=?";
		String sqlCheck = "select MaTinh from Tinh where MaTinh=?";
        PreparedStatement stmtc = null;
        PreparedStatement stmte = null;
        try {
        	for(DLTinh ttp : listTinh){
        		stmtc = con.getConnect().prepareStatement(sqlCheck);
        		stmtc.setString(1, ttp.getMaTinh());
        		if(stmtc.executeQuery().next()){
        			stmte = con.getConnect().prepareStatement(sqlUpdate);
        			stmte.setString(1, ttp.getTenTinh());
        			stmte.setString(2, ttp.getMaTinh());
        		} else {
        			stmte = con.getConnect().prepareStatement(sqlInsert);
        			stmte.setString(1, ttp.getMaTinh());
        			stmte.setString(2, ttp.getTenTinh());
        		}
        		stmte.executeUpdate();
        	}
        	stmtc.close();
        	stmte.close();
        	return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
	}*/
}
