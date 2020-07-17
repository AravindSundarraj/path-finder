package com.test.coding.pathfinder;

import com.test.coding.pathfinder.service.PathFinderImpl;
import com.test.coding.pathfinder.service.PathTraversalImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class PathFinderApplicationTests {

	@Autowired
	PathFinderImpl pathFinderImpl;

	@Autowired
	PathTraversalImpl pathTraversalImpl;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void fileLoad() {
		Assert.assertFalse("File load failed", PathFinderImpl.pathFinderMap.isEmpty());
	}


	@Test
	public void testFileData() {

		String test = pathTraversalImpl.getRoute("Newark".toLowerCase() , "Wisconsin".toLowerCase());
		Assert.assertEquals("yes" , test);

	}

	@Test
	public void controllerTestYes() {

		Map<String, String> params = new HashMap<>();
		params.put("origin", "Boston");
		params.put("destination", "Florida");

		String body = restTemplate.getForObject("/v1/route?origin={origin}&destination={destination}", String.class, params);
		Assert.assertEquals("yes", body);
	}

	@Test
	public void controllerTestNo() {

		Map<String, String> params = new HashMap<>();
		params.put("origin", "Boston");
		params.put("destination", "Albany");

		String body = restTemplate.getForObject("/v1/route?origin={origin}&destination={destination}", String.class, params);
		Assert.assertEquals("no", body);
	}

	@Test
	public void controllerTestCase() {

		Map<String, String> params = new HashMap<>();
		params.put("origin", "Newark");
		params.put("destination", "Wisconsin");

		String body = restTemplate.getForObject("/v1/route?origin={origin}&destination={destination}", String.class, params);
		Assert.assertEquals("yes", body);
	}

	@Test
	public void controllerTestException() {
		Map<String, String> params = new HashMap<>();
		params.put("origin", "Boston");
		params.put("destination", "Texas");

		String body = restTemplate.getForObject("/v1/route?origin={origin}&destination={destination}", String.class, params);
		Assert.assertEquals("please enter valid Origin and Destination !!!", body);
	}


}
