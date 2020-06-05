package homework_23;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class TestHashTable extends BaseTest {
    public ExcelReader excel = null;

    @Test(dataProvider = "getData")
    public void typeLogin(Hashtable<String, String> data) {

        driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("test@ukr.com");
        driver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();
        driver.findElement(By.xpath("//input[@id='customer_firstname']")).sendKeys(data.get("FirstName"));
        driver.findElement(By.xpath("//input[@id='customer_lastname']")).sendKeys(data.get("LastName"));
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(data.get("Email"));
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(data.get("Password"));

    }

    @DataProvider
    public  Object[][] getData() {
        if(excel == null) {
            excel = new ExcelReader("/Users/marinabelozerova/IdeaProjects/Homework_23/TestData.xlsx");
        }


        String sheetName = "HeshTable";
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows-1][1];

        Hashtable<String, String> table = null;

        for (int rowNum = 2; rowNum <= rows; rowNum++) {
            table = new Hashtable<String, String>();

            for (int colNum = 0; colNum < cols; colNum++) {

                table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
                data[rowNum-2][0] = table;

            }
        }

        return data;

    }

}
