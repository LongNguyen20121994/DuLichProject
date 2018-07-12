package model.dao.khoi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.bean.KhoiThi;
import model.bean.KhoiThiNganhDHCD;

public class KhoiThiNganhDHCDDAO {

	public boolean insertKhoiThiNganhDHCD(KhoiThiNganhDHCD ktn) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlInsert = "insert into KhoiThi_NganhDHCD(MaNganh,MaTruong,DaoTao,MaKhoi,NamTuyenSinh) values (?,?,?,?,?)";
		String sqlCheck = "select * from KhoiThi_NganhDHCD where MaNganh=? and MaTruong=? and DaoTao=? and MaKhoi=? and NamTuyenSinh=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sqlCheck);
			stmt.setString(1, ktn.getMaNganh());
			stmt.setString(2, ktn.getMaTruong());
			stmt.setString(3, ktn.getDaoTao());
			stmt.setString(4, ktn.getMaKhoi());
			stmt.setInt(5, ktn.getNamTuyenSinh());
			if (!stmt.executeQuery().next()) {
				stmt = con.getConnect().prepareStatement(sqlInsert);
				stmt.setString(1, ktn.getMaNganh());
				stmt.setString(2, ktn.getMaTruong());
				stmt.setString(3, ktn.getDaoTao());
				stmt.setString(4, ktn.getMaKhoi());
				stmt.setInt(5, ktn.getNamTuyenSinh());
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

	public boolean addListKhoiThiNganhDHCD(List<KhoiThiNganhDHCD> list) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sqlInsert = "insert into KhoiThi_NganhDHCD(MaNganh,MaTruong,DaoTao,MaKhoi,NamTuyenSinh) values (?,?,?,?,?)";
		String sqlCheck = "select * from KhoiThi_NganhDHCD where MaNganh=? and MaTruong=? and DaoTao=? and MaKhoi=? and NamTuyenSinh=?";
		PreparedStatement stmt = null;
		try {
			con.getConnect().setAutoCommit(false);
			for (KhoiThiNganhDHCD ktn : list) {
				stmt = con.getConnect().prepareStatement(sqlCheck);
				stmt.setString(1, ktn.getMaNganh());
				stmt.setString(2, ktn.getMaTruong());
				stmt.setString(3, ktn.getDaoTao());
				stmt.setString(4, ktn.getMaKhoi());
				stmt.setInt(5, ktn.getNamTuyenSinh());
				if (!stmt.executeQuery().next()) {
					stmt = con.getConnect().prepareStatement(sqlInsert);
					stmt.setString(1, ktn.getMaNganh());
					stmt.setString(2, ktn.getMaTruong());
					stmt.setString(3, ktn.getDaoTao());
					stmt.setString(4, ktn.getMaKhoi());
					stmt.setInt(5, ktn.getNamTuyenSinh());
					stmt.executeUpdate();
				} 
			}
			con.getConnect().commit();
			stmt.close();
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

	public ArrayList<KhoiThi> getAllKhoiThi(KhoiThiNganhDHCD ktn) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		ArrayList<KhoiThi> arr = new ArrayList<KhoiThi>();
		String sql = "select kt.MaKhoi, TenKhoi,DiemChuan from KhoiThi_NganhDHCD as ktn inner join KhoiThi as kt on "
				+ "ktn.MaKhoi = kt.MaKhoi where MaNganh=? and MaTruong=? and DaoTao=? and NamTuyenSinh=?";
		String sqlMon = "select TenMonThi from ChiTietKhoiThi as ct inner join MonThi as mt "
				+ "on ct.MaMon=mt.MaMon where MaKhoi=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ktn.getMaNganh());
			stmt.setString(2, ktn.getMaTruong());
			stmt.setString(3, ktn.getDaoTao());
			stmt.setInt(4, ktn.getNamTuyenSinh());
			ResultSet rs = stmt.executeQuery();
			KhoiThi kt;
			while (rs.next()) {
				kt = new KhoiThi();
				kt.setMaKhoi(rs.getString(1));
				StringBuffer tenMT = new StringBuffer(rs.getString(2) + " (");
				PreparedStatement stmtKT = con.getConnect().prepareStatement(sqlMon);
				stmtKT.setString(1, kt.getMaKhoi());
				ResultSet rsMon = stmtKT.executeQuery();
				int i = 1;
				while(rsMon.next()){
					if(i==1){
						tenMT.append(rsMon.getString(1));
					} else {
						tenMT.append(" - ");
						tenMT.append(rsMon.getString(1));
					}
					i++;
				}
				kt.setTenKhoi(tenMT.append(")").toString());
				kt.setGhiChu(String.format("%.2f",rs.getDouble(3)));
				arr.add(kt);
			} 
			stmt.close();
			return arr;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}
	
	public ArrayList<KhoiThi> getAllKhoiThiNganh(KhoiThiNganhDHCD ktn) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		ArrayList<KhoiThi> arr = new ArrayList<KhoiThi>();
		String sql = "select kt.MaKhoi, TenKhoi from KhoiThi_NganhDHCD as ktn inner join KhoiThi as kt on "
				+ "ktn.MaKhoi = kt.MaKhoi where MaNganh=? and MaTruong=? and DaoTao=? and NamTuyenSinh=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ktn.getMaNganh());
			stmt.setString(2, ktn.getMaTruong());
			stmt.setString(3, ktn.getDaoTao());
			stmt.setInt(4, ktn.getNamTuyenSinh());
			ResultSet rs = stmt.executeQuery();
			KhoiThi kt;
			while (rs.next()) {
				kt = new KhoiThi();
				kt.setMaKhoi(rs.getString(1));
				kt.setTenKhoi(rs.getString(2));
				arr.add(kt);
			} 
			stmt.close();
			return arr;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean validKhoiThi(KhoiThiNganhDHCD ktn) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKhoi from KhoiThi_NganhDHCD where MaNganh=? and MaTruong=? and DaoTao=? and MaKhoi=? and NamTuyenSinh=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ktn.getMaNganh());
			stmt.setString(2, ktn.getMaTruong());
			stmt.setString(3, ktn.getDaoTao());
			stmt.setString(4, ktn.getMaKhoi());
			stmt.setInt(5, ktn.getNamTuyenSinh());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			} 
			stmt.close();
			return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}

	public boolean deleteListKhoiThi(String[] listKT, KhoiThiNganhDHCD ktn) {
		String str = "('";
        for (int i = 0; i < listKT.length - 1; i++) {
            str += listKT[i] + "','";
        }
        str += listKT[listKT.length - 1] + "')";
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "delete from KhoiThi_NganhDHCD where MaNganh=? and MaTruong=? and DaoTao=? and NamTuyenSinh=? and MaKhoi in " + str;
		String sqlXT = "delete from XetTuyen where MaNganh=? and MaTruong=? and DaoTao=? and NamTuyenSinh=? and MaKhoi in " + str;
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sqlXT);
			stmt.setString(1, ktn.getMaNganh());
			stmt.setString(2, ktn.getMaTruong());
			stmt.setString(3, ktn.getDaoTao());
			stmt.setInt(4, ktn.getNamTuyenSinh());
			stmt.executeUpdate();
			stmt.close();
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ktn.getMaNganh());
			stmt.setString(2, ktn.getMaTruong());
			stmt.setString(3, ktn.getDaoTao());
			stmt.setInt(4, ktn.getNamTuyenSinh());
			if (stmt.executeUpdate() > 0) {
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
	
	public static void main(String[] args){
		KhoiThiNganhDHCD kt = new KhoiThiNganhDHCD();
		kt.setMaNganh("140101");
		kt.setMaTruong("DDK");
		kt.setDaoTao("DHCQ");
		kt.setNamTuyenSinh(2016);
		List<KhoiThi> list = new KhoiThiNganhDHCDDAO().getAllKhoiThiNganh(kt);
		List<KhoiThi> result = list.stream().filter(p -> p.getMaKhoi().equals("A")).collect(Collectors.toList());
		for(KhoiThi k : result) System.out.println(k.getTenKhoi());
	}

	public ArrayList<KhoiThiNganhDHCD> getAllKhoiThiNganhDHCD(String maTruong) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		ArrayList<KhoiThiNganhDHCD> arr = new ArrayList<KhoiThiNganhDHCD>();
		String sql = "select kt.MaNganh,TenNganh,DaoTao,MaKhoi,NamTuyenSinh,DiemChuan from KhoiThi_NganhDHCD as kt "
				+ "inner join Nganh as n on kt.MaNganh=n.MaNganh where MaTruong=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, maTruong);
			ResultSet rs = stmt.executeQuery();
			KhoiThiNganhDHCD kt;
			while (rs.next()) {
				kt = new KhoiThiNganhDHCD();
				kt.setMaNganh(rs.getString(1));
				kt.setMaTruong(rs.getString(2));
				kt.setDaoTao(rs.getString(3));
				kt.setMaKhoi(rs.getString(4));
				kt.setNamTuyenSinh(rs.getInt(5));
				kt.setDiemChuan(rs.getDouble(6));
				arr.add(kt);
			} 
			stmt.close();
			return arr;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}

	public boolean updateDiemChuan(List<KhoiThiNganhDHCD> listKTN) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update KhoiThi_NganhDHCD set DiemChuan=? where MaNganh=? and MaTruong=? and DaoTao=? and MaKhoi=? and NamTuyenSinh=?";
		PreparedStatement stmt = null;
		try {
			con.getConnect().setAutoCommit(false);
			for (KhoiThiNganhDHCD ktn : listKTN) {
				stmt = con.getConnect().prepareStatement(sql);
				stmt.setDouble(1, ktn.getDiemChuan());
				stmt.setString(2, ktn.getMaNganh());
				stmt.setString(3, ktn.getMaTruong());
				stmt.setString(4, ktn.getDaoTao());
				stmt.setString(5, ktn.getMaKhoi());
				stmt.setInt(6, ktn.getNamTuyenSinh());
				stmt.executeUpdate();
			}
			con.getConnect().commit();
			stmt.close();
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
}
