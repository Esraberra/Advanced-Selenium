package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import java.lang.reflect.Field;

public class VisibleAjaxElementFactory implements ElementLocatorFactory {
    private final WebDriver driver;
    private final int timeOut;

    public VisibleAjaxElementFactory(WebDriver driver, int timeOutInSeconds) {
        this.driver=driver;
        this.timeOut=timeOutInSeconds;
    }

    @Override
    public ElementLocator createLocator(Field field) {

        return new VisibleAjaxElementLocator(driver,field,timeOut);
    }

    private class VisibleAjaxElementLocator extends AjaxElementLocator {
        public VisibleAjaxElementLocator(WebDriver driver, Field field, int timeOut) {
            super(driver,field,timeOut);
        }

        @Override
        protected boolean isElementUsable(WebElement element) {
            if (element==null){
                return false;
            }
            return element.isDisplayed()&&element.isEnabled();



        }
    }
}
