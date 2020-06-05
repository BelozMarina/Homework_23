package homework_23;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataProvider extends BaseTest {

    @Test(dataProvider = "invalidCredentials")
    public void typeLogin(String email, String password) {

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
        String error = driver.findElement(By.xpath("//*[@id='center_column']/div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("There is 1 error\n" +
                "Authentication failed.", error);
    }


    @DataProvider
    public Object[][] invalidCredentials() {
        return new Object[][] {
                {"admin@test.com", "qwerty"},
                {"user@test.com", "987654"},
        };
    }
}
