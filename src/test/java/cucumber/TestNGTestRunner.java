package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumer-> TestNGRunner, junit we need help of testng to run cucumber
@CucumberOptions(features="src/test/java/cucumber", glue="rahulsettyacedny.stepDefinitions",
monochrome=true,tags= "@Regression", plugin= "html:target/cucumber.html")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
