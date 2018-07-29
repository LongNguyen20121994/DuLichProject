package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.DLTour;
import model.dao.khoi.ConnectDB;

public class DLTourDAO {

	public String getMaxRecord() {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTour from Tour ORDER BY MaTour DESC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLTour tur = null;
    		if(rs.next()){
    			tur = new DLTour();
    			tur.setMaTour(rs.getString(1));
    		}else {
    			stmt.close();
    			return null;
    		}
        	stmt.close();
        	return tur.getMaTour();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public boolean insertTour(DLTour tour) {		
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert into Tour(MaTour,TieuDe,AnhDaiDien,MoTaTongQuan,LichTrinh,DiaDiemKhoiHanh,SoNgay,SoDem,GhiChu,MaLoai) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
        try {
        	stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, tour.getMaTour());
			stmt.setString(2, tour.getTieuDe());
			stmt.setString(3, tour.getHinhAnh());
			stmt.setString(4, tour.getMoTaTongQuan());
			stmt.setString(5, tour.getLichTrinh());
			stmt.setString(6, tour.getDiaDiemKhoiHanh());
			stmt.setInt(7, tour.getSoNgay());
			stmt.setInt(8, tour.getSoDem());
			stmt.setString(9, tour.getGhiChu());
			stmt.setString(10, tour.getMaLoai());
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
	
	public List<DLTour> getAll() {
		List<DLTour> list = new ArrayList<DLTour>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "SELECT top 4 MaTour,TieuDe,AnhDaiDien,SoNgay FROM Tour ORDER BY MaTour DESC";	
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);		
    		ResultSet rs = stmt.executeQuery();
    		DLTour tur;
    		while(rs.next()){
    			tur = new DLTour();
    			tur.setMaTour(rs.getString(1));
    			tur.setTieuDe(rs.getString(2));
    			tur.setHinhAnh(rs.getString(3));
    			tur.setSoNgay(rs.getInt(4));
    			list.add(tur);
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
	
	public List<DLTour> getAllByMaLoaiObject(String maLoai) {
		List<DLTour> list = new ArrayList<DLTour>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql;
		if(maLoai.equals("-1") || maLoai == null) {
			sql = "select MaTour,TieuDe,AnhDaiDien from Tour";
		} else {
			sql = "select t.MaTour,TieuDe,AnhDaiDien from Tour t, LoaiTour th where t.MaLoai=th.MaLoai and th.MaLoai=?";
		}		
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		if(!maLoai.equals("-1")) {
    			stmt.setString(1, maLoai);
    		}    		
    		ResultSet rs = stmt.executeQuery();
    		DLTour tur;
    		while(rs.next()){
    			tur = new DLTour();
    			tur.setMaTour(rs.getString(1));
    			tur.setTieuDe(rs.getString(2));
    			tur.setHinhAnh(rs.getString(3));
    			list.add(tur);
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

	public HashMap<String, String> getAllByMaLoaiTour(String maLoai) {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql;
		if(maLoai == null) {
			sql = "select MaTour,TieuDe,AnhDaiDien from Tour";
		} else {
			sql = "select t.MaTour,TieuDe,AnhDaiDien from Tour t, LoaiTour th where t.MaLoai=th.MaLoai and th.MaLoai=?";
		}
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		if(maLoai != null) {
    			stmt.setString(1, maLoai);
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
	
	public HashMap<String, String> getAllBy() {
		HashMap<String, String> list = new HashMap<String, String>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql;
			sql = "select MaTour,TieuDe,AnhDaiDien from Tour";
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

	public DLTour getInfo(String maTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTour,TieuDe,AnhDaiDien,MoTaTongQuan,LichTrinh,DiaDiemKhoiHanh,SoNgay,SoDem,GhiChu,MaTinh "
				+ "from Tour where MaTour=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTour);
    		ResultSet rs = stmt.executeQuery();
            DLTour tur = null;
    		if(rs.next()){
    			tur = new DLTour();
    			tur.setMaTour(maTour);
    			tur.setTieuDe(rs.getString(2));
    			tur.setHinhAnh(rs.getString(3));
    			tur.setMoTaTongQuan(rs.getString(4));
    			tur.setLichTrinh(rs.getString(5));
    			tur.setDiaDiemKhoiHanh(rs.getString(6));
    			tur.setSoNgay(rs.getInt(7));
    			tur.setSoDem(rs.getInt(8));
    			tur.setGhiChu(rs.getString(9));
    			tur.setMaLoai(rs.getString(10));
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
	
	public boolean updateTour(DLTour tour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update Tour set TieuDe=?, AnhDaiDien=?, MoTaTongQuan=?, LichTrinh=?, DiaDiemKhoiHanh=?, "
				+"SoNgay=?, SoDem=?, GhiChu=?, MaTinh=? where MaTour=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, tour.getTieuDe());
			stmt.setString(2, tour.getHinhAnh());
			stmt.setString(3, tour.getMoTaTongQuan());
			stmt.setString(4, tour.getLichTrinh());
			stmt.setString(5, tour.getDiaDiemKhoiHanh());
			stmt.setInt(6, tour.getSoNgay());
			stmt.setInt(7, tour.getSoDem());
			stmt.setString(8, tour.getGhiChu());
			stmt.setString(9, tour.getMaLoai());
			stmt.setString(10, tour.getMaTour());
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
}
