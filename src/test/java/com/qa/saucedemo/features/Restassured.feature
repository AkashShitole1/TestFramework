Feature: this feature test all the apis of restfulbooker

@Test-getToken
Scenario: Get Auth Token 
And I call "get token" api using file "getAccessToken.json"
Then The response status should be 200
Then I store response fields value to below variables
|token|
|token|
Then I print the value of "${token}"

@Test-getCreatedBookings
Scenario: Get all bookings
When I call "get all bookings" api using file "GetBookingIds.json"
Then The response status should be 200

@Test-CreateBooking
Scenario: create new booking
When I call "create new booking" api using file "CreateBooking.json"
Then The response status should be 200
Then I store response fields value to below variables
|bookingid|
|bookingid|
Then I print the value of "${bookingid}"
 When I call "get created booking" api using file "GetBooking.json"
Then The response status should be 200