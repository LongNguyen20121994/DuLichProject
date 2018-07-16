package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.CumThi;
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
		String sqlCheck = "select SoCMND from QuanTriVien where SoCMND=?";
		String sql = "insert into Tour(MaTour,TieuDe,AnhDaiDien,MoTaTongQuan,LichTrinh,DiaDiemKhoiHanh,SoNgay,SoDem,GhiChu) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sqlCheck);
			stmt.setString(1, tour.getMaTour());
			ResultSet rs = stmt.executeQuery();
			int check = 0;
			if (!rs.next()) {
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
				check = stmt.executeUpdate();
			}
			stmt.close();
			if(check > 0){
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}

	public List<DLTour> getAllByMaTinh(String maTinh) {
		List<DLTour> list = new ArrayList<DLTour>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql;
		if(maTinh.equals("-1")) {
			sql = "select MaTour,TieuDe,AnhDaiDien from Tour";
		} else {
			sql = "select t.MaTour,TieuDe,AnhDaiDien from Tour t, Tinh th where t.MaTinh=th.MaTinh and th.MaTinh=?";
		}		
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		if(!maTinh.equals("-1")) {
    			stmt.setString(1, maTinh);
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

	public DLTour getInfo(String maTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTour,TieuDe,AnhDaiDien,MoTaTongQuan,LichTrinh,DiaDiemKhoiHanh,SoNgay,SoDem,GhiChu,MaTinh "
				+ "from Tour where MaTour=?";
        PreparedStatement stmt = null;
        DLTour tur = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTour);
    		ResultSet rs = stmt.executeQuery();
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
    			tur.setMaTinh(rs.getString(10));
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.closeConnection();
        }
        return tur;
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
			stmt.setString(9, tour.getMaTinh());
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
