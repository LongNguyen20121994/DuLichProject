package model.dao.longnt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.ChiTietDKDT;
import model.bean.MonThi;
import model.dao.khoi.ConnectDB;

public class ChiTietDKDTDAO {

	public boolean validMonThi(String soCMND, int namTS, String maMon) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "Select SoCMND from ChiTietDKDT where SoCMND =? and NamTS=? and MaMon=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1,soCMND);
			stmt.setInt(2,namTS);
			stmt.setString(3,maMon);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			stmt.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
		return false;
	}

	public ArrayList<MonThi> getAllMonThi(String soCMND, int namTS) {
		ArrayList<MonThi> list = new ArrayList<MonThi>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "Select ChiTietDKDT.MaMon,TenMonThi from ChiTietDKDT inner join MonThi on MonThi.MaMon = ChiTietDKDT.MaMon"
				+ " where SoCMND =? and NamTS=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1,soCMND);
			stmt.setInt(2,namTS);
			
			ResultSet rs = stmt.executeQuery();
			MonThi mt;
			
			while (rs.next()) {
				 mt=new MonThi();
				 mt.setMaMonThi(rs.getString(1));
				 mt.setTenMonThi(rs.getString(2));
				 
				 list.add(mt);
			}
			stmt.close();
			return list;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		} finally {
			con.closeConnection();
		}
		
	}

	public boolean deleteListMonDKDT(String soCMND, int namTS, String[] listMT) {
		String str = "('";
        for (int i = 0; i < listMT.length - 1; i++) {
            str += listMT[i] + "','";
        }
        str += listMT[listMT.length - 1] + "')";
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "delete from ChiTietDKDT where SoCMND=? and NamTS=? and MaMon in " + str;
		String sqlCheck = " Select SoCMND from ChiTietDKDT where SoCMND =? and NamTS=?";
		String sqlDelDKDT = "delete from DangKyDuThi where SoCMND=? and NamTS=?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			stmt.setInt(2, namTS);
			
			//Xóa môn
			if (stmt.executeUpdate() > 0) {
				stmt.close();
				
				// kiểm tra còn môn
				stmt = con.getConnect().prepareStatement(sqlCheck);
				stmt.setString(1, soCMND);
				stmt.setInt(2, namTS);
				
				if(!stmt.executeQuery().next()){
					
					//Xóa DKDT
					stmt = con.getConnect().prepareStatement(sqlDelDKDT);
					stmt.setString(1, soCMND);
					stmt.setInt(2, namTS);
					stmt.executeUpdate();
				}
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

	public boolean insertChiTietDKDT(ChiTietDKDT ctDKDT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "insert ChiTietDKDT(SoCMND,NamTS,MaMon) values(?,?,?)";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1,ctDKDT.getSoCMND());
			stmt.setInt(2,ctDKDT.getNamTS());
			stmt.setString(3,ctDKDT.getMaMonThi());
			
			int rs = stmt.executeUpdate();
			if (rs == 1) {
				return true;
			}
			stmt.close();
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		} finally {
			con.closeConnection();
		}
	}
	
	public boolean updateChiTietDKDT(ChiTietDKDT ctDKDT) {
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "update ChiTietDKDT set DiemThi = ? where SoCMND = ? and NamTS = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1,ctDKDT.getSoCMND());
			stmt.setInt(2,ctDKDT.getNamTS());
			stmt.setString(3,ctDKDT.getMaMonThi());
			
			int rs = stmt.executeUpdate();
			if (rs == 1) {
				return true;
			}
			stmt.close();
			return false;
		} catch (Exception e) {
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
		String sql = "select MaTinh,TenTinh from Tinh_ThanhPho";
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

	public String themListChiTietDKDT(ArrayList<ChiTietDKDT> list) {
		ConnectDB connectDB = new ConnectDB();
		String listExist = "";
		String listReturn = "";
		String sqlInsert = "insert into ChiTietDKDT(SoCMND, NamTS, MaMon, DiemThi, IDPhong values (?,?,?,?,?)";
		String sqlCheck = "select SoCMND from ChiTietDKDT where SoCMND=?";
		PreparedStatement stmtc = null;
		PreparedStatement stmte = null;
		connectDB.openConnection();
		try {
			for (ChiTietDKDT ctdkdt : list) {
				stmtc = connectDB.getConnect().prepareStatement(sqlCheck);
				stmtc.setString(1, ctdkdt.getSoCMND());
				if (stmtc.executeQuery().next()) {
					listExist = listExist + ctdkdt.getSoCMND() + ", ";
				} else {
					stmte = connectDB.getConnect().prepareStatement(sqlInsert);
					stmte.setString(1, ctdkdt.getSoCMND());
					stmte.setInt(2, ctdkdt.getNamTS());
					stmte.setString(3, ctdkdt.getMaMonThi());
					stmte.setDouble(4, ctdkdt.getDiemThi());
					stmte.setString(5, ctdkdt.getIDPhong());

					if (stmte.executeUpdate() < 1)
						listReturn = listReturn + ctdkdt.getSoCMND() + ", ";
					stmte.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "True";
		} finally {
			connectDB.closeConnection();
		}
		if (listExist.length() > 2)
			listExist = "Đã tồn tại thí sinh: " + listExist.substring(0, listExist.length() - 2) + ". ";
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listExist + listReturn;
	}
	
	public String updateListCTDKDT(ArrayList<ChiTietDKDT> listCTDKDT) {
		ConnectDB connectDB = new ConnectDB();
		String listReturn = "";
		String sqlInsert = "update ChiTietDKDT set MaMon=? where SoCMND=? and NamTS=?)";
		PreparedStatement stmte = null;
		connectDB.openConnection();
		try {
			for (ChiTietDKDT ct : listCTDKDT) {
				stmte = connectDB.getConnect().prepareStatement(sqlInsert);
				stmte.setInt(1, ct.getNamTS());
				stmte.setString(2, ct.getSoCMND());
				stmte.setString(3, ct.getMaMonThi());

				if (stmte.executeUpdate() < 1)
					listReturn = listReturn + ct.getSoCMND() + "Mã môn: " + ct.getMaMonThi() + ", ";
				stmte.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}
	
	public String themListCTDKDT(ArrayList<ChiTietDKDT> listCTDKDT) {
		ConnectDB connectDB = new ConnectDB();
		String listReturn = "";
		String sqlInsert = "insert into ChiTietDKDT(SoCMND, NamTS, MaMon) values (?,?,?)";
		PreparedStatement stmte = null;
		connectDB.openConnection();
		try {
			for (ChiTietDKDT ct : listCTDKDT) {
				stmte = connectDB.getConnect().prepareStatement(sqlInsert);
				stmte.setString(1, ct.getSoCMND());
				stmte.setInt(2, ct.getNamTS());
				stmte.setString(3, ct.getMaMonThi());

				if (stmte.executeUpdate() < 1)
					listReturn = listReturn + ct.getSoCMND() + "Mã môn: " + ct.getMaMonThi() + ", ";
				stmte.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "fail";
		} finally {
			connectDB.closeConnection();
		}
		if (listReturn.length() > 2)
			listReturn = "Dữ liệu nhập chưa đúng: " + listReturn.substring(0, listReturn.length() - 2) + ". ";
		return listReturn;
	}

	public List<ChiTietDKDT> getListInfoChiTietDKDT(String soCMND) {
		ArrayList<ChiTietDKDT> list =  new ArrayList<ChiTietDKDT>();
		ConnectDB con = new ConnectDB();
		con.openConnection();
		String sql = "select SoCMND, MaMon, DiemThi from ChiTietDKDT where SoCMND = ? and NamTS = ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.getConnect().prepareStatement(sql);
			stmt.setString(1, soCMND);
			ResultSet rs =  stmt.executeQuery();
			ChiTietDKDT ct;
			
			while (rs.next()) {
				ct =  new ChiTietDKDT();
				ct.setSoCMND(rs.getString(1));
				ct.setMaMonThi(rs.getString(2));
				ct.setDiemThi(rs.getDouble(3));
				list.add(ct);
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
