import static io.restassured.RestAssured.*;

import org.junit.Test;

import config.EndPoint;
import config.TestConfig;

public class MyFirstTest extends TestConfig {
	
	@Test
	public void fistTest() {
		given()
		.log().all()
		//.log().ifValidationFails()
		.when().get("videogames/2")
		.then()
		.log().all();
		//.statusCode(200);  this line is remarked out as the 
		//expected statuscode is now in the test config.
	}
	
	@Test
	public void getAllVideoGames() {
		when().get(EndPoint.VIDEOGAMES);
		
	}

}
