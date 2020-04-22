Feature: Validation place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
 Given Add place Payload with "<name>" "<language>" "<address>"
 When user calls "AddPlaceAPI" with "POST" http request
 Then the API call is successfull with status code as 200
 And "status" is response bodfy is "OK"
 Then verify place_id created maps to "<name>" using "GetPlaceAPI"
 

 Examples:
 | name          | language | address                 |
 |Frontline house|English   |29, side layout, cohen 09|
# |wadhwa medpws  |Spanish   |Spain                   |




@DeletePlace
Scenario: Verify if delete functionality is working fine

Given DeletePlace payload
 When user calls "DeletePlaceAPI" with "POST" http request
 Then the API call is successfull with status code as 200
 And "status" is response bodfy is "OK"