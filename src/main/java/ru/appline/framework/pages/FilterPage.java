package ru.appline.framework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class FilterPage extends BasePage{

    @FindBy(xpath = "//section[@data-widget=\"searchResultsFilters\"]/div[2]/div/div")
    List<WebElement> filters;

    @FindBy(xpath = "//div[text()='Применить']")
    WebElement applyButton;

    public WebElement filter(String filterTitle) {
        WebElement title = null;
        for (WebElement e: filters) {
            title = e.findElement(By.xpath(".//div[contains(@class, 'subtitle')]"));
            if(title.getText().contains(filterTitle)) {
                return e;
            }
        }
        Assert.fail("Фильтр не найден");
        return title;
    }

    public void setFilterValue(String filterTitle, String fromOrTo, String value) {
        WebElement thisFilter = filter(filterTitle);
        clicker(thisFilter);
        WebElement field = thisFilter.findElement(By.xpath("./..//p[contains(text(), '" + fromOrTo + "')]/../input"));
        fillField(field, value);
        waitLoadPage();
        sleeper(2000);
    }


    public void setFilterSwitch(String filterTitle) {
        WebElement thisFilter = filter(filterTitle).findElement(By.xpath(".//label"));
        clicker(thisFilter);
        waitLoadPage();
        sleeper(2000);
    }

    public void setFilterTick(String filterTitle, String value) {
        WebElement thisFilter = filter(filterTitle);
        if(thisFilter.findElement(By.xpath("./div[2]")).getAttribute("style").equals("display: none;")) {
            clicker(thisFilter);
        }
        List<WebElement> ticks = thisFilter.findElements(By.xpath(".//div[2]//span/label"));
        WebElement link;
        for (WebElement e: ticks) {
            link = e.findElement(By.xpath(".//div[2]/span"));
            if (link.getText().contains(value)){
                if(!link.isSelected()) clicker(link);
                waitLoadPage();
                sleeper(1000);
                return;
            }
        }


        WebElement viewAll = null;
        WebElement searchTick;
        try {
            wait.withTimeout(Duration.ofSeconds(1));
            viewAll = thisFilter.findElement(By.xpath(".//span[contains(.,'Посмотреть все')]"));
            clicker(viewAll);
        } catch (NoSuchElementException e) {
        }
        finally {
            wait.withTimeout(Duration.ofSeconds(20));
            searchTick = thisFilter.findElement(By.xpath(".//p[text()='Найти']/../input"));
            fillField(searchTick, value);
            List<WebElement> findTicks = thisFilter.findElements(By.xpath(".//a"));
            for (WebElement e: findTicks) {
                link = e.findElement(By.xpath(".//div[2]/span"));
                if (link.getText().contains(value)){
                    if(!link.isSelected()) clicker(link);
                    waitLoadPage();
                    sleeper(1000);
                    return;
                }
            }
        }
    }



    public void apply() {
        clicker(applyButton);
        sleeper(1000);
    }





}
