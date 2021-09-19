
package qaautomation.august2021;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import qaautomation.august2021.pages.InvalidLogin;
import qaautomation.august2021.pages.LoginPage;
import qaautomation.august2021.pages.ProfilePage;

/**
 * Unit test for simple App.
 */
public class FailedAppTest extends BaseWebTest {
	/**
	 * Rigorous Test :-)
	 */
	LoginPage loginPage = new LoginPage(driver, explicitWait);
	InvalidLogin invalidLogin = new InvalidLogin(driver, explicitWait);
	ProfilePage profilePage = new ProfilePage(driver, explicitWait);

	@Test(testName = "verify login is failed", description = "The password you entered for the username is incorrect.")
	public void usernameCorrectAndpasswordIncorrect() {
		String username = "fullstackdemo";
		String password = "fullstackdemoerer";

		loginPage.login(username, password);
		String actualUser = invalidLogin.getProfileText(username);
		AssertJUnit.assertEquals(actualUser, username);
		

	}
	
	@Test(testName = "verify login is failed", description = "The password you entered for the username is incorrect.")
	public void usernameIncorrectAndpasswordCorrect() {
		String username = "fullstackdemo";
		String password = "fullstackdemoerer";

		loginPage.login(username, password);
		String actualUser = invalidLogin.getProfileText(username);
		AssertJUnit.assertEquals(actualUser, username);
		

	}

	@Test(testName = "verify login successful", description = "verify login successful")
	public void usernameCorrectAndpasswordCorrect() {
		String username = "fullstackdemo";
		String password = "fullstackdemo";

		loginPage.login(username, password);
		String actualUser = profilePage.getProfileText(username);
		AssertJUnit.assertEquals(actualUser, username);
	}

}