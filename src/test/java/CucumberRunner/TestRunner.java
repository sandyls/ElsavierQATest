package CucumberRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = "src/test/java/features",
		glue = "StepDefinitions",//passing package name for stepdefs
		tags= {"@wip"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
