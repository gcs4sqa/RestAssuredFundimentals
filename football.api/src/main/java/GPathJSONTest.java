
import static io.restassured.RestAssured.*;

import org.junit.Test;

import config.TestConfig;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class GPathJSONTest extends TestConfig{
	
	@Test
	public void extractMapOfElementsWithFind() {
		
		Response response = get("/competitions/426/teams");
		
		Map<String, ?> allTeamDataForSingleTeam = response
				.path("teams.find { it.name == 'Leicester City FC' }");
		
		System.out.println(allTeamDataForSingleTeam);
		
	}
	
	@Test
	public void extractSingleValueWithFind() {
		
		Response response = get("teams/66/players");
		
		String certainPlayer = response.path("players.find { it.jerseyNumber == 20 }.name");
		
		System.out.println("player 20 is: "+ certainPlayer);
	}
	
	@Test
	public void extractListOfValueWithFindAll() {
		
		Response response = get("teams/66/players");
		
		List<String> playerNames = response.path("players.findAll { it.jerseyNumber > 10}.name");
		
		
			System.out.println(playerNames);
		
	}
	
	@Test
	public void extractSingleValueWithHighestNumber() {
		
		Response response = get("teams/66/players");
		
		String highestJersey = response.path("players.max { it.jerseyNumber }.name");
		
		
		System.out.println("The player with the highest jersey number is :" + highestJersey);
		
		
	}
	
	@Test
	public void extractMultipleValuesAndSumThem() {
		
		Response response = get("teams/66/players");
		
		int sumOfJerseyNumbers = response.path("players.collect { it.jerseyNumber }.sum()");
		
		System.out.println("The sum of all the jersey numbers is: " + sumOfJerseyNumbers);
		
	}
	
	@Test 
	public void extractMapOfObjectWithFindAndFindAll() {
		
		String position = "Keeper";
		String nationality = "Argentina";
		
		Response response = get("teams/66/players");
		
		Map<String, ?> playerOfCertainPosition = response.path(
				"players.findAll() { it.position == '%s'}.find { it.nationality == '%s'}",position ,nationality);
		
		System.out.println("The player to fit the bill was "+playerOfCertainPosition);
		
	}
	
	
	
	

}
