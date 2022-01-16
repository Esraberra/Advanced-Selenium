package pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLInputElement;

public class SupportClassesPage extends PageFactory {
    private  final WebDriver driver;

   @FindBy(how = How.ID, using = "resend-select")
    public WebElement singleResendButton;

   @FindBy(how = How.ID,using = "message")
    public WebElement message;

    public SupportClassesPage(WebDriver driver) {
        this.driver=driver;
    }

    public String waitForMessage() {
        new WebDriverWait(driver, 20).
                until(ExpectedConditions.
                        visibilityOfElementLocated(By.id("message")));

        return message.getText();
    }
}
