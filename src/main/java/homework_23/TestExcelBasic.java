package homework_23;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestExcelBasic extends BaseTest {
    ExcelReader excel = null;


    @Test(dataProvider = "getData")
    public void typeLogin(String email, String password) {

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
        String error = driver.findElement(By.xpath("//*[@id='center_column']/div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("There is 1 error\n" +
                "Authentication failed.", error);
    }


    @DataProvider
    public Object[][] getData() {

        if (excel == null) {
            excel = new ExcelReader("/Users/marinabelozerova/IdeaProjects/Homework_23/TestData.xlsx");
        }

        String sheetName = "TestData2";
        int rows = excel.getRowCount(sheetName);
        int columns = excel.getColumnCount(sheetName);
        Object[][] data = new Object[rows-1][columns];

        for (int rowNum = 2; rowNum <= rows; rowNum++) {
            for (int colNum = 0; colNum < columns; colNum++) {
                data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
            }
        }
        return data;
    }
}
