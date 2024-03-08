@LenskartSanity
Feature: Lenskart feature

@test1 @smoke
Scenario: VFC_Access for user with Viewer role
          Given I launch the application url
          When I move to login page
          Then "user1" logged in to the application
          Then I go to EyeWear Section
          And I select rectangular frame


 
