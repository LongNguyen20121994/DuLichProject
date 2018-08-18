package model.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.DLThongKe;
import model.bean.DLTour;
import model.bean.DLTourTrangChu;
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
		String sql = "insert into Tour(MaTour,TieuDe,AnhDaiDien,MoTaTongQuan,LichTrinh,SoNgay,SoDem,GhiChu,MaLoai) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
        try {
        	stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, tour.getMaTour());
			stmt.setString(2, tour.getTieuDe());
			stmt.setString(3, tour.getHinhAnh());
			stmt.setString(4, tour.getMoTaTongQuan());
			stmt.setString(5, tour.getLichTrinh());
			stmt.setInt(6, tour.getSoNgay());
			stmt.setInt(7, tour.getSoDem());
			stmt.setString(8, tour.getGhiChu());
			stmt.setString(9, tour.getMaLoai());
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
	
	public List<DLTourTrangChu> getTop(String maLt) throws ParseException {
		List<DLTourTrangChu> list = new ArrayList<DLTourTrangChu>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "SELECT top 4 t.MaTour,TieuDe,AnhDaiDien,SoNgay,SoDem FROM Tour t, LoaiTour lt"
						+ " Where t.MaLoai = lt.MaLoai and"
						+ " lt.MaLoai = ?"
						+ " ORDER BY MaTour DESC";	
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);	
    		stmt.setString(1, maLt);
    		ResultSet rs = stmt.executeQuery();
    		DLTourTrangChu tur;
    		int soNgayDem=0;
    		String tongNgayDem = "";
    		while(rs.next()){
    			tur = new DLTourTrangChu();
    			tur.setMaTour(rs.getString(1));
    			tur.setTieuDe(rs.getString(2));
    			tur.setHinhAnh(rs.getString(3));
    			soNgayDem = rs.getInt(4);
    			if(soNgayDem > 0) {
    				tongNgayDem = soNgayDem + " ngày ";
    			}
    			soNgayDem = rs.getInt(5);
    			if(soNgayDem > 0) {
    				tongNgayDem += soNgayDem + " đêm";
    			}
    			tur.setSoNgayDem(tongNgayDem);
    			getLichTrinh(tur);
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
	
	public void getLichTrinh(DLTourTrangChu tur) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "SELECT top 1 GiaVeNguoiLon, NgayKhoiHanh FROM ChiTietTour"
						+ " Where MaTour = ?"
						+ " and NgayKhoiHanh > ?"
						+ " ORDER BY NgayKhoiHanh ASC";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);	
    		stmt.setString(1, tur.getMaTour());
    		long millis=System.currentTimeMillis();  
    		java.sql.Date date=new java.sql.Date(millis);
    		stmt.setDate(2, date);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			tur.setGiaVe(rs.getString(1) + " VND");
    			tur.setNgayKhoiHanh(rs.getString(2).substring(0, 10));
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.closeConnection();
        }
	}
	public void getNameKhachSan(DLTourTrangChu tur) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "SELECT top 1 TenKS FROM KhachSan k, ChiTietTour c"
						+ " Where k.MaKS = c.MaKS"
						+ " and MaTour = ?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);	
    		stmt.setString(1, tur.getMaTour());
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			tur.setKhachSan(rs.getString(1));
    		} 
        	stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            con.closeConnection();
        }
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
		String sql = "select MaTour,TieuDe,AnhDaiDien,MoTaTongQuan,LichTrinh,SoNgay,SoDem,GhiChu,MaLoai "
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
    			tur.setSoNgay(rs.getInt(6));
    			tur.setSoDem(rs.getInt(7));
    			tur.setGhiChu(rs.getString(8));
    			tur.setMaLoai(rs.getString(9));
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
	public DLTourTrangChu getInfoTtTour(String maTour) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "SELECT t.MaTour,SoNgay,SoDem FROM Tour t, LoaiTour lt"
				+ " Where t.MaLoai = lt.MaLoai and" 
				+ " t.MaTour = ?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, maTour);
    		ResultSet rs = stmt.executeQuery();
    		DLTourTrangChu tur = null;
    		int soNgayDem=0;
    		String tongNgayDem = "";
    		if(rs.next()){
    			tur = new DLTourTrangChu();
    			tur.setMaTour(rs.getString(1));
    			soNgayDem = rs.getInt(2);
    			if(soNgayDem > 0) {
    				tongNgayDem = soNgayDem + " ngày ";
    			}
    			soNgayDem = rs.getInt(3);
    			if(soNgayDem > 0) {
    				tongNgayDem += soNgayDem + " đêm";
    			}
    			tur.setSoNgayDem(tongNgayDem);
    			getLichTrinh(tur);
    			getNameKhachSan(tur);
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
		String sql = "update Tour set TieuDe=?, AnhDaiDien=?, MoTaTongQuan=?, LichTrinh=?, SoNgay=?, SoDem=?, GhiChu=?, MaLoai=? where MaTour=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, tour.getTieuDe());
			stmt.setString(2, tour.getHinhAnh());
			stmt.setString(3, tour.getMoTaTongQuan());
			stmt.setString(4, tour.getLichTrinh());
			stmt.setInt(5, tour.getSoNgay());
			stmt.setInt(6, tour.getSoDem());
			stmt.setString(7, tour.getGhiChu());
			stmt.setString(8, tour.getMaLoai());
			stmt.setString(9, tour.getMaTour());
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

	public boolean deleteListTour(List<String> listMaTour) {
		ConnectDB connectDB = new ConnectDB();
		String str = "('";
		for (int i = 0; i < listMaTour.size() - 1; i++) {
			str += listMaTour.get(i) + "','";
		}
		str += listMaTour.get(listMaTour.size() - 1) + "')";
		String sql = "delete from Tour where MaTour in " + str;
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

	public List<DLTour> getAll() {
		List<DLTour> list = new ArrayList<DLTour>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTour,TieuDe,AnhDaiDien,MoTaTongQuan,LichTrinh,SoNgay,SoDem,GhiChu,MaLoai from Tour";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLTour mt;
    		while(rs.next()){
    			mt = new DLTour();
    			mt.setMaTour(rs.getString(1));
    			mt.setTieuDe(rs.getString(2));
    			mt.setHinhAnh(rs.getString(3));
    			mt.setMoTaTongQuan(rs.getString(4));
    			mt.setLichTrinh(rs.getString(5));
    			mt.setSoNgay(rs.getInt(6));
    			mt.setSoDem(rs.getInt(7));
    			mt.setGhiChu(rs.getString(8));
    			mt.setMaLoai(rs.getString(9));
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
	public List<DLThongKe> getAllDaDat(Date from, Date to) {
		List<DLThongKe> list = new ArrayList<DLThongKe>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select t.MaTour,TieuDe,NgayKhoiHanh from Tour t, ChiTietTour ct, KhachHang kh"
				+ " where t.MaTour = ct.MaTour"
				+ " and ct.MaChiTietTour = kh.MaChiTietTour"
				+ " and NgayKhoiHanh >= ?"
				+ " and NgayKhoiHanh <= ?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setDate(1, from);
    		stmt.setDate(2, to);
    		ResultSet rs = stmt.executeQuery();
    		DLThongKe mt;
    		while(rs.next()){
    			mt = new DLThongKe();
    			mt.setMaTour(rs.getString(1));
    			mt.setTieuDe(rs.getString(2));
    			mt.setNgayKhoiHanh(rs.getDate(3));
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
	public List<DLThongKe> getAllChuaDat(Date from, Date to) {
		List<DLThongKe> list = new ArrayList<DLThongKe>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select t.MaTour,TieuDe,NgayKhoiHanh from Tour t, ChiTietTour ct, KhachHang kh"
				+ " where t.MaTour = ct.MaTour"
				+ " and ct.MaChiTietTour = kh.MaChiTietTour"
				+ " and NgayKhoiHanh >= ?"
				+ " and NgayKhoiHanh <= ?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setDate(1, from);
    		stmt.setDate(2, to);
    		ResultSet rs = stmt.executeQuery();
    		DLThongKe mt;
    		while(rs.next()){
    			mt = new DLThongKe();
    			mt.setMaTour(rs.getString(1));
    			mt.setTieuDe(rs.getString(2));
    			mt.setNgayKhoiHanh(rs.getDate(3));
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
	public List<DLThongKe> getAllThongKe() {
		List<DLThongKe> list = new ArrayList<DLThongKe>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select t.MaTour,TieuDe,NgayKhoiHanh from Tour t, ChiTietTour ct, KhachHang kh"
				+ " where t.MaTour = ct.MaTour"
				+ " and ct.MaChiTietTour = kh.MaChiTietTour";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		ResultSet rs = stmt.executeQuery();
    		DLThongKe mt;
    		while(rs.next()){
    			mt = new DLThongKe();
    			mt.setMaTour(rs.getString(1));
    			mt.setTieuDe(rs.getString(2));
    			mt.setNgayKhoiHanh(rs.getDate(3));
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
	public List<DLTour> getAllLikeName(String name) {
		List<DLTour> list = new ArrayList<DLTour>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select MaTour,TieuDe from Tour where TieuDe like '%?%'";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, name);
    		ResultSet rs = stmt.executeQuery();
    		DLTour mt;
    		while(rs.next()){
    			mt = new DLTour();
    			mt.setMaTour(rs.getString(1));
    			mt.setTieuDe(rs.getString(2));
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
}
