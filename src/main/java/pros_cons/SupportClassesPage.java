package pros_cons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SupportClassesPage  {

    /*
    Avoid making your WebElement objects public
    Use a WebElement proxy in a WebDriverWait
    You can use custom ElementLocatorFactory approaches
    You dont have to extend PageFactory
    You can annotate List of WebElement

webelementi private yaparak public bir method olusturup web ele
elementi o method icine wrp edersek abstraction yapimis
 da oluyoruz bu bize
 Additional Annotations
    @FindBys
    -use an array of @FindBy in sequence to find an element {@FindBy(...),@FindBy(..)}
  @FindAll
 - use an array of @FindBy and return all matching items
 @CacheLookup
 -if an Element never changes in the DOM then we can cache it

     */


    private final WebDriver driver;

    @FindBy(how = How.ID, using = "resend-select")
    private WebElement singleResendButton;

    @FindBy(how = How.CSS, using = "#message")
    private WebElement message;

  //  @FindBy(how = How.CLASS_NAME,using = "message")
    @FindBys({
            @FindBy(how = How.ID,using = "single"),
            @FindBy(how = How.CLASS_NAME,using = "message")
    })

    List<WebElement> singleMessages;

@CacheLookup
    @FindBy(how = How.ID,using = "history")
    private WebElement historyTitle; //baslik degismedigi icin cacheLookup kullanarak ön bellege alabiliriz
  //  bunu kullanmak selenium grid veya cloud da test kosuluoyrsa hizlandirir
    public SupportClassesPage (WebDriver driver){
        this.driver=driver;
     PageFactory.   initElements(driver,this);

    }

    public void clickResendSingleButton(){
        singleResendButton.click();
    }
    public  String waitForMessage(){
        new WebDriverWait(driver,5).
                until(ExpectedConditions.visibilityOf(message));
        return message.getText();
    }

    public int countSingleMessagesHistory(){
        return singleMessages.size();
    }
}
