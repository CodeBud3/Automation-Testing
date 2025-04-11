package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataManager {
    private static final String EXCEL_PATH = System.getProperty("user.dir")+"/src/main/resources/testData/TestData.xlsx";
    // Key: sheetName.testCaseName → List of data rows
    private static final Map<String, List<Map<String, String>>> cachedData = new HashMap<>();

    static {
        loadAllSheets();
    }

    private static void loadAllSheets() {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            for (Sheet sheet : workbook) {
                String sheetName = sheet.getSheetName();
                Row headerRow = sheet.getRow(0);
                if (headerRow == null) continue;

                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;
//                    Log.info("sheetName"+sheetName+i);
                    Cell cellValue = row.getCell(0);
                    if (cellValue == null) {
                    	break;
                    }
                    String functionName = cellValue.getStringCellValue();
                    Map<String, String> data = new HashMap<>();

                    for (int j = 1; j < headerRow.getLastCellNum(); j++) {
                        String key = headerRow.getCell(j).getStringCellValue();
                        String value = row.getCell(j) != null ? row.getCell(j).toString() : "";
                        data.put(key, value);
                    }

                    String mapKey = sheetName + "." + functionName;
                    cachedData.computeIfAbsent(mapKey, k -> new ArrayList<>()).add(data);
                }
            }

        } catch (IOException e) {
//        	Log.info("failedToReadExcel");
            e.printStackTrace();
        }
    }

    public static Object[][] getTestData(String sheetName, String functionName) {
//    	Log.info("sheetName-"+sheetName);
//    	Log.info("functionName-"+functionName);
        String mapKey = sheetName + "." + functionName;
        List<Map<String, String>> dataList = cachedData.get(mapKey);
        if (dataList == null) return new Object[0][0];

        Object[][] dataArray = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }
        return dataArray;
    }
}

