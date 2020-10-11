package ru.appline.framework.tests.steps;

import io.cucumber.java.ru.Когда;
import io.qameta.allure.Allure;
import ru.appline.framework.managers.PageManager;

import java.io.IOException;

import static ru.appline.framework.utils.Log.getBytesAnnotationWithArgs;

public class Steps {
    private PageManager app = PageManager.getPageManager();

    @Когда("^Загружена стартовая страница$")
    public void getInitialPage(){
        app.getStartPage();
    }

    @Когда("^Поиск товара '(.*)'$")
    public void search(String nameItem) {
        app.getStartPage().search(nameItem);
    }

    @Когда("^Выставляем фильтры$")
    public void goToFiltersPage() {
        app.getSearchPage().goToFiltersPage();
    }

    @Когда("^Выбираем фильтр '(.*)' и выставляем значение '(.*)' '(.*)'$")
    public void setFilterValue(String filterTitle, String fromOrTo, String value) {
        app.getFilterPage().setFilterValue(filterTitle, fromOrTo, value);
    }

    @Когда("^Устанавливаем фильтр '(.*)'$")
    public void setCheckboxSwitch(String nameCheckBox) {
        app.getFilterPage().setFilterSwitch(nameCheckBox);
    }

    @Когда("^Устанавливаем в фильтре '(.*)' галочку возле '(.*)'$")
    public void setCheckboxTick(String filterTitle, String value) {
        app.getFilterPage().setFilterTick(filterTitle, value);
    }

    @Когда("^Применяем выбранные фильтры$")
    public void apply(){
        app.getFilterPage().apply();
    }

    @Когда("^Добавляем в корзину '(.*)' четных товаров из списка$")
    public void addEvenItems(String n) {
        app.getSearchPage().addEvenItems(n);
        try {
            Allure.getLifecycle().addAttachment("log","text/plain",null,getBytesAnnotationWithArgs());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Когда("^Переходим в Корзину$")
    public void goToBasket() {
        app.getSearchPage().goToBasket();
    }

    @Когда("^Удаляем все товары из корзины$")
    public void cleanBasket() {
        app.getBasketPage().cleanBasket()
        ;
    }
}

