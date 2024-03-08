Feature: this feature test all the apis of restfulbooker

@Test-getToken
Scenario: Get Auth Token 
And I call "get token" api using file "AkashHybrid/src/test/resources/getAccessToken.json"
Then The response status should be 201