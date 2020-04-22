package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before
	public void beforeScenario() throws IOException {
		// run this code when place id is null
		// write code which will give the place id

		stepDefination m = new stepDefination();

		if (stepDefination.place_id == null) {
			m.add_place_Payload_with("siddharth", "English", "India");
			m.user_calls_with_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("siddharth", "GetPlaceAPI");
		}

	}

}
