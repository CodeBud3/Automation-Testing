package utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "excelData")
    public static Object[][] provideExcelData(Method method) {
        String sheetName = method.getDeclaringClass().getSimpleName(); // e.g. LoginTests
        String functionName = method.getName(); // e.g. testInvalidLogin
        return ExcelDataManager.getTestData(sheetName, functionName);
    }
}
