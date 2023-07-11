package com.williams.identityverification.util;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@Component
@PropertySource(value = "classpath:application.properties")
@Slf4j
public class API {
	@Autowired
	private RestTemplate template;

	HttpHeaders headers = null;
	ObjectMapper mapper = null;
	@Autowired
	private Environment env;

	@PostConstruct
	public void init() {
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth("BQA8lL5xBM6jvs3g4SQ7G-JxlV-0uprBQHhza7Isd4qPjuGN05mujYdgvndcCinhUAZVtPRFdGf_nT_UeTHyA_UVOVa109tXaRQ_f87VE7jGwQcLRzY");
		mapper = new ObjectMapper();
	}

	public Object processPayment(Object payment) {
		Object response = null;
		String jsonRequest = null;
		String result = "";
		String url = env.getProperty("payment.url");
		try {
//			HttpHeaders headers = new HttpHeaders();
			// Set any required headers for the request
			// headers.set("HeaderName", "HeaderValue");

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					url,
					HttpMethod.POST,
					new HttpEntity<>(payment, headers),
					String.class
			);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				result = responseEntity.getBody();
				// Perform any necessary processing on the result
				// response = mapper.readValue(result, Object.class);
			} else {
				System.out.println("Request failed with status code: " + responseEntity.getStatusCodeValue());
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		return response;
	}

	public Object getTransaction(String vendor) {
		Object response = null;
		String result = "";
		String url = env.getProperty("transaction.url") + vendor;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.exchange(
					url,
					HttpMethod.GET,
					new HttpEntity<>(headers),
					String.class
			);

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				result = responseEntity.getBody();
				// Perform any necessary processing on the result
				 response = mapper.readValue(result, Object.class);
				 log.info("Response body from client {}",response);
			} else {
				System.out.println("Request failed with status code: " + responseEntity.getStatusCodeValue());
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return response;
	}

	public Object getObject2(Object requestObject) throws IOException {
		Object response = null;
		String result = "";
		HttpHeaders headers1 = new HttpHeaders();
		headers1.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		String url = env.getProperty("payment.url");
		HttpEntity<String> entity = new HttpEntity<>("parameter",headers1);
		ResponseEntity<String> responseEntity = template.exchange(url,HttpMethod.GET,entity,String.class);
//		ResponseEntity<String> responseEntity = template.exchange(
//				url,
//				HttpMethod.GET,
//				new HttpEntity<>(payment, headers),
//				String.class
//		);
		result = responseEntity.getBody();
		response = mapper.readValue(result, Object.class);
		return response;
	}
}
