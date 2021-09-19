package qaautomation.august2021.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvalidLogin extends BasePage {
	
	String errorText = "//*[@id=\"post-1248\"]/div/div/div[1]/strong[2]";

	public InvalidLogin(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
	}

	public String getProfileText(String username) {
		By finalXpath = By.xpath(String.format(errorText, username));
		String actualText = getText(finalXpath);
		return actualText;
	}
}
