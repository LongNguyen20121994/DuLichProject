/**
 * 
 */
package controller.quang;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.ExtractZipRar;
import common.Library;
import model.bean.ChiTietDKDT;
import model.bean.DangKyDuThi;
import model.bean.Info;
import model.bean.ThiSinh;
import model.bean.ThiSinhTHPT;
import model.bo.quang.ChiTietDKDTBO;
import model.bo.quang.DanTocBO;
import model.bo.quang.DangKyDuThiBO;
import model.bo.quang.DoiTuongUTBO;
import model.bo.quang.NamTuyenSinhBO;
import model.bo.quang.ThiSinhBO;
import model.bo.quang.TinhThanhPhoBO;

/**
 * @author MinhQuang
 *
 */
@SuppressWarnings("serial")
public class UploadDSDKAction extends ActionSupport {

	private File danhSachDK, listAnh;
	private String danhSachDKFileName, listAnhFileName;
	private ArrayList<ThiSinh> listTSDK;
	private HashMap<String, ArrayList<ThiSinhTHPT>> listThiSinhTHPT;
	private ArrayList<ChiTietDKDT> listCTDKDT;
	private ArrayList<DangKyDuThi> listDangKyDT;

	private ThiSinhBO thiSinhBO;
	
	private Info info;
	private String nguoiDK = "";
	private int namTS;

	// show dk 1 thi sinh
	private HashMap<String, String> listTinh;
	private HashMap<String, String> listDanToc;
	private HashMap<String, String> listDoiTuongUT;

	/**
	 * return type of value Excell
	 * 
	 * @param cell
	 * @return
	 */
	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}
		return null;
	}

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		nguoiDK = (String) session.get("soCMND");

		if (nguoiDK != null && !nguoiDK.isEmpty()) {
			String account = (String) session.get("account");
			if ("2".equals(account)) {

				// upload danh sách ảnh
				if (listAnh == null && danhSachDK == null) {
					info = new Info("Lỗi nhập liệu", "Chưa chọn file upload!");
					return ERROR;
				} else {
					long size = 0;
					if (listAnh != null)
						size += listAnh.length();
					if (danhSachDK != null)
						size += danhSachDK.length();
					size = size / 1024 / 1024;
					if (size < 50) {
						if (listAnh != null) {
							if ("rar".equals(Library.getExensionFile(listAnhFileName))) {
								ServletContext context = ServletActionContext.getServletContext();
								String anhPath = context.getRealPath("") + "\\anhThanhVien\\";
								System.out.println(anhPath);
								ExtractZipRar.extractRARFile(anhPath, listAnh);
							} else {
								info = new Info("Lỗi nhập file sai định dạng",
										"File ảnh thí sinh phải là file RAR (.rar)");
								return ERROR;
							}
						}

						// upload danh sách thí sinh đăng ký.
						if (danhSachDK != null) {
							String extension = Library.getExensionFile(danhSachDKFileName);
							if ("xlsx".equals(extension) || "xls".equals(extension)) {

								String dong = readExcel();
								if ("".equals(dong)) {
									if (listTSDK == null || listThiSinhTHPT == null) {
										info = new Info("Lỗi nhập liệu",
												"File danh sách thí sinh Excel phải theo định dạng !");
										return ERROR;
									} else {

										// Thêm vào csdl
										thiSinhBO = new ThiSinhBO();
										String listTSDKError = thiSinhBO.themListDangKyDT(listTSDK, listThiSinhTHPT);
										if (listTSDKError != null && !listTSDKError.isEmpty()) {
											info = new Info("Lỗi nhập liệu", listTSDKError);
											return ERROR;
										}

										DangKyDuThiBO dkdtBO = new DangKyDuThiBO();
										if(listDangKyDT != null){
											System.out.println(dkdtBO.insertListDangKy(listDangKyDT));
										}
										
										if(listCTDKDT != null){
										System.out.println(new ChiTietDKDTBO().themListCTDKDT(listCTDKDT));
										}
										System.out.println(listTSDKError);
									}
								} else {
									info = new Info("Lỗi nhập liệu",
											"File excel, dòng: " + dong + " nhập không theo định dạng");
									return ERROR;
								}
							} else {
								info = new Info("Lỗi nhập file sai định dạng",
										"File dang sách thí sinh phải là file Excel 2007 hoặc cũ hơn (.xls, .xlsx)");
								return ERROR;
							}
						}
					} else {
						info = new Info("Lỗi nhập file sai định dạng", "2 file upload phải dưới 50MB");
						return ERROR;
					}
					info = new Info("Đăng ký thành công", "Upload file thành công! ");
					return SUCCESS;
				}
			} else {
				info = new Info("Lỗi", "Bạn không đủ quyền vào trang này!");
				return ERROR;
			}
		} else {
			info = new Info("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}

	/**
	 * Show page upload danh sách đăng ký
	 * 
	 * @return
	 */
	public String display() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		nguoiDK = (String) session.get("soCMND");

		if (nguoiDK != null && !nguoiDK.isEmpty()) {
			String account = (String) session.get("account");
			if ("2".equals(account)) {
				listTinh = new TinhThanhPhoBO().getAllSelect();
				listDanToc = new DanTocBO().getAllSelect();
				listDoiTuongUT = new DoiTuongUTBO().getAllSelect();
				return NONE;
			} else {
				info = new Info("Lỗi", "Bạn không đủ quyền vào trang này!");
				return ERROR;
			}
		} else {
			info = new Info("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}

	/**
	 * Read file Excel Upload
	 * 
	 * @return
	 */
	private String readExcel() {
		namTS = new NamTuyenSinhBO().getNamTSHienTai();

		// read file danh sach dang ky du thi excel
		FileInputStream inputStream = null;
		Workbook workbook = null;
		ArrayList<ThiSinhTHPT> arrayTSTHPT;
		String error = "";
		int dong = 1;

		// đọc file Excel + lấy dữ liệu
		try {
			workbook = WorkbookFactory.create(danhSachDK);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			iterator.next();
			iterator.next();
			String tenTS = "";
			ThiSinh thiSinh;
			ThiSinhTHPT thiSinhTHPT;
			ChiTietDKDT chiTietDKDT;
			DangKyDuThi dangKyDuThi;

			listTSDK = new ArrayList<ThiSinh>();
			listThiSinhTHPT = new HashMap<String, ArrayList<ThiSinhTHPT>>();
			listDangKyDT = new ArrayList<DangKyDuThi>();
			listCTDKDT = new ArrayList<ChiTietDKDT>();
			
			Row nextRow;
			Cell nextCell;
			Iterator<Cell> cellIterator;

			// read rows
			while (iterator.hasNext()) {
				nextRow = iterator.next();
				cellIterator = nextRow.cellIterator();
				thiSinh = new ThiSinh();
				String soCMND = "";
				String[] part = null;
				arrayTSTHPT = new ArrayList<ThiSinhTHPT>();
				// read columns

				while (cellIterator.hasNext()) {
					nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 1:
						tenTS = Library.trimString(getCellValue(nextCell));
						break;
					case 2:
						thiSinh.setHoTen(tenTS + " " + Library.trimString(getCellValue(nextCell)));
						break;
					case 3:
						thiSinh.setGioiTinh(("Nam".equals(Library.trimString(getCellValue(nextCell)))) ? true : false);
						break;
					case 4: {
						thiSinh.setNgaySinh(Library.untilToSql(DateUtil.getJavaDate(Double.valueOf(Library.trimString(getCellValue(nextCell))))));
						break;
					}
					case 5:
						thiSinh.setNoiSinh(Library.trimString(getCellValue(nextCell)));
						break;
					case 6:
						thiSinh.setDanToc(Library.trimString(getCellValue(nextCell)));
						break;
					case 7: {
						soCMND = Library.trimString(getCellValue(nextCell));
						thiSinh.setSoCMND(soCMND);
						break;
					}
					case 8: {
						thiSinh.setMaTinh(Library.trimString((String) getCellValue(nextCell)));
						if(cellIterator.hasNext()){
							nextCell = cellIterator.next();
						}
						thiSinh.setMaHuyen(Library.trimString((String) getCellValue(nextCell)));
						if(cellIterator.hasNext()){
							nextCell = cellIterator.next();
						}
						thiSinh.setMaXa(getCellValue(nextCell) == null ? null : Library.trimString(getCellValue(nextCell)));
						break;
					}
					// Truong THPT
					case 11: {
						part = ((String) getCellValue(nextCell)).split(",");
						thiSinhTHPT = new ThiSinhTHPT();
						thiSinhTHPT.setSoCMND(soCMND);
						thiSinhTHPT.setMaTinhTHPT(Library.trimString(part[0]));
						thiSinhTHPT.setMaTruong(Library.trimString(part[1]));
						thiSinhTHPT.setLop(10);

						arrayTSTHPT.add(thiSinhTHPT);
						break;
					}
					case 12: {
						part = ((String) getCellValue(nextCell)).split(",");
						thiSinhTHPT = new ThiSinhTHPT();
						thiSinhTHPT.setSoCMND(soCMND);
						thiSinhTHPT.setMaTinhTHPT(Library.trimString(part[0]));
						thiSinhTHPT.setMaTruong(Library.trimString(part[1]));
						thiSinhTHPT.setLop(11);

						arrayTSTHPT.add(thiSinhTHPT);
						break;
					}
					case 13: {
						part = ((String) getCellValue(nextCell)).split(",");
						thiSinhTHPT = new ThiSinhTHPT();
						thiSinhTHPT.setSoCMND(soCMND);
						thiSinhTHPT.setMaTinhTHPT(Library.trimString(part[0]));
						thiSinhTHPT.setMaTruong(Library.trimString(part[1]));
						thiSinhTHPT.setLop(12);

						arrayTSTHPT.add(thiSinhTHPT);
						listThiSinhTHPT.put(soCMND, arrayTSTHPT);
						break;
					}
					case 14:
						thiSinh.setDiaChi(Library.trimString(getCellValue(nextCell)));
						break;
					case 15:
						thiSinh.setEmail(Library.trimString(getCellValue(nextCell)));
						break;
					case 16:
						thiSinh.setSoDT(Library.trimString(getCellValue(nextCell)));
						break;
					case 17:
						thiSinh.setDoiTuongUT(Library.trimString(getCellValue(nextCell)));
						break;
					case 18: {
						double n = (double) getCellValue(nextCell);
						thiSinh.setNamTN((int) n);
						break;
					}
					//Dang ky du thi
					case 19: {
						dangKyDuThi = new DangKyDuThi();
						dangKyDuThi.setSoCMND(soCMND);
						dangKyDuThi.setNamTS(namTS);
						dangKyDuThi.setMaCumThi(Library.trimString(getCellValue(nextCell)));

						listDangKyDT.add(dangKyDuThi);
						break;
					}
					case 20: {
						part = ((String) getCellValue(nextCell)).split(",");
						for (int i = 0; i < part.length; i++) {
							chiTietDKDT = new ChiTietDKDT();
							chiTietDKDT.setMaMonThi(Library.trimString(part[i]));
							chiTietDKDT.setSoCMND(soCMND);
							chiTietDKDT.setNamTS(namTS);
							listCTDKDT.add(chiTietDKDT);
						}
						break;
					}
					}

					thiSinh.setTrangThai(true);
					thiSinh.setHinhAnh("/anhThanhVien/" + soCMND);
					thiSinh.setNguoiDK(nguoiDK);
					Random random = new Random();
					thiSinh.setMatKhau(String.valueOf(random.nextInt()));
				}
				if (soCMND != null && !"".equals(soCMND))
					listTSDK.add(thiSinh);
				dong++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("read file Excel: " + e.getMessage());
			error += dong + ", ";
		} finally {
			try {
				if(workbook != null)
					workbook.close();
				if(inputStream != null)
					inputStream.close();
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
		if (error.length() > 2) {
			error = error.substring(0, error.length() - 2);
		}
		return error;
	}

	public ArrayList<ThiSinh> getListTSDK() {
		return listTSDK;
	}

	public void setListTSDK(ArrayList<ThiSinh> listTSDK) {
		this.listTSDK = listTSDK;
	}

	public int getNamTS() {
		return namTS;
	}

	public void setNamTS(int namTS) {
		this.namTS = namTS;
	}

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}

	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}

	public HashMap<String, String> getListDanToc() {
		return listDanToc;
	}

	public void setListDanToc(HashMap<String, String> listDanToc) {
		this.listDanToc = listDanToc;
	}

	public HashMap<String, String> getListDoiTuongUT() {
		return listDoiTuongUT;
	}

	public void setListDoiTuongUT(HashMap<String, String> listDoiTuongUT) {
		this.listDoiTuongUT = listDoiTuongUT;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getListAnhFileName() {
		return listAnhFileName;
	}

	public void setListAnhFileName(String listAnhFileName) {
		this.listAnhFileName = listAnhFileName;
	}

	public File getListAnh() {
		return listAnh;
	}

	public void setListAnh(File listAnh) {
		this.listAnh = listAnh;
	}

	public File getDanhSachDK() {
		return danhSachDK;
	}

	public void setDanhSachDK(File danhSachDK) {
		this.danhSachDK = danhSachDK;
	}

	public String getDanhSachDKFileName() {
		return danhSachDKFileName;
	}

	public void setDanhSachDKFileName(String danhSachDKFileName) {
		this.danhSachDKFileName = danhSachDKFileName;
	}

}
