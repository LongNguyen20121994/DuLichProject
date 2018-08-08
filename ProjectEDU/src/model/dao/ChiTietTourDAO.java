package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.DLChiTietTour;
import model.dao.khoi.ConnectDB;

public class ChiTietTourDAO {
	
	public boolean insertChiTietTour(DLChiTietTour ctTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into ChiTietTour(MaChiTietTour,MaTour,MaKS,NgayKhoiHanh,DacDiem,GiaVeNguoiLon,SoCho,SoChoDaDat) "+
		" values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ctTour.getMaChiTietTour());
			stmt.setString(2, ctTour.getMaTour());
			stmt.setString(3, ctTour.getMaKS());
			stmt.setDate(4, ctTour.getNgayKhoiHanh());
			stmt.setString(5, ctTour.getDacDiem());
			stmt.setString(6, ctTour.getGiaVeNguoiLon());
			stmt.setInt(7, ctTour.getSoCho());
			stmt.setInt(8, ctTour.getSoCho());
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

	public String getMaxRecord() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaChiTietTour from ChiTietTour ORDER BY MaChiTietTour DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLChiTietTour tur = null;
    		if(rs.next()){
    			tur = new DLChiTietTour();
    			tur.setMaChiTietTour(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaChiTietTour();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public boolean updateChiTietTour(DLChiTietTour ctTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update ChiTietTour set MaTour=?,MaKS=?,NgayKhoiHanh=?,DacDiem=?,GiaVeNguoiLon=?,SoCho=?,SoChoDaDat=? where MaChiTietTour=?";
        PreparedStatement stmt = null;
        try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, ctTour.getMaTour());
			stmt.setString(2, ctTour.getMaKS());
			stmt.setDate(3, ctTour.getNgayKhoiHanh());
			stmt.setString(4, ctTour.getDacDiem());
			stmt.setString(5, ctTour.getGiaVeNguoiLon());
			stmt.setInt(6, ctTour.getSoCho());
			stmt.setInt(7, ctTour.getSoCho());
			stmt.setString(8, ctTour.getMaChiTietTour());
			int check = stmt.executeUpdate();
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

	public boolean deleteListLichTrinh(List<String> listMaCTTour) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaCTTour.size() - 1; i++) {
			str += listMaCTTour.get(i) + "','";
		}
		str += listMaCTTour.get(listMaCTTour.size() - 1) + "')";
		String sql = "delete from ChiTietTour where MaChiTietTour in " + str;
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
	
	public List<DLChiTietTour> getAll() {
		List<DLChiTietTour> list = new ArrayList<DLChiTietTour>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaChiTietTour,MaTour,MaKS,NgayKhoiHanh,DacDiem,GiaVeNguoiLon,SoCho from ChiTietTour";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLChiTietTour mt;
    		while(rs.next()){
    			mt = new DLChiTietTour();
    			mt.setMaChiTietTour(rs.getString(1));
    			mt.setMaTour(rs.getString(2));
    			mt.setMaKS(rs.getString(3));
    			mt.setNgayKhoiHanh(rs.getDate(4));
    			mt.setDacDiem(rs.getString(5));
    			mt.setGiaVeNguoiLon(rs.getDouble(6));
    			mt.setSoCho(rs.getInt(7));
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
	
	public List<DLChiTietTour> getTheoMaTour(String maTour) {
		List<DLChiTietTour> list = new ArrayList<DLChiTietTour>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaChiTietTour,MaTour,MaKS,NgayKhoiHanh,DacDiem,GiaVeNguoiLon,SoCho from ChiTietTour where MaTour = ?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTour);
    		ResultSet rs = stmt.executeQuery();
    		DLChiTietTour mt;
    		while(rs.next()){
    			mt = new DLChiTietTour();
    			mt.setMaChiTietTour(rs.getString(1));
    			mt.setMaTour(rs.getString(2));
    			mt.setMaKS(rs.getString(3));
    			mt.setNgayKhoiHanh(rs.getDate(4));
    			mt.setDacDiem(rs.getString(5));
    			mt.setGiaVeNguoiLon(rs.getDouble(6));
    			mt.setSoCho(rs.getInt(7));
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
	
	public DLChiTietTour getInfo(String maTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaChiTietTour,MaTour,MaKS,NgayKhoiHanh,DacDiem,GiaVeNguoiLon,SoCho,SoChoDaDat "
				+ "from ChiTietTour where MaTour=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTour);
    		ResultSet rs = stmt.executeQuery();
            DLChiTietTour tur = null;
    		if(rs.next()){
    			tur = new DLChiTietTour();
    			tur.setMaChiTietTour(rs.getString(1));
    			tur.setMaTour(rs.getString(2));
    			tur.setMaKS(rs.getString(3));
    			tur.setNgayKhoiHanh(rs.getDate(4));
    			tur.setDacDiem(rs.getString(5));
    			tur.setGiaVeNguoiLon(rs.getDouble(6));
    			tur.setSoCho(rs.getInt(7));
    			tur.setSoChoDaDat(rs.getInt(8));
    		}
        	stmt.close();
            return tur;
        } catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}
	public DLChiTietTour getInfoGanNhat(String maTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select top 1 MaChiTietTour,MaTour,MaKS,NgayKhoiHanh,DacDiem,GiaVeNguoiLon,SoCho,SoChoDaDat "
				+ "from ChiTietTour where MaTour=? and NgayKhoiHanh > ?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTour);
    		long millis=System.currentTimeMillis();  
    		java.sql.Date date=new java.sql.Date(millis);
    		stmt.setDate(2, date);
    		ResultSet rs = stmt.executeQuery();
            DLChiTietTour tur = null;
    		if(rs.next()){
    			tur = new DLChiTietTour();
    			tur.setMaChiTietTour(rs.getString(1));
    			tur.setMaTour(rs.getString(2));
    			tur.setMaKS(rs.getString(3));
    			tur.setNgayKhoiHanh(rs.getDate(4));
    			tur.setDacDiem(rs.getString(5));
    			tur.setGiaVeNguoiLon(rs.getDouble(6));
    			tur.setSoCho(rs.getInt(7));
    			tur.setSoChoDaDat(rs.getInt(8));
    		}
        	stmt.close();
            return tur;
        } catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
	}
}
