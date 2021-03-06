package pageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageFactoryTest  {
    static WebDriver driver;
    @BeforeClass
    public static void createDriver(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://eviltester.github.io/supportclasses/#2000");

    }
    @Test
    public void sendMessage(){
        SupportClassesPage page=new SupportClassesPage(driver);
        page.singleResendButton.click();
        Assert.assertEquals("Received message: selected 1",page.message.getText());
    }

    @Test
    public void sendMessageWithWaitInPageObject(){
        SupportClassesPage page=new SupportClassesPage(driver);
        page.singleResendButton.click();
        Assert.assertEquals("Received message: selected 1", page.waitForMessage());
    }

    @AfterClass
    public static void closeDriver(){


        driver.quit();
    }
}
