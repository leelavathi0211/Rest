package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;

import io.restassured.response.Response;

public class Excel_Testdata {
	public static String TESTDATA_SHEET_PATH="C:/Rest/src/test/resources/Testdata.xlsx";
	static Workbook book;
	public static Sheet sheet;
	
	//Excel utility 
	public static Object[][] getTestData(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

			book = WorkbookFactory.create(file);
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				
			}
		}
		return data;
	}
//To valdate response headers
	public static final Map<String, String> Expected_header = Map.of(
	        "Content-Type", "application/json",
	        "Server", "Jetty",
	        "Connection", "keep-alive",
	        "Transfer-Encoding", "chunked"
	);

	public static void validate(Response response) {

	    for (Map.Entry<String, String> header : Expected_header.entrySet()) {

	        String actualValue = response.getHeader(header.getKey());

	        Assert.assertNotNull(actualValue, header.getKey() + " header is missing");

	        Assert.assertTrue(actualValue.contains(header.getValue()),header.getKey() + " header value mismatch");
	    }

	    Assert.assertNotNull(response.getHeader("Date"),"Date header not found");
	}
}
	

