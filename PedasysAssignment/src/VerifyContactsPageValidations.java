import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyContactsPageValidations {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\smainkatte\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get("https://www.growthengineering.co.uk/");
		driver.manage().window().maximize();

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		String url = driver.findElement(By.xpath("*//a/span[contains(text(),'Contact Us')]//parent::a")).getAttribute("href");
		driver.findElement(By.xpath("*//a/span[contains(text(),'Contact Us')]")).click();
		assertEquals(url, driver.getCurrentUrl());

		WebElement btnSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("*//input[@type='submit']")));
		js.executeScript("window.scrollBy(0,1000)");
		btnSubmit.click();

		WebElement txtValidationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("*//label[contains(text(),\"Please complete all required fields.\")]")));
		if( txtValidationMsg.getText().equals("Please complete all required fields.") ) {
			System.out.println(txtValidationMsg.getText());}
		else {
			System.out.println("Required field form validation is not working on the contact page");
		}

		driver.quit();


	}

}
