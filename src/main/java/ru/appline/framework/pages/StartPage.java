package ru.appline.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

    @FindBy(xpath = "//input[@name='search']")
    WebElement search;

    public void search(String nameItem) {
        fillField(search, nameItem);
    }
}
