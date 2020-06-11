package restAssured;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Base {
static String data;
static FileWriter myWriter;
	public Base() throws IOException, InterruptedException {
		System.out.println("Please read instructions carefully.\n\n1. Column Headers must be like below:\n|Serial No.|\t|Description|\t|Base|\t|URL|\t|Base Path [key,value]|\t|Path Params|\t|Query Params|\t|Authentication[If yes then body can't be NA]|\t|Auth Key to be retrieved|\t|Body|\t|expected status code|\t|Expected Status Line|\t|Expected Response Body|\t|Method|\t|\n\n2. Data must be inserted as per the column headers above and in the same sequence");
		System.out.println(
				"Please fill the column header and api data as per the instruction above.");
		System.out.println("\nNOTE : Use 'NA' as value if you don't want to have any value for a column");
		Thread.sleep(5000);
				
		data = "./TechHub.xlsx";
		
		try {

			FileInputStream input = new FileInputStream(data);
			input.close();
		} catch (Exception e) {
			
			
			System.out.println("\nTechHub Data sheet not found. \nCreating a new file for you at the location './TechHub.xlsx'....");
			System.out.println("\nNOTE : Use 'NA' as value if you don't want to have any value for a column");
			 
			OutputStream out = new FileOutputStream(data);
			
			 XSSFWorkbook create = new XSSFWorkbook();
			  create.createSheet();
			 create.write(out);
			 out.close();
			 create.close();
			 
			System.out.println(
					"Please fill the column header and api data as per the instruction above.");
			Thread.currentThread().stop();
		}
		new ExcelReader(data);

		new LoggerLog4j();

	}

}
