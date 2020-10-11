package ru.appline.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.PageManager;

import java.io.FileWriter;
import java.io.IOException;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class BasePage {

    protected PageManager app = PageManager.getPageManager();
    protected WebDriverWait wait = new WebDriverWait(getDriver(), 20, 1000);
    protected JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void scrollTo (WebElement element) {
        if (!element.isDisplayed()) javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected  void cleanField (WebElement field) {
        field.sendKeys(Keys.CONTROL,"a");
        field.sendKeys(Keys.DELETE);
    }

    protected void clicker (WebElement element) {
        scrollTo(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected void clickEnter (WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    protected void fillField(WebElement e, String value) {
        clicker(e);
        cleanField(e);
        e.sendKeys(value);
        clickEnter(e);
    }

    protected void waitLoadPage() {
        wait.until(
                driver -> javascriptExecutor.executeScript("return document.readyState")
                        .equals("complete"));
    }

    protected void sleeper(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void fileWriter(String log, String path) {
        try(FileWriter writer = new FileWriter(path, true)) {
            writer.write(log);
            writer.append('\n');
            writer.flush();
        } catch(IOException ex){
            ex.printStackTrace();
        }
    }


}
