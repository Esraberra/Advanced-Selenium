import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

public class Support_Class {

static WebDriver driver;




@BeforeClass
public static void test(){
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
driver.get("https://eviltester.github.io/supportclasses/");
}


@Test
    public void ByIdOrName(){
    WebElement idButton=driver.findElement(By.id("resend-select"));
    Assert.assertEquals("Resend Single Option Message",idButton.getText());
    WebElement nameButton=driver.findElement(By.name("resend-select"));
    Assert.assertEquals("Resend Multi Option Message",nameButton.getText());

    WebElement button=driver.findElement(new ByIdOrName("resend-select"));
    Assert.assertEquals(idButton,button);
    Assert.assertEquals(nameButton,button);

    // ByIdOrName findElements returns all id and name matches
    List<WebElement> buttons = driver.findElements(new ByIdOrName("resend-select"));
    Assert.assertTrue(buttons.contains(nameButton));
}

@Test
    public void byAll(){
    List<WebElement> buttons = driver.findElements(new ByAll(By.id("resend-select"),By.name("resend-select")));
}

@Test
    public void byAllChained(){

    final WebElement resendSingle=driver.findElement(By.id("resend-select"));
    resendSingle.click();
    resendSingle.click();
    resendSingle.click();
    resendSingle.click();

    final WebElement resend=driver.findElement(By.id("resend-multi"));
    resend.click();
    resend.click();
    //TODO:make this more specific to only find messages under a 'list'
    //we are building up a chain of locators
    final List<WebElement> allMessages=driver.findElements(new ByChained(By.name("list"),By.className("message")));
Assert.assertEquals(6,allMessages.size());

//then just single message list
    final  List<WebElement> singleMessages=driver.findElements(new ByChained(By.id("single"),By.name("list"),By.className("message")));
    final  List<WebElement> multiMessages1=driver.findElements(new ByChained(By.id("multi"),By.name("list"),By.className("message")));

    Assert.assertEquals(4,singleMessages.size());
    Assert.assertEquals(2,multiMessages1.size());
}

}
