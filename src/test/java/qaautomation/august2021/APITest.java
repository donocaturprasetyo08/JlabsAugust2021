//package qaautomation.august2021;
//
//import static org.testng.Assert.assertEquals;
//
//import org.testng.annotations.Test;
//
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//
///**
// * Unit test for simple App.
// */
//public class APITest extends BaseAPITest {
//	/**
//	 * Rigorous Test :-)
//	 */
//	String token = "";
//
//	@Test(testName = "verify sign in is success", description = "sign in will get the token", priority = 1)
//	public void testLoginAPI() {
//		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
//		String payload = "{\"email\": \"jogidemo321@gmail.com\", \"password\": \"builder123\"}";
//		//RestAssured.given().contentType("application/json").body(payload).when().post("/users/sign_in").then()
//				//.assertThat().statusCode(200);
//		Response response = RestAssured.given().contentType("application/json").body(payload).when()
//				.post("/users/sign_in");
//		assertEquals(response.statusCode(), 200);
//		token = response.jsonPath().get("user.authtoken");
//	}
//
//	@Test(priority = 2)
//	public void userAPI() {
//		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
//		//RestAssured.given().contentType("application/json").header("authtoken", token).when().get("/user").then()
//		//		.assertThat().statusCode(200);
//		Response response = RestAssured.given().contentType("application/json").header("authtoken", token).when()
//				.get("/user");
//		assertEquals(response.statusCode(), 200);
//	}
//	
//	@Test(priority = 3)
//	public void dashboardAPI() {
//		RestAssured.baseURI = "https://api-staging-builder.engineer.ai";
//		Response response = RestAssured.given().contentType("application/json")
//				.params("status", "completed").header("authtoken", token).when()
//				.get("/build_cards");
//		assertEquals(response.statusCode(), 200);
//		System.out.println(response.body().asPrettyString());
//	}
//
//}
package qaautomation.august2021;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import qaautomation.august2021.apis.APIEndpoints;
import qaautomation.august2021.utils.APIUtility;
import qaautomation.august2021.utils.DataUtility;

/**
 * Unit test for simple App.
 */
public class APITest extends BaseAPITest {
	/**
	 * Rigorous Test :-)
	 */

	@Test(priority = 2)
	public void userAPI() {
		Response response = given().spec(loginJsonSpec).when().get(APIEndpoints.user);
		APIUtility.verifyStatusCode(response);
		assertEquals(APIUtility.verifyStatusCode(response), true);
	}

	@Test(priority = 3)
	public void dashboardAPI() {
		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get(APIEndpoints.dashboard);
		APIUtility.verifyStatusCode(response);
	}

	@Test
	public void configAPI() {
		Response response = given().spec(loginJsonSpec).when().get(APIEndpoints.config);
		APIUtility.verifyStatusCode(response);
		AssertJUnit.assertEquals(APIUtility.verifyStatusCode(response), true);
	}
	
	@Test
	public void fakerEmail() {
		Faker faker = new Faker();
		System.out.println(faker.name().username() + "@gmail.com");
		System.out.println("+" + faker.phoneNumber().cellPhone());
		System.out.println(faker.name().firstName());
		System.out.println(faker.name().lastName());
	}
	
	@Test
	public void incorrectLoginAPI() {
		String loginFailedPayload = DataUtility.getDataFromExcel("Payloads", "IncorrectLoginPayload")
				.replace("$.username", "a@gmail.com").replace("$.password", "12344");
		Response response = given().spec(commonJsonSpec).body(loginFailedPayload).when().post(APIEndpoints.login);
		assertNotEquals(response.getStatusCode(), 200);
		AssertJUnit.assertEquals(response.getStatusCode(), 422);
	}
	
	//check type data dengan schema
	@Test
	public void dashboardAPIWithSchema() {
		Response response = given().spec(loginJsonSpec).param("status", "completed").when().get(APIEndpoints.dashboard);
		APIUtility.verifyStatusCode(response);
		response.then().assertThat()
				.body(matchesJsonSchema(DataUtility.getDataFromExcel("Schemas", "DashboardAPISchema")));
	}

}