package controller.longnt;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opensymphony.xwork2.ActionSupport;

import common.ReadExcelFile;
import model.bean.ChiTietDKDT;

@SuppressWarnings("serial")
public class KetQuaDuThiAction extends ActionSupport{
	private File file;
	List<ChiTietDKDT> list;
	private ChiTietDKDT ctdkdt = new ChiTietDKDT();

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List<ChiTietDKDT> getList() {
		return list;
	}

	public void setList(List<ChiTietDKDT> list) {
		this.list = list;
	}

	public ChiTietDKDT getCtdkdt() {
		return ctdkdt;
	}

	public void setCtdkdt(ChiTietDKDT ctdkdt) {
		this.ctdkdt = ctdkdt;
	}

	public List<ChiTietDKDT> readChiTietDKDT(File file) {
		List<ChiTietDKDT> listBooks = new ArrayList<ChiTietDKDT>();
		FileInputStream inputStream = null;
		Workbook workbook = null;
		try {
			inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				ChiTietDKDT ctdkdt = new ChiTietDKDT();
				ReadExcelFile read = new ReadExcelFile();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
						case 1:
							ctdkdt.setSoCMND((String) read.getCellValue(nextCell));
							break;
						case 2:
							ctdkdt.setNamTS((int) read.getCellValue(nextCell));
							break;
						case 3:
							ctdkdt.setIDPhong((String) read.getCellValue(nextCell));
							break;
						case 4:
							ctdkdt.setMaMonThi((String) read.getCellValue(nextCell));
							break;
						case 5:
							ctdkdt.setDiemThi((double) read.getCellValue(nextCell));
							break;
					}
				}
				listBooks.add(ctdkdt);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
			try {
				workbook.close();

				inputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return listBooks;
	}
	
	public String execute() throws Exception {
		list = readChiTietDKDT(file);
		if (list.isEmpty()) {
			return "input";
		}
		return "success";
	}
}
