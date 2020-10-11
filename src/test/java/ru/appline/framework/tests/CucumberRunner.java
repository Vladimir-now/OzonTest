package ru.appline.framework.tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"ru.appline.framework.tests.steps"},
        features = {"src/test/resources/"},
        tags = "@iphone"
)

public class CucumberRunner {
}
