package ru.appline.framework.tests.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.PageManager;

import java.io.File;

public class Hooks {

    @Before
    public void beforeEach() {
        InitManager.initFramework();
        File file = new File("src/main/resources/log.txt");
        file.delete();
    }

    @After
    public void afterEach() {
        PageManager.getPageManager().deletePages();
        InitManager.quitFramework(); }
}
