package wrappers;

import static org.junit.Assert.assertEquals;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import java.util.List;
import java.util.Map;

public class WeatherForecast extends Utilities {

	private Response response;

	public void submit_get_request(String URL) {
		// To make get request for given URL

		response = do_get_request(URL);
	}

	public void assert_weather_state_name(String exp_weather_state, String day) {
		// To verify weather state name for coming days

		String date = get_date_based_on_day(day);
		List<Map<String, String>> consolidated_weather_lst = response.jsonPath().getList("consolidated_weather");
		for (int i = 0; i < consolidated_weather_lst.size(); i++) {
			String response_applicable_date = consolidated_weather_lst.get(i).get("applicable_date");

			if (response_applicable_date.equalsIgnoreCase(date)) {
				String response_state = consolidated_weather_lst.get(i).get("weather_state_name");
				assertEquals("Assertion failed for date : " + date, exp_weather_state, response_state);
			}
		}
	}

	public void assert_location_title(String location) {
		// To verify that response body has given title

		response.then().body("title", equalTo(location));
	}

	public void assert_location_woeid(int woeid) {
		// To verify that response body has given woied

		response.then().body("woeid", comparesEqualTo(woeid));
	}

	public void assert_status_code(int status_code) {
		// To verify that response body has given status code

		response.then().assertThat().statusCode(status_code);

	}

	public void assert_timezone(String timezone) {
		// To verify timezone in the response body

		response.then().body("timezone", equalTo(timezone));

	}

	public void assert_consolidated_weatherforecast(int no_of_days) {
		// To verify that response body provides consolidated weather forecast for given
		// number of days

		response.then().body("consolidated_weather.size()", equalTo(no_of_days));

	}

	public void assert_sources_count(int count) {
		// To verify that response body has given count of sources

		response.then().body("sources.size()", equalTo(count));

	}

}
