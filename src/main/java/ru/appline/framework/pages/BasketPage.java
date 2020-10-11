package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends BasePage{

    @FindBy(xpath = "//span[contains(text(), 'товаров')]")
    WebElement amount;

    @FindBy(xpath = "//label[contains(text(), 'Выбрать все')]/label/div/input")
    WebElement tickSelectAll;

    @FindBy(xpath = "//span[contains(text(), 'Удалить выбранные')]")
    WebElement deleteSelected;

    @FindBy(xpath = "//div[text()='Удалить']")
    WebElement confirmDeletion;

    @FindBy(xpath = "//h1")
    WebElement header;

    private void checkAmountItems(String n) {
        String[] arr = amount.getText().split(" ");
        Assert.assertEquals("Количество товаров в корзине не соответствует", n, arr[0]);
    }

    public void cleanBasket() {
        checkAmountItems("8");
        if (!tickSelectAll.isSelected()) clicker(tickSelectAll);
        clicker(deleteSelected);
        clicker(confirmDeletion);
        waitLoadPage();
        Assert.assertEquals("Корзина не отчистилась","Корзина пуста", header.getText());
    }
}
