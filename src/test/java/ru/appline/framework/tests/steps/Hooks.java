package ru.appline.framework.tests.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import ru.appline.framework.managers.InitManager;
import ru.appline.framework.managers.PageManager;

public class Hooks {

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach(Scenario scenario) {
        PageManager.getPageManager().deletePages();
        InitManager.quitFramework(); }
}
