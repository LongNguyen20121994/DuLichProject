package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.NganhDHCD;

public class NganhDHCDDAO {

	public boolean addListNganhDHCD(List<NganhDHCD> list) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlInsert = "insert into Nganh_DHCD(MaNganh,MaTruong,DaoTao,ChiTieu,GhiChu) values (?,?,?,?,?)";
		String sqlUpdate = "update Nganh_DHCD set ChiTieu=?,GhiChu=? where MaNganh=? and MaTruong=? and DaoTao=?";
		String sqlCheck = "select MaNganh from Nganh_DHCD where MaNganh=? and MaTruong=? and DaoTao=?";
		PreparedStatement stmtc = null;
		PreparedStatement stmte = null;
		try {
			con.getConnect().setAutoCommit(false);
			for (NganhDHCD tr : list) {
				stmtc = con.getConnect().prepareStatement(sqlCheck);
				stmtc.setString(1, tr.getMaNganh());
				stmtc.setString(2, tr.getMaTruong());
				stmtc.setString(3, tr.getDaoTao());
				if (stmtc.executeQuery().next()) {
					stmte = con.getConnect().prepareStatement(sqlUpdate);
					stmte.setInt(1, tr.getChiTieu());
					stmte.setString(2, tr.getGhiChu());
					stmte.setString(3, tr.getMaNganh());
					stmte.setString(4, tr.getMaTruong());
					stmte.setString(5, tr.getDaoTao());
				} else {
					stmte = con.getConnect().prepareStatement(sqlInsert);
					stmte.setString(1, tr.getMaNganh());
					stmte.setString(2, tr.getMaTruong());
					stmte.setString(3, tr.getDaoTao());
					stmte.setInt(4, tr.getChiTieu());
					stmte.setString(5, tr.getGhiChu());
				}
				stmte.executeUpdate();
			}
			con.getConnect().commit();
			stmtc.close();
			stmte.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			try {
				con.getConnect().rollback();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
			return false;
		} finally {
			try {
				con.getConnect().setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			con.closeConnection();
		}
	}

	public boolean insertNganhDHCD(NganhDHCD nganh) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlInsert = "insert into Nganh_DHCD(MaNganh,MaTruong,DaoTao,ChiTieu,GhiChu) values (?,?,?,?,?)";
		String sqlCheck = "select MaNganh from Nganh_DHCD where MaNganh=? and MaTruong=? and DaoTao=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sqlCheck);
			stmt.setString(1, nganh.getMaNganh());
			stmt.setString(2, nganh.getMaTruong());
			stmt.setString(3, nganh.getDaoTao());
			if (!stmt.executeQuery().next()) {
				stmt = con.getConnect().prepareStatement(sqlInsert);
				stmt.setString(1, nganh.getMaNganh());
				stmt.setString(2, nganh.getMaTruong());
				stmt.setString(3, nganh.getDaoTao());
				stmt.setInt(4, nganh.getChiTieu());
				stmt.setString(5, nganh.getGhiChu());
			}
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}

	public HashMap<String, String> getAllTruongSelect(String maTruong) {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select Nganh_DHCD.MaNganh,TenNganh,DaoTao from Nganh_DHCD inner join Nganh "
				+ "on Nganh_DHCD.MaNganh = Nganh.MaNganh where MaTruong=? order by(Nganh_DHCD.MaNganh)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTruong);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			list.put(rs.getString(1) + "-" + rs.getString(3), rs.getString(2) + "(" + rs.getString(3) + ")");
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

	public List<NganhDHCD> getAllByMaTruong(String maTruong) {
		List<NganhDHCD> list = new ArrayList<NganhDHCD>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select Nganh_DHCD.MaNganh,TenNganh,DaoTao,ChiTieu from Nganh_DHCD inner join Nganh " +
				"on Nganh_DHCD.MaNganh = Nganh.MaNganh where MaTruong=? order by(Nganh_DHCD.MaNganh)";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTruong);
    		ResultSet rs = stmt.executeQuery();
    		NganhDHCD ng;
    		while(rs.next()){
    			ng = new NganhDHCD();
    			ng.setMaNganh(rs.getString(1));
    			ng.setGhiChu(rs.getString(2));
    			ng.setDaoTao(rs.getString(3));
    			ng.setChiTieu(rs.getInt(4));
    			list.add(ng);
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

	public boolean deleteListNganhByMa(String[] listMaNganh, String maTruong) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlDelXetTuyen = "delete from XetTuyen where MaNganh=? and MaTruong=? and DaoTao=?";
		String sqlDelKhoiThiNganh = "delete from KhoiThi_NganhDHCD where MaNganh=? and MaTruong=? and DaoTao=?";
		String sqlDelNganhDHCD = "delete from Nganh_DHCD where MaNganh=? and MaTruong=? and DaoTao=?";
		String sqlCheck = "select * from ChiTietHoSo where MaNganh=? and MaTruong=? and DaoTao=?";
		PreparedStatement stmtCheck = null;
		PreparedStatement stmtDel = null;
		try {
			con.getConnect().setAutoCommit(false);
			for(String tmp : listMaNganh){
				String ma[] = tmp.split("-");
				stmtCheck = con.getConnect().prepareStatement(sqlCheck);
				stmtCheck.setString(1, ma[0]);
				stmtCheck.setString(2, maTruong);
				stmtCheck.setString(3, ma[1]);
				if (!stmtCheck.executeQuery().next()) {
					//Xóa Xét tuyển
					stmtDel = con.getConnect().prepareStatement(sqlDelXetTuyen);
					stmtDel.setString(1, ma[0]);
					stmtDel.setString(2, maTruong);
					stmtDel.setString(3, ma[1]);
					stmtDel.executeUpdate();
					stmtDel.close();
					//Xóa Khối thi - ngành ĐHCĐ
					stmtDel = con.getConnect().prepareStatement(sqlDelKhoiThiNganh);
					stmtDel.setString(1, ma[0]);
					stmtDel.setString(2, maTruong);
					stmtDel.setString(3, ma[1]);
					stmtDel.executeUpdate();
					stmtDel.close();
					//Xóa Ngành
					stmtDel = con.getConnect().prepareStatement(sqlDelNganhDHCD);
					stmtDel.setString(1, ma[0]);
					stmtDel.setString(2, maTruong);
					stmtDel.setString(3, ma[1]);
					stmtDel.executeUpdate();
					stmtDel.close();
				}
			}
			if(stmtCheck != null){
				stmtCheck.close();
			}
			con.getConnect().commit();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			try {
				con.getConnect().setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con.closeConnection();
		}
	}

}
