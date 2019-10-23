package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wrappers.WeatherForecast;

public class WeatherForecastSteps {

	WeatherForecast weatherForecast = new WeatherForecast();

	@When("^I submit the GET request to call endpoint \"([^\"]*)\"$")
	public void i_submit_the_GET_request_to_call_endpoint(String URL) {
		// To submit get request for given endpoint
		weatherForecast.submit_get_request(URL);
	}

	@Then("^I see the weather state is \"([^\"]*)\" for the day \"([^\"]*)\"$")
	public void i_see_the_weather_state_is_for_the_day(String weather_state, String day) {
		// To verify weather state for given day
		weatherForecast.assert_weather_state_name(weather_state, day);
	}

	@Then("^I see that response body has correct title displayed as \"([^\"]*)\"$")
	public void i_see_that_response_body_has_correct_title_displayed_as(String country_name) {
		// To verify title for given location/country
		weatherForecast.assert_location_title(country_name);
	}

	@Then("^I see that response body has correct woeid (\\d+) for respective location$")
	public void i_see_that_response_body_has_correct_woeid_for_respective_location(int woeid) {
		// To verify woied (where on earth id)
		weatherForecast.assert_location_woeid(woeid);
	}

	@Then("^I see that response status code is (\\d+)$")
	public void i_see_that_response_status_code_is(int status_code) {
		// To verify status code
		weatherForecast.assert_status_code(status_code);
	}

	@Then("^I see that response body has correct timezone \"([^\"]*)\" for respective location$")
	public void i_see_that_response_body_has_correct_timezone_for_respective_location(String timezone)
			throws Throwable {
		// To verify timezone
		weatherForecast.assert_timezone(timezone);
	}

	@Then("^I see that response body has consolidated weather forecast for (\\d+) days$")
	public void i_see_that_response_body_has_consolidated_weather_forecast_for_days(int days) {
		// To verify response has consolidated weather forecast for given number of days
		weatherForecast.assert_consolidated_weatherforecast(days);
	}

	@Then("^I see that response body has total (\\d+) sources$")
	public void i_see_that_response_body_has_total_sources(int count) {
		// To verify response body has total number of sources as expected
		weatherForecast.assert_sources_count(count);
	}

}
