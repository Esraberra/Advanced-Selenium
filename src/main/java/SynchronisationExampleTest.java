import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SynchronisationExampleTest {

    static WebDriver driver;




    @BeforeClass
    public static void test(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://eviltester.github.io/supportclasses/#2000");
    }


    @Test
    public void implicitWait(){
        final WebElement resendSingle=driver.findElement(By.id("resend-select"));
        resendSingle.click();
        driver.manage().timeouts().implicitlyWait(5000,TimeUnit.SECONDS);
        //It's a pooling mechanism for the element, and if the element doesn't appear within 5 seconds we receive a time out exception.
   final WebElement message=driver.findElement(By.cssSelector("#single-list li.message"))
    }
}
