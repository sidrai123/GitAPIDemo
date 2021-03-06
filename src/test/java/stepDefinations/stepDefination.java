package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class stepDefination extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;

	TestDataBuild data = new TestDataBuild();

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_Payload_with(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		// constructor will be called with value of resource
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
	}

	@Then("the API call is successfull with status code as {int}")
	public void the_API_call_is_successfull_with_status_code_as(Integer int1) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} is response bodfy is {string}")
	public void is_response_bodfy_is(String keyvalue, String ExpectedValue) {

		assertEquals(getJsonPath(response, keyvalue), ExpectedValue);

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {

		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualname = getJsonPath(response, "name");
		assertEquals(actualname, expectedname);

	}
	
	@Given("DeletePlace payload")
	public void deleteplace_payload() throws IOException {
		
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));

	
	}
	
	
	
	
	
	
	
	
	
	
	

}
