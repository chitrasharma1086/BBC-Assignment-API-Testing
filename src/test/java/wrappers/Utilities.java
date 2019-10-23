package wrappers;

import static io.restassured.RestAssured.given;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import wrappers.Constants;

public class Utilities {

	public static Response do_get_request(String endpoint) {
		// To make get request for given endpoint

		RestAssured.defaultParser = Parser.JSON;
		RestAssured.baseURI = Constants.BaseURI;
		return given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).when().get(endpoint).then()
				.contentType(ContentType.JSON).extract().response();
	}

	public static String get_date_based_on_day(String day) {
		// To get date based on day

		Calendar c = Calendar.getInstance();

		if (day.equalsIgnoreCase("today")) {
			c.getTime();
		} else if (day.equalsIgnoreCase("tomorrow")) {
			c.add(Calendar.DATE, 1);
		} else if (day.equalsIgnoreCase("tomorrow+1")) {
			c.add(Calendar.DATE, 2);
		} else if (day.equalsIgnoreCase("tomorrow+2")) {
			c.add(Calendar.DATE, 3);
		}

		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd");
		String date = (String) (formattedDate.format(c.getTime()));
		return date;

	}

}
