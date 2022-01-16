import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
   final WebElement message=driver.findElement(By.cssSelector("#single-list li.message"));

    }


    @Test
    public void explicitWait(){
        final WebElement resendButton=driver.findElement(By.id("resend-select"));
        resendButton.click();
    WebDriverWait wait=   new WebDriverWait(driver,10);
            WebElement message=   wait. until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#single-list li.message")));// it will be wait until the particular condition has been satisfied


      //  final WebElement message=driver.findElement(By.cssSelector("#single-list li.message"));
        Assert.assertTrue(message.getText().startsWith("Received message"));
    }



    @Test
    public void explicitWaitUsingCustomExpectedCondition(){
        final WebElement resendButton=driver.findElement(By.id("resend-select"));
        resendButton.click();
        //TODO : ideally we should wait until the historyMessages increase in number

new WebDriverWait(driver,10).until(historyMessagesIncreaseInNumber());
     final WebElement message=driver.findElement(By.cssSelector("#single-list li.message"));
     Assert.assertTrue(message.getText().startsWith("Received message"));
    }

    private ExpectedCondition<Boolean> historyMessagesIncreaseInNumber() {

        return new ExpectedCondition<Boolean>() {
        private    int initialCount=driver.findElements(By.cssSelector("li.message")).size();

            @Override
            public Boolean apply( WebDriver driver) {
                int currentCount=driver.findElements(By.cssSelector("li.message")).size();


                return currentCount>initialCount;
            }
        };
    }
}
