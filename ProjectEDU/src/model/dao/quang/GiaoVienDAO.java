package model.dao.quang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.GiaoVien;
import model.dao.khoi.ConnectDB;

public class GiaoVienDAO {

	public boolean isSoCMND(String soCMND) {
		ConnectDB connectDB = new ConnectDB();
		String sql = "Select SoCMND from GiaoVien where SoCMND=?";
		
		try {
			connectDB.openConnection();
			PreparedStatement stmt = connectDB.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			if ((stmt.executeQuery()).next())
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public GiaoVien getInfo(String soCMND) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,"
				+ "Email,MaTruongTHPT,MaTinhTHPT,HinhAnh,Logined,TrangThai from GiaoVien where SoCMND=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, soCMND);
    		ResultSet rs = stmt.executeQuery();
    		GiaoVien gv = null;
    		if(rs.next()){
    			gv = new GiaoVien();
    			gv.setSoCMND(rs.getString(1));
    			gv.setHoTen(rs.getString(2));
    			gv.setMatKhau(rs.getString(3));
    			gv.setNgaySinh(rs.getDate(4));
    			gv.setMaXa(rs.getString(5));
    			gv.setMaHuyen(rs.getString(6));
    			gv.setMaTinh(rs.getString(7));
    			gv.setGioiTinh(rs.getBoolean(8));
    			gv.setSoDT(rs.getString(9));
    			gv.setEmail(rs.getString(10));
    			gv.setMaTruongTHPT(rs.getString(11));
    			gv.setMaTinhTHPT(rs.getString(12));
    			gv.setHinhAnh(rs.getString(13));
    			gv.setLogined(rs.getBoolean(14));
    			gv.setTrangThai(rs.getBoolean(15));
    		} 
        	stmt.close();
        	return gv;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}
	
	public boolean doiMatKhau(String soCMND, String matKhau) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update GiaoVien set MatKhau=?, Logined=? where SoCMND=?";
        PreparedStatement stmt = null;
        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, matKhau);
    		stmt.setBoolean(2, true);
    		stmt.setString(3, soCMND);
    		int rs = stmt.executeUpdate();
        	stmt.close();
        	if(rs > 0){
        		return true;
        	}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            con.closeConnection();
        }
        return false;
	}

	public boolean insertGiaoVien(GiaoVien gv) {
		ConnectDB con = new ConnectDB();
		PreparedStatement stmt =null;
		con.openConnection();
		String sql="insert into GiaoVien(SoCMND,HoTen,NgaySinh,MaXa,MaHuyen,MaTinh,GioiTinh,SoDT,Email,"
				+ "MaTruongTHPT,MaTinhTHPT,HinhAnh,MatKhau) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try{
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1,gv.getSoCMND());
			stmt.setString(2,gv.getHoTen());
			stmt.setDate(3,gv.getNgaySinh());
			stmt.setString(4,gv.getMaXa());
			stmt.setString(5,gv.getMaHuyen());
			stmt.setString(6,gv.getMaTinh());
			stmt.setBoolean(7,gv.isGioiTinh());
			stmt.setString(8,gv.getSoDT());
			stmt.setString(9,gv.getEmail());
			stmt.setString(10,gv.getMaTruongTHPT());
			stmt.setString(11,gv.getMaTinhTHPT());
			stmt.setString(12,gv.getHinhAnh());
			stmt.setString(13,gv.getMatKhau());
			
			if(stmt.executeUpdate()==1)
				return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}

	public GiaoVien getInfoHienThi(String soCMND) {
		ConnectDB con = new ConnectDB();
        PreparedStatement stmt = null;
		con.openConnection();
		String sql = "select SoCMND,HoTen,MatKhau,NgaySinh,GiaoVien.MaXa,GiaoVien.MaHuyen,GiaoVien.MaTinh,GioiTinh,SoDT, "
				+ "Email,GiaoVien.MaTruongTHPT,GiaoVien.MaTinhTHPT,HinhAnh,Logined,TrangThai, "
				+ "TenXa,TenHuyen,TinhHK.TenTinh,TenTruong,TinhTHPT.TenTinh "
				+ "from GiaoVien left join Xa_Phuong on GiaoVien.MaXa=Xa_Phuong.MaXa and Xa_Phuong.MaHuyen = GiaoVien.MaHuyen and Xa_Phuong.MaTinh = GiaoVien.MaTinh "
				+ "inner join Huyen_Quan on Huyen_Quan.MaHuyen = GiaoVien.MaHuyen and GiaoVien.MaTinh=Huyen_Quan.MaTinh "
				+ "inner join Tinh_ThanhPho as TinhHK on TinhHK.MaTinh= GiaoVien.MaTinh "
				+ "inner join TruongTHPT on TruongTHPT.MaTruong = GiaoVien.MaTruongTHPT and GiaoVien.MaTinhTHPT = TruongTHPT.MaTinh "
				+ "inner join Tinh_ThanhPho as TinhTHPT on GiaoVien.MaTinhTHPT = TinhTHPT.MaTinh where SoCMND=?";

        try {
    		stmt = con.getConnect().prepareStatement(sql);
    		stmt.setString(1, soCMND);
    		ResultSet rs = stmt.executeQuery();
    		GiaoVien gv = null;
    		if(rs.next()){
    			gv = new GiaoVien();
    			gv.setSoCMND(rs.getString(1));
    			gv.setHoTen(rs.getString(2));
    			gv.setMatKhau(rs.getString(3));
    			gv.setNgaySinh(rs.getDate(4));
    			gv.setMaXa(rs.getString(5));
    			gv.setMaHuyen(rs.getString(6));
    			gv.setMaTinh(rs.getString(7));
    			gv.setGioiTinh(rs.getBoolean(8));
    			gv.setSoDT(rs.getString(9));
    			gv.setEmail(rs.getString(10));
    			gv.setMaTruongTHPT(rs.getString(11));
    			gv.setMaTinhTHPT(rs.getString(12));
    			gv.setHinhAnh(rs.getString(13));
    			gv.setLogined(rs.getBoolean(14));
    			gv.setTrangThai(rs.getBoolean(15));
    			gv.setTenXa(rs.getString(16));
    			gv.setTenHuyen(rs.getString(17));
    			gv.setTenTinh(rs.getString(18));
    			gv.setTenTruongTHPT(rs.getString(19));
    			gv.setTenTinhTHPT(rs.getString(20));
    		} 
        	stmt.close();
        	return gv;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            con.closeConnection();
        }
	}

	public boolean updateInfo(GiaoVien gv) {
		ConnectDB con = new ConnectDB();
		PreparedStatement stmt =null;
		con.openConnection();
		String sql="update GiaoVien set HoTen=?,NgaySinh=?,MaXa=?,MaHuyen=?,MaTinh=?,GioiTinh=?,SoDT=?,Email=?,"
				+ "MaTruongTHPT=?,MaTinhTHPT=?,HinhAnh=? where SoCMND=?";

		try{
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(12,gv.getSoCMND());
			stmt.setString(1,gv.getHoTen());
			stmt.setDate(2,gv.getNgaySinh());
			stmt.setString(3,gv.getMaXa());
			stmt.setString(4,gv.getMaHuyen());
			stmt.setString(5,gv.getMaTinh());
			stmt.setBoolean(6,gv.isGioiTinh());
			stmt.setString(7,gv.getSoDT());
			stmt.setString(8,gv.getEmail());
			stmt.setString(9,gv.getMaTruongTHPT());
			stmt.setString(10,gv.getMaTinhTHPT());
			stmt.setString(11,gv.getHinhAnh());
			
			if(stmt.executeUpdate()==1)
				return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}
}
