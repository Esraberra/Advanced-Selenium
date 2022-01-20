package locatorStrategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SupportPage extends PageFactory {
    private final WebDriver driver;
    @FindBy(how= How.ID, using = "resend-select")

    public WebElement singleResendButton;

    public SupportPage(WebDriver driver){
        this.driver=driver;
        initElements(driver, this);

    }
}
