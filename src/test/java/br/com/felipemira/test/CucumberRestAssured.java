package br.com.felipemira.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features/restAssured.feature"}, glue = { "br.com.felipemira.steps" },
        tags = {"@teste_api_rest_assured"},
        format = {"pretty", "html:target/reports/cucumber/html",
        "json:target/cucumber.json", "usage:target/usage.jsonx", "junit:target/junit.xml"})
public class CucumberRestAssured {
}
