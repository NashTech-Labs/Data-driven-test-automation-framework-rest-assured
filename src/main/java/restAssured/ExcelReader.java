package restAssured;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public static int totalRows;
	public static int totalColumns;
	public static XSSFWorkbook testData;
	public static XSSFSheet basesheet;
	FileInputStream fis;
	public static ArrayList<HashMap<String, String>> dataset = new ArrayList<>();
	public static HashMap<String, String> coloumnDataSet;
	public static ArrayList<HashMap<String, String>> forSearch = new ArrayList<>();
	public static HashMap<String, String> subStringDataSet;
	public static ArrayList<String> dataHeader;

	public ExcelReader(String path) {

		try {
			fis = new FileInputStream(path);
			testData = new XSSFWorkbook(fis);
			basesheet = testData.getSheetAt(0);
			totalRows = basesheet.getLastRowNum();
			totalColumns = basesheet.getRow(0).getLastCellNum() - 1;
			System.out.println("Sheet is having " + totalRows + " rows and " + totalColumns + " columns");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getData(int sheetNo, int rowIndex, int columnIndex) {
		basesheet = testData.getSheetAt(sheetNo);
		String data = basesheet.getRow(rowIndex).getCell(columnIndex).getStringCellValue();

		return data;

	}

	public static void getDataSet(int rowFrom, int rowTo, int columnFrom, int columnTo) {
		int coloumnIndex = 0;
		int rowIndex = 0;

		basesheet = testData.getSheetAt(0);
		dataHeader = new ArrayList<String>();
		for (rowIndex = rowFrom; rowIndex <= rowFrom; rowIndex++) {
			for (coloumnIndex = columnFrom; coloumnIndex <= columnTo; coloumnIndex++) {
				dataHeader.add(basesheet.getRow(rowIndex).getCell(coloumnIndex).getStringCellValue());
			}
		}
		for (rowIndex = rowFrom + 1; rowIndex <= rowTo; rowIndex++) {
			subStringDataSet = new HashMap<>();
			coloumnDataSet = new HashMap<>();
			for (coloumnIndex = columnFrom; coloumnIndex <= columnTo; coloumnIndex++) {
					basesheet.getRow(rowIndex).getCell(coloumnIndex).setCellType(CellType.STRING);
				String cellvalue = basesheet.getRow(rowIndex).getCell(coloumnIndex).getStringCellValue();
				
				coloumnDataSet.put(dataHeader.get(coloumnIndex), cellvalue);

			}
			dataset.add(coloumnDataSet);
			forSearch.add(subStringDataSet);


		}

	}
	//For testing this class

	// public static void main(String[] args) {
	//		new ExcelReader("C:\\\\Users\\\\91981\\\\Documents\\\\Test data\\\\TimeSheet.xlsx");
	//		ExcelReader.getDataSet(0, ExcelReader.totalRows - 1, 0, ExcelReader.totalColumns - 1);
	//		for (int a = 0; a <= ExcelReader.forSearch.size() - 1; a++) {
	//			for (int b = 0; b <= ExcelReader.subStringDataSet.size() - 1; b++) {
	//				System.out.println("core classssss");
	//
	//				String value =ExcelReader.forSearch.get(a).get(ExcelReader.dataHeader.get(b));
	//				
	//			System.out.println(value);
	//			
	//			}}
	//	}

}
