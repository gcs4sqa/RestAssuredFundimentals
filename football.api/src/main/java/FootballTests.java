import static io.restassured.RestAssured.*;

import org.junit.Test;

import config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;



public class FootballTests extends TestConfig{
	
	@Test
	public void getAllCompetitonsOneSeason() {
		given()
			.spec(football_RequestSpec)
			.queryParam("season", 2016)
		.when()
		.get("competitions/")
		.then();
	}
	
	@Test
	public void getTeamCount() {
		given()
			.spec(football_RequestSpec)
		.when()
			.get("competitions/426/teams")
		.then()
			.body("count", equalTo(20));
	}
	
	
	@Test
	public void getFirstTeamName() {
		given()
		.spec(football_RequestSpec)
	.when()
		.get("competitions/426/teams")
	.then()
		.body("teams.name[0]", equalTo("Hull City FC"));
	}
	
	@Test
	public void getAllTeamData() {
		String ResponseBody = given().spec(football_RequestSpec).when().get("competitions/426/teams").asString();
		System.out.println(ResponseBody);		

		
	}
	
	@Test
	public void getAllTeamDataCheckFirst() {
		Response response = 
				given()
					.spec(football_RequestSpec)
				.when()
					.get("competitions/426/teams")
				.then()
					.contentType(ContentType.JSON)
					.extract().response();
		
		String JSONResponse = response.asString();
		
	}
	
	@Test
	public void getAllHeaders() {
		
		Response response = 
				given()
					.spec(football_RequestSpec)
				.when()
					.get("competitions/426/teams")
				.then()
					.contentType(ContentType.JSON)
					.extract().response();
		
		Headers headers = response.getHeaders();
		
		String contentType = response.getHeader("Content-Type");
		System.out.println("the contentType is: "+ contentType);
		
		
	}
	
	@Test
	public void extractFirstTeamName() {
		
		String firstTeamName = given().spec(football_RequestSpec).when().get("competitions/426/teams").jsonPath().getString("teams.name[1]");
	
	
		System.out.println("the name of the first team is: "+ firstTeamName);
	}
	
	@Test
	public void extractAllTeamNames() {
		
		Response response = given()
				.spec(football_RequestSpec)
			.when()
				.get("competitions/426/teams")
			.then()
				.contentType(ContentType.JSON)
				.extract().response();
		
		List<String> teamNames = response.path("teams.name");
		
		for (String teamName : teamNames) {
			System.out.println(teamName);
		}
		
	}
	
	
	

}
