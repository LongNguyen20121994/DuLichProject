package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.DLChiTietDatTour;
import model.dao.khoi.ConnectDB;

public class ChiTietDatTourDAO {
	public String getMaxRecord() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaChiTietDatTour from ChiTietDatTour ORDER BY MaChiTietDatTour DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLChiTietDatTour tur = null;
    		if(rs.next()){
    			tur = new DLChiTietDatTour();
    			tur.setMaChiTietDatTour(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaChiTietDatTour();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	/*public HashMap<String, String> getAllSelect() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKS, TenKS from KhachSan";
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
	
	public HashMap<String, String> getAllByMaTinh(String maTinh) {
		List<DLKhachSan> list = new ArrayList<DLKhachSan>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql;
		//if(maTinh.equals("-1")) {
		if(maTinh == null) {
			sql = "select MaKS,TenKS from KhachSan";
		} else {
			sql = "select k.MaKS,TenKS from KhachSan k, Tinh t where k.MaTinh=t.MaTinh and t.MaTinh=?";
		}		
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		//if(!maTinh.equals("-1")) {
    		if(maTinh != null) {
    			stmt.setString(1, maTinh);
    		}    		
    		ResultSet rs = stmt.executeQuery();
    		DLKhachSan ks;
    		while(rs.next()){
    			ks = new DLKhachSan();
    			ks.setMaKS(rs.getString(1));
    			ks.setTenKS(rs.getString(2));
    			list.add(ks);
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
		return list;
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql;
		//if(maTinh.equals("-1")) {
		if(maTinh == null) {
			sql = "select MaKS,TenKS from KhachSan";
		} else {
			sql = "select k.MaKS,TenKS from KhachSan k, Tinh t where k.MaTinh=t.MaTinh and t.MaTinh=?";
		}
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		//if(!maTinh.equals("-1")) {
    		if(maTinh != null) {
    			stmt.setString(1, maTinh);
    		}
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

	public DLKhachSan getInfo(String maKS) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKS, TenKS, HinhAnh, MaTinh, DiaChi, Sao, MoTa "
				+ "from KhachSan where MaKS=?";
        PreparedStatement stmt = null;
        DLKhachSan ks = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maKS);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			ks = new DLKhachSan();
    			ks.setMaKS(maKS);
    			ks.setTenKS(rs.getString(2));
    			ks.setHinhAnh(rs.getString(3));
    			ks.setMaTinh(rs.getString(4));
    			ks.setDiaChi(rs.getString(5));
    			ks.setSao(rs.getInt(6));
    			ks.setMoTa(rs.getString(7));
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.closeConnection();
        }
        return ks;
	}

	public boolean insertKhachSan(DLKhachSan ks) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into KhachSan(MaKS,TenKS,HinhAnh,MaTinh,DiaChi,Sao,MoTa) values(?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ks.getMaKS());
			stmt.setString(2, ks.getTenKS());
			stmt.setString(3, ks.getHinhAnh());
			stmt.setString(4, ks.getMaTinh());
			stmt.setString(5, ks.getDiaChi());
			stmt.setInt(6, ks.getSao());
			stmt.setString(7, ks.getMoTa());
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

	public boolean updateKhachSan(DLKhachSan ks) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update KhachSan set TenKS=?, HinhAnh=?, MaTinh=?, DiaChi=?, Sao=?, MoTa=? where MaKS=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ks.getTenKS());
			stmt.setString(2, ks.getHinhAnh());
			stmt.setString(3, ks.getMaTinh());
			stmt.setString(4, ks.getDiaChi());
			stmt.setInt(5, ks.getSao());
			stmt.setString(6, ks.getMoTa());
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
	
	public boolean deleteKhachSan(List<String> listMaKS) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaKS.size() - 1; i++) {
			str += listMaKS.get(i) + "','";
		}
		str += listMaKS.get(listMaKS.size() - 1) + "')";
		String sql = "delete from KhachSan where MaKS in " + str;
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
	
	public boolean deleteListKhachSan(String[] listKS, String maTinh) {
		String str = "('";
        for (int i = 0; i < listKS.length - 1; i++) {
            str += listKS[i] + "','";
        }
        str += listKS[listKS.length - 1] + "')";
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "delete from KhachSan where MaTinh=? and MaKS in " + str;
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, maTinh);
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
	
	public List<DLKhachSan> getAll() {
		List<DLKhachSan> list = new ArrayList<DLKhachSan>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaKS,TenKS,HinhAnh,MaTinh,DiaChi,Sao,MoTa from KhachSan";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLKhachSan mt;
    		while(rs.next()){
    			mt = new DLKhachSan();
    			mt.setMaKS(rs.getString(1));
    			mt.setTenKS(rs.getString(2));
    			mt.setHinhAnh(rs.getString(3));
    			mt.setMaTinh(rs.getString(4));
    			mt.setDiaChi(rs.getString(5));
    			mt.setSao(rs.getInt(6));
    			mt.setMoTa(rs.getString(7));
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
	}*/
}