package ru.appline.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends BasePage{

    @FindBy(xpath = "//div[text()='Все фильтры']")
    WebElement goToFilters;

    @FindBy(xpath = "//div[contains(@style, 'grid-column')]")
    List<WebElement> foundItems;

    @FindBy(xpath = "//a[@href='/cart']")
    WebElement basket;

    public void goToFiltersPage() {
        clicker(goToFilters);
    }

    public void addEvenItems(String n) {
        int maxPrice = 0;
        String itemWithMaxPrice = "";

        WebElement itemTitle;
        WebElement itemPrice;
        WebElement buttonTitle;
        int count = 0;
        for (int i = 1; i < foundItems.size(); i+=2) {
            scrollTo(foundItems.get(i));

            if (!n.equalsIgnoreCase("Все")) {
                int nInt = Integer.parseInt(n);
                if(count == nInt) break;
            }

            buttonTitle = foundItems.get(i)
                    .findElement(By.xpath(".//div[text()='В корзину' or text()='Узнать о поступлении']"));
            if(buttonTitle.getText().contains("Узнать о поступлении")) continue;
            clicker(buttonTitle.findElement(By.xpath("./../..")));

            //Записываем добавленные в корзину товары  в файл
            itemTitle = foundItems.get(i).findElement(By.xpath(".//div[not(@style)]/a[contains(@class, 'tile-hover-target')]"));
            itemPrice = foundItems.get(i).findElement(By.xpath(".//a[contains(@class, 'tile-hover-target')]//span[contains(., '₽')]"));
            String str = itemTitle.getText() + " Цена: " + itemPrice.getText().replaceAll("[^0-9]","");
            fileWriter(str, "src/main/resources/log.txt");

            //Ищем товар с максимальной ценой и в конце цикла записываем в файл
            int currentPrice = Integer.parseInt(itemPrice.getText().replaceAll("[^0-9]",""));
            if(maxPrice < currentPrice) {
                maxPrice = currentPrice;
                itemWithMaxPrice = "Товар с наибольшей ценой: " + str;
            }

            count++;
        }
        fileWriter(itemWithMaxPrice, "src/main/resources/log.txt");

    }

    public void goToBasket() {
        clicker(basket);
    }
}
