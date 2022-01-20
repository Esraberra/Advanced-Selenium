package pros_cons;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class PageFactoryTest {
   static WebDriver driver;
    @Test
    public void sendMessageWithWaitInPageObject(){

        SupportClassesPage page=new SupportClassesPage(driver);

        page.clickResendSingleButton();
        Assert.assertEquals("Received message: selected 1",page.waitForMessage());
  Assert.assertEquals(1,page.countSingleMessagesHistory());
    }
}
