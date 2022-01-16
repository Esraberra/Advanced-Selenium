import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CreateLocatorsClass {


    static WebDriver driver;




    @BeforeClass
    public static void test(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://eviltester.github.io/supportclasses/");
    }


    @Test
    public void FindByClassAttribute(){
        final WebElement resendSingle=driver.findElement(By.id("resend-select"));
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();
        resendSingle.click();
//TODO: create a custom ByAttribute locator for any attribute i.e. "class"
        final WebElement resend=driver.findElement(By.id("resend-multi"));
        resend.click();
        resend.click();

       List<WebElement> messages=driver.findElements( new ByAttributeValue("class","message"));
        Assert.assertEquals(6,messages.size());
    }


    @Test
    public void dataLocatorAttribute(){

       // WebElement instructions=driver.findElement(By.cssSelector("[data-locator='instructions']"))

     WebElement instructions=driver.findElement(new ByAttributeValue("data-locator","instructions"));
     Assert.assertEquals("Select an item from the list to show the response message.",instructions.getText());
    }
    @Test
    public void dataLocatorFindBy(){
        //todo create a ByGlobalDataAttribute
        WebElement title=driver.findElement( new ByGlobalDataAttribute("title","historytitle"));
    }

    private class ByAttributeValue extends By {
        private final String name;
        private final String value;
        public ByAttributeValue(String attributeName, String attributeValue) {
            this.name=attributeName;
            this.value=attributeValue;
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            return context.findElements(By.cssSelector(String.format("[%s='%s']",name,value)));
        }
        //we have created a private inner class that is implemented form the Searccontext and we have overreid the Findelements method and reedited
    }

    private class ByGlobalDataAttribute extends By {
        private final String name;
        private final String value;

        public ByGlobalDataAttribute(String dataAttributeName, String valueToMatch) {
            this.name=dataAttributeName;
            this.value=valueToMatch;
        }

        @Override
        public List<WebElement> findElements(SearchContext context) {
            return context.findElements(By.cssSelector(String.format("[data-%s='%s']",name,value)));
        }
    }
}
