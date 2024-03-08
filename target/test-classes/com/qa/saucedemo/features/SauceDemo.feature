@SauceDemoSanity
Feature: Lenskart feature

@test1 @smoke
Scenario: SauceDemo Login Feature
          Given user launch the saucedemo application[UI]
          When "standard_user" logged into the application with "secret_sauce"[UI]
          Then I verify page title should be "Swag Labs"[UI]
          
          


 
