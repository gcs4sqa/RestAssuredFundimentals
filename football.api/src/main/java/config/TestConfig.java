package config;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.lessThan;

public class TestConfig {
	
	public static RequestSpecification videoGame_RequestSpec;
	public static RequestSpecification football_RequestSpec;
	public static ResponseSpecification ResponseSpec;
	
	@BeforeClass
	public static void setup() {
		
		RestAssured.proxy("localhost", 8888);
		
		videoGame_RequestSpec = new RequestSpecBuilder()
				.setBaseUri("http://localhost")
				.setPort(8080)
				.setBasePath("/app/")
				.addHeader("content-type", "application/json")
				.addHeader("Accept", "application/json")
				.build();
		
		
		
	
		
		football_RequestSpec = new RequestSpecBuilder()
				.setBaseUri("http://api.football-data.org")
				.setBasePath("/v1/")
				.addHeader("X-Auth-Token", "3d78f52315bd4db387610f1db48a2d63")
				.addHeader("X-Response-Control", "minified")
				
				.build();
		
		//RestAssured.requestSpecification = videoGame_RequestSpec;
		RestAssured.requestSpecification = football_RequestSpec;
		
		
		
		//this is a common response spec
		ResponseSpec = new ResponseSpecBuilder()
			.expectStatusCode(200)
			//.expectResponseTime(lessThan(10000L))
			.build();
		
		RestAssured.responseSpecification = ResponseSpec;
		
	}
	
	

}
