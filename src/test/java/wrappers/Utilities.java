package wrappers;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class Utilities {

	public static Response doGetRequest(String endpoint) {
		// To make get request for given endpoint
		RestAssured.defaultParser = Parser.JSON;
		RestAssured.baseURI = "https://www.metaweather.com/api";
		return given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).when().get(endpoint).then()
				.contentType(ContentType.JSON).extract().response();
	}

}
