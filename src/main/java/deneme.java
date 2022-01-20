import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.List;

public class deneme {

    static WebDriver driver;
    @BeforeClass
    public static void test(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://coast.noaa.gov/digitalcoast/tools/slr.html");
    }


    @Test
    public void byAll() throws InterruptedException {

//Thread.sleep(2000);
        driver.findElement(By.xpath("//h5[contains(text(),'Mapping Methods')]")).click();
        Thread.sleep(2000);
      //  List<WebElement> mappings = driver.findElements(By.xpath("//div[@id='accordion2']//ul//li//a"));
      //  final  List<WebElement> singleMessages=driver.findElements(new ByChained(By.id("accordion2"),By.tagName("a")));
       final  List<WebElement> singleMessages1=driver.findElements(By.id("accordion2"));


        for (WebElement e :singleMessages1
             ) {
            System.out.println(e.getText());

//new ByAll(By.id("accordion2"),By.tagName("a"))
        }
    }


}
