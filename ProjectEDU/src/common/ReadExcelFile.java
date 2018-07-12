package common;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A nice simple program that reads an Excel file in a more object-oriented way.
 * 
 * @author www.codejava.net
 *
 */
public class ReadExcelFile {

	public List<Object> readSP(File file) {
		List<Object> listBooks = new ArrayList<>();
		FileInputStream inputStream;
		Workbook workbook = null;
		try {
			inputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputStream);

			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();

			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Object aBook = new Object();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
//					case 0:
//						aBook.setMasp((String) getCellValue(nextCell));
//						break;
//					case 1:
//						aBook.setTensp((String) getCellValue(nextCell));
//						break;
//					case 2:
//						aBook.setGia((double) getCellValue(nextCell));
//						break;
					}
				}
				listBooks.add(aBook);
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listBooks;
	}

	public Object getCellValue(Cell cell) {
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

	public static void main(String[] args) {
		String excelFilePath = "C:\\Users\\daikh_000\\Desktop\\Books.xlsx";
		File file = new File(excelFilePath);
		ReadExcelFile reader = new ReadExcelFile();
		List<Object> listBooks = reader.readSP(file);
		for (Object sp : listBooks)
			System.out.println(sp);
	}

}
