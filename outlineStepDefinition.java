package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class outlineStepDefinition {
	WebDriver driver = null;
    @Before()
    public void setup() {
    	System.setProperty("webdriver.firefox.marionette", "C:/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.navigate().to("https://connect.qq.com/");
    }
    
	@Given("^user navigates to QQ$")
	public void goTologin() {
		//user access login page
		driver.findElement(By.className("btn-login")).click();
		driver.switchTo().frame(0);
		driver.findElement(By.id("switcher_plogin")).click();
	}

	@When("^I enter Username as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void I_enter_Username_as_and_Password_as(String arg1, String arg2) {
		// enter user name and password
		driver.findElement(By.id("u")).sendKeys(arg1);
		driver.findElement(By.id("p")).sendKeys(arg2);
		driver.findElement(By.id("login_button")).click();
	}

	@Then("^login should be unsuccessful$")
	
	public void validateRelogin() throws InterruptedException {
		// the target position is not fixed, so using for statement fo find out the correct place.
		for (int xOffset = 180; xOffset <= 275; xOffset = xOffset + 5) {
			driver.switchTo().frame(0);
			Thread.sleep(10000);
			WebElement source = driver.findElement(By.id("tcaptcha_drag_button"));
			Actions build = new Actions(driver);
			
			build.clickAndHold(source).moveToElement(source, xOffset, 0);
			try {
				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			build.release().build().perform();
			
			Thread.sleep(10000);
		    
			try {				
				String alert = driver.findElement(By.className("tcaptcha-title")).getText();
				System.out.println("the current status  is" + alert);
				if ("拖动下方滑块完成拼图" == alert) {
					System.out.println("滑块需要调整大于" + xOffset);
					System.out.println("test failed");
					
				} else  {
					System.out.println("test pass");
					//driver.switchTo().parentFrame();
					break;
				}
			} catch(Exception ex) {
				System.out.println("cought a running exception");
				System.out.println("test failed");
				// Do nothing.
			}
		}

	}
	
	@After()
	public void cleanup()
	{

		driver.close();
	}

}
