package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import model.bean.InfoNganhDHCD;
import model.bean.TruongDHCD;

public class TruongDHCDDAO {
	public boolean addListTruongDHCD(List<TruongDHCD> list) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		
		String sqlInsert = "insert into TruongDH_CD(MaTruong,TenTruong,DiaChi,GhiChu) values (?,?,?,?)";
		String sqlUpdate = "update TruongDH_CD set TenTruong=?,DiaChi=?,GhiChu=? where MaTruong=?";
		String sqlCheck = "select MaTruong from TruongDH_CD where MaTruong=?";
        PreparedStatement stmtc = null;
        PreparedStatement stmte = null;
        try {
        	for(TruongDHCD tr : list){
        		stmtc = con.getConnect().prepareStatement(sqlCheck);
        		stmtc.setString(1, tr.getMaTruong());
        		if(stmtc.executeQuery().next()){
        			stmte = con.getConnect().prepareStatement(sqlUpdate);
        			stmte.setString(1, tr.getTenTruong());
        			stmte.setString(2, tr.getDiaChi());
        			stmte.setString(3, tr.getGhiChu());
        			stmte.setString(4, tr.getMaTruong());
        		} else {
        			stmte = con.getConnect().prepareStatement(sqlInsert);
        			stmte.setString(1, tr.getMaTruong());
        			stmte.setString(2, tr.getTenTruong());
        			stmte.setString(3, tr.getDiaChi());
        			stmte.setString(4, tr.getGhiChu());
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
	}

	public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTruong,TenTruong from TruongDH_CD";
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

	public HashMap<String, String> getListDHCDSelect(String maTinh) {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTruong,TenTruong from TruongDH_CD where DiaChi=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTinh);
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

	public boolean insertTruongDHCD(TruongDHCD truongDHCD) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlCheck = "select MaTruong from TruongDH_CD where MaTruong=?";
		String sql = "insert into TruongDH_CD(MaTruong,TenTruong,DiaChi,GhiChu) values (?,?,?,?)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sqlCheck);
    		stmt.setString(1, truongDHCD.getMaTruong());
    		ResultSet rs = stmt.executeQuery();
    		int check = 0;
    		if(!rs.next()){
    			stmt = con.getConnect().prepareStatement(sql);
    			stmt.setString(1, truongDHCD.getMaTruong());
    			stmt.setString(2, truongDHCD.getTenTruong());
    			stmt.setString(3, truongDHCD.getDiaChi());
    			stmt.setString(4, truongDHCD.getGhiChu());
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

	public TruongDHCD getInfo(String maTruong) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTruong,TenTruong,TenTinh,GhiChu from TruongDH_CD as tr "
				+ "inner join Tinh_ThanhPho as t on tr.DiaChi=t.MaTinh where MaTruong=?";
        PreparedStatement stmt = null;
		TruongDHCD tr = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTruong);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			tr = new TruongDHCD();
    			tr.setMaTruong(maTruong);
    			tr.setTenTruong(rs.getString(2));
    			tr.setDiaChi(rs.getString(3));
    			tr.setGhiChu(rs.getString(4));
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.closeConnection();
        }
        return tr;
	}

	public List<InfoNganhDHCD> getListNganhDHCDByMaTruong(String maTruong) {
		List<InfoNganhDHCD> list = new ArrayList<InfoNganhDHCD>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select n1.MaNganh,TenNganh,n1.DaoTao,MaKhoi,ChiTieu,GhiChu "
				+ "from Nganh_DHCD as n1 inner join Nganh as n2 on n1.MaNganh=n2.MaNganh "
				+ "inner join KhoiThi_NganhDHCD as n3 on n1.MaNganh = n3.MaNganh and n1.DaoTao=n3.DaoTao " 
				+ "and n1.MaTruong=n3.MaTruong where n1.MaTruong=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTruong);
    		ResultSet rs = stmt.executeQuery();
    		InfoNganhDHCD info;
    		while(rs.next()){
    			info = new InfoNganhDHCD();
    			info.setMaNganh(rs.getString(1));
    			info.setTenNganh(rs.getString(2));
    			info.setDaoTao(rs.getString(3));
    			info.setMaKhoi(rs.getString(4));
    			info.setChiTieu(rs.getInt(5));
    			info.setGhiChu(rs.getString(6));
    			list.add(info);
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

	public List<TruongDHCD> getAllByMaTinh(String maTinh) {
		List<TruongDHCD> list = new ArrayList<TruongDHCD>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select tr.MaTruong,TenTruong,TenTinh,SUM(ChiTieu) from TruongDH_CD as tr "
				+ "inner join Tinh_ThanhPho as t on tr.DiaChi=t.MaTinh "
				+ "left join Nganh_DHCD as ng on tr.MaTruong=ng.MaTruong where t.MaTinh=?"
				+ "group by tr.MaTruong,TenTruong,TenTinh";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTinh);
    		ResultSet rs = stmt.executeQuery();
    		TruongDHCD tr;
    		while(rs.next()){
    			tr = new TruongDHCD();
    			tr.setMaTruong(rs.getString(1));
    			tr.setTenTruong(rs.getString(2));
    			tr.setDiaChi(rs.getString(3));
    			tr.setGhiChu((StringUtils.isBlank(rs.getString(4))?"0":rs.getString(4)));
    			list.add(tr);
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
