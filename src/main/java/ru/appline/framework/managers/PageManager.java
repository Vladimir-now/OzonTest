package ru.appline.framework.managers;


import com.sun.org.apache.regexp.internal.RE;
import ru.appline.framework.pages.*;

import java.util.ArrayList;
import java.util.List;

import static ru.appline.framework.managers.DriverManager.getDriver;
import static ru.appline.framework.utils.PropConst.APP_URL;

public class PageManager {

    private List<BasePage> pages = new ArrayList<>();

    private static PageManager pageManager;

    private StartPage startPage;

    private SearchPage searchPage;

    private FilterPage filterPage;

    private BasketPage basketPage;


    private PageManager() {
    }

    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
            pages.add(startPage);
        }
        return startPage;
    }

    public SearchPage getSearchPage() {
        if (searchPage == null) {
            searchPage = new SearchPage();
            pages.add(searchPage);
        }
        return searchPage;
    }

    public BasketPage getBasketPage() {
        if(basketPage == null) {
            basketPage = new BasketPage();
            pages.add(basketPage);
        }
        return basketPage;
    }

    public FilterPage getFilterPage() {
        if(filterPage == null) {
            filterPage = new FilterPage();
            pages.add(filterPage);
        }
        return filterPage;
    }

    public void deletePages() {
        for (int i = 0; i < pages.size(); i++) {
            pages.remove(i);
        }
    }
}




