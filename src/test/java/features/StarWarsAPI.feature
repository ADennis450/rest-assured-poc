@run
Feature: StarWarsAPI

  Background: Use Star Wars API
    Given I am using the "Star Wars API"


  Scenario: Verify Yoda's Skin is Green
    When I send a GET request to the endpoint "api/people/?search=yoda"
    Then the response code is 200
    And the "results[0].skin_color" property on the response object is "green"

  Scenario: Verify Darth Vader Matches Character Schema
    When I send a GET request to the endpoint "api/people/?search=Darth Vader"
    Then the response matches the "Character" schema