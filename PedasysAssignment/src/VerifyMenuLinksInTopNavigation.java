import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyMenuLinksInTopNavigation {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\smainkatte\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		//Launching website
		driver.get("https://www.growthengineering.co.uk/");
		driver.manage().window().maximize();

		//Get list of web-elements with tagName  - a
		List<WebElement> allLinks = driver.findElements(By.xpath("*//div/nav[1]/ul/li//child::a"));

		//Traversing through the list and printing its text along with link address
		for(WebElement link:allLinks){
			String url = link.getAttribute("href");
			System.out.println(link.getText() + " - " + url);

			// Store the current window handle
			String winHandleBefore = driver.getWindowHandle();
			
			// Perform the click operation that opens new window			
			js.executeScript("window.open('');");

			// Switch to new window opened
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}

			// Perform the actions on new window
			driver.get(url);
			System.out.println(driver.getTitle());
			driver.getPageSource();
			
			// Close the new window, if that window no more required
			driver.close();

			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);

			// Continue with original browser (first window)

		}

		//close the browser
		driver.quit();





	}

}
