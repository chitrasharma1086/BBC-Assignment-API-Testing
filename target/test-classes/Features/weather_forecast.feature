Feature: Open MetaWeather API testing for BBC assignment

  @API_Test
  Scenario Outline: Verify weather forecast for London for upcoming 4 days
    When I submit the GET request to call endpoint "/location/44418"
    Then I see that response status code is 200
    And I see the weather state is "<weather_state>" for the day "<day>"

    Examples: 
      | day        | weather_state |
      | today      | Heavy Cloud   |
      | tomorrow   | Showers       |
      | tomorrow+1 | Clear         |
      | tomorrow+2 | Light Cloud   |

  @API_Test
  Scenario Outline: Verify location method
    When I submit the GET request to call endpoint "<endpoint>"
    Then I see that response status code is 200
    Then I see that response body has correct title displayed as "<country_name>"
    And I see that response body has correct woeid <woeid> for respective location
    And I see that response body has correct timezone "<timezone>" for respective location
    And I see that response body has consolidated weather forecast for 6 days
    And I see that response body has total 7 sources

    Examples: 
      | endpoint           | country_name  | woeid   | timezone      |
      | /location/44418    | London        |   44418 | Europe/London |
      | /location/2487956/ | San Francisco | 2487956 | US/Pacific    |

  @manual
  Scenario Outline: Verify location search method for full text as "london"
    When I submit the GET request to call endpoint "/location/search/?query=london"
    Then I see that response status code is 200
    And I see that response body contains following

    Examples: 
      | title  | location_type | woeid | latt_long     |
      | London | City          | 44418 | Europe/London |

  @manual
  Scenario: Verify location search method for partial text as "san"
    When I submit the GET request to call endpoint "/location/search/?query=san"
    Then I see that response status code is 200
    And I see that response body contains "11" locations
    And I see that all values contains "san" for the key "title" in response body

  @manual
  Scenario Outline: Verify location day method for london
    When I submit the GET request to call endpoint "/location/44418/2019/10/22/"
    Then I see that response status code is 200
    And I see that response body has value "2019-10-22" for key "applicable_date"
    And I see that response body contains following keys

    Examples: 
      | weather_state_name | weather_state_abbr | wind_direction_compass | min_temp | max_temp | air_pressure | humidity |

  @manual
  Scenario Outline: Verify location search based on longitude lattitude coordinates
    When I submit the GET request to call endpoint "/location/search/?lattlong=36.96,-122.02"
    Then I see that response status code is 200
    And I see that response body contains key "distance"
    And I see that response body contains following

    Examples: 
      | distance | title      | location_type | woeid   | lattlong              |
      |     1836 | Santa Cruz | City          | 2488853 | 36.974018,-122.030952 |

  @manual
  Scenario Outline: Verify status code is 404 for bad requests
    When I submit the GET request to call endpoint "/location/44018/"
    Then I see that response status code is 404
