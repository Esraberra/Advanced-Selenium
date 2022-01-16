import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class FluentWaitExampleTest {

    /*
        WebDriverWait is built on top of of FluentWait,
        we can use this to wait on anything, not just WebDriver
     */
  static   WebDriver driver;
    @BeforeClass
    public static void createDriver(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://eviltester.github.io/supportclasses/#2000");

    }


    @Test
    public void explicitWaitIsFluent(){
        final WebElement resendButton=driver.findElement(By.id("resend-select"));
        resendButton.click();
        //todo: WebdriverWait is built on FluentWait so we have a lot of control over wait
    //    TOD0 : customise timeout message, poll every 50 miliseconds, and ignore StaleElementReferenceException.class
final WebElement message=new WebDriverWait(driver,5).
        withMessage("couldnt find the message").
        pollingEvery(Duration.ofMillis(50)).ignoring(StaleElementReferenceException.class).
        until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#single-list li.message")));

        Assert.assertTrue(message.getText().startsWith("Received message"));


    }


    @Test
    public void usingFluentWait(){
        final WebElement resendButton=driver.findElement(By.id("resend-select"));
        resendButton.click();
        //todo : I could build a custom wait around the element I want to work with
        WebElement singleListParent=driver.findElement(By.id("single-list"));
        FluentWait wait=new FluentWait<WebElement>(singleListParent).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofMillis(500)).
                withMessage("Could not find any new messages");

wait.until(
        new HistoryMessagesIncreaseInNumber(0)
);

          final WebElement message=driver.findElement(By.cssSelector("#single-list li.message"));
      Assert.assertTrue(message.getText().startsWith("Received message"));
    }


    private class HistoryMessagesIncreaseInNumber implements Function<WebElement, Boolean> {
        private final int initialCount;

        public HistoryMessagesIncreaseInNumber(int initialCount) {
            this.initialCount=initialCount;
        }

        @Override
        public Boolean apply( final WebElement webElement ) {
         int currentCount =  webElement.findElements(By.cssSelector("li.message")).size();
            return currentCount>initialCount;
        }
    }




    @AfterClass
    public static void closeDriver(){
        driver.quit();
    }
}
