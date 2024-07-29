package util;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiWorkflow {
	static ReportLogger reportLogger = ReportLogger.getInstance();
	public static final String BASE_URL = ApiUtil.readProperty("SearchAPIBaseURL");
	static RequestSpecification requestSpecification;

	static {
		RestAssured.baseURI = BASE_URL;
		RestAssured.useRelaxedHTTPSValidation();
		requestSpecification = RestAssured.given().contentType(ContentType.JSON)
				.header("Content-Type", "application/json").header("Accept", "application/json");
	}

	public static Response callGetService(String endpoint, String headers, String token, String payload) {
		reportLogger.log("Endpoint URL: " + endpoint);
		reportLogger.log("--------------------------------------------------------------------------------------");
		reportLogger.log("Payload: " + payload);

		if (token != null && !token.isEmpty())
			requestSpecification.header("Authorization", "Bearer " + token);
		if (headers != null && !headers.isEmpty())
			ApiUtil.processHeaders(requestSpecification, headers);
		if (payload != null)
			requestSpecification.when().body(payload);
		Response response = requestSpecification.get(endpoint).thenReturn();
		reportLogger.info("----------------------------------Response--------------------------------------------");
		reportLogger.info("Response: " + response.getBody().asString());
		reportLogger.info("--------------------------------------------------------------------------------------");
		return response;
	}

	public static Response callPostService(String endpoint, String headers, String token, String payload) {
		reportLogger.info("Endpoint URL: " + endpoint);
		reportLogger.info("--------------------------------------------------------------------------------------");
		reportLogger.info("Payload: " + payload);

		if (token != null && !token.isEmpty())
			requestSpecification.header("Authorization", "Bearer " + token);
		if (headers != null && !headers.isEmpty())
			ApiUtil.processHeaders(requestSpecification, headers);
		Response response = requestSpecification.when().body(payload).post(endpoint).then().extract().response();
		reportLogger.info("----------------------------------Response--------------------------------------------");
		reportLogger.info("Response: " + response.getBody().asString());
		reportLogger.info("--------------------------------------------------------------------------------------");
		return response;
	}

	public static Response callPutService(String endpoint, String headers, String token, String payload) {
		reportLogger.info("Endpoint URL: " + endpoint);
		reportLogger.info("--------------------------------------------------------------------------------------");
		reportLogger.info("Payload: " + payload);

		if (token != null && !token.isEmpty())
			requestSpecification.header("Authorization", "Bearer " + token);
		if (headers != null && !headers.isEmpty())
			ApiUtil.processHeaders(requestSpecification, headers);
		Response response = requestSpecification.when().body(payload.toString()).request().patch(endpoint).then()
				.extract().response();
		reportLogger
				.info("-----------------------------------Response---------------------------------------------------");
		reportLogger.info("Response: " + response.getBody().asString());
		reportLogger
				.info("----------------------------------------------------------------------------------------------");

		return response;
	}

	public static Response callDeleteService(String endpoint, String headers, String token, String payload) {
		reportLogger.info("Endpoint URL: " + endpoint);
		reportLogger.info("--------------------------------------------------------------------------------------");
		reportLogger.info("Payload: " + payload);

		if (token != null && !token.isEmpty())
			requestSpecification.header("Authorization", "Bearer " + token);
		if (headers != null && !headers.isEmpty())
			ApiUtil.processHeaders(requestSpecification, headers);
		if (payload != null)
			requestSpecification.when().body(payload);
		Response response = requestSpecification.delete(endpoint).thenReturn();
		reportLogger
				.info("-----------------------------------Response---------------------------------------------------");
		reportLogger.info("Response: " + response.getBody().asString());
		reportLogger
				.info("----------------------------------------------------------------------------------------------");
		return response;
	}

	public static Response callGetServiceUsingQueryString(String endpoint, String headers, String token,
			Map<String, String> queryString) {
		reportLogger.log("Endpoint URL: " + endpoint);
		reportLogger.log("--------------------------------------------------------------------------------------");
		reportLogger.log("QueryString: " + queryString);

		if (token != null && !token.isEmpty())
			requestSpecification.header("Authorization", "Bearer " + token);
		if (headers != null && !headers.isEmpty())
			ApiUtil.processHeaders(requestSpecification, headers);
		if (queryString != null)
			requestSpecification.queryParams(queryString);
		Response response = requestSpecification.redirects().follow(false).get(endpoint).thenReturn();

		reportLogger.info("----------------------------------Response--------------------------------------------");
		reportLogger.info("Response: " + response.getBody().asString());
		reportLogger.info("--------------------------------------------------------------------------------------");
		return response;
	}

	public static Response callPostServiceUsingQueryString(String endpoint, String headers, String token,
			Map<String, String> queryString) {
		reportLogger.info("Endpoint URL: " + endpoint);
		reportLogger.info("--------------------------------------------------------------------------------------");
		reportLogger.info("QueryString: " + queryString);

		if (token != null && !token.isEmpty())
			requestSpecification.header("Authorization", "Bearer " + token);
		if (headers != null && !headers.isEmpty())
			ApiUtil.processHeaders(requestSpecification, headers);
		if (queryString != null)
			requestSpecification.queryParams(queryString);
		Response response = requestSpecification.when().post(endpoint).then().extract().response();
		reportLogger.info("----------------------------------Response--------------------------------------------");
		reportLogger.info("Response: " + response.getBody().asString());
		reportLogger.info("--------------------------------------------------------------------------------------");
		return response;
	}
}
