package restAssured;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.config.ConnectionConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredCore {

	private static RequestSpecification request;
	private static Response response;
	private static String url;
	public static String httpMethod;
	private static String accessToken;
	static String actualStatuscode;
	static String actualStatusLine;
	static String actualResponseBody;

	public static void initialize(String baseURL, String baseurlPath, String method, String token)
			throws ParseException {

		// Formulate the API url
		ConnectionConfig.connectionConfig().closeIdleConnectionsAfterEachResponseAfter(950, TimeUnit.MILLISECONDS);

		request = RestAssured.given();
		url = baseURL + baseurlPath;
		RestAssuredCore.httpMethod = method;
		request.contentType(ContentType.JSON);
		request.baseUri(url);
		if (token != null) {

			
			Authenticate(Runner.body);

			request.headers("Authorization", "Bearer " + accessToken);

		}
		try {
			executeAPI();
		} catch (InterruptedException e) {
			System.out.println("Interuption Alert!!!");
			e.printStackTrace();
		}
	}

	private static void executeAPI() throws InterruptedException {

		
		if (httpMethod.equalsIgnoreCase("post")) {
			if (Runner.pathParams != null) {
				RestAssuredCore.ExecuteWithPathParams(Runner.pathParams);

			}
			if (Runner.queryParams != null) {
				RestAssuredCore.ExecuteWithQueryParams(Runner.queryParams);
			}
			if (Runner.body != null) {
				request.body(Runner.body);
			}
			response = request.post(url);
		}
		
		else if (httpMethod.equalsIgnoreCase("delete")) {
			if (Runner.pathParams != null) {
				RestAssuredCore.ExecuteWithPathParams(Runner.pathParams);

			}

			if (Runner.queryParams != null) {
				RestAssuredCore.ExecuteWithQueryParams(Runner.queryParams);
			}
			if (Runner.body != null) {
				request.body(Runner.body);
			}
			response = request.delete(url);
		}
		/////////////////////////////////////////////////////////
		else if (httpMethod.equalsIgnoreCase("get")) {
			if (Runner.pathParams != null) {
				RestAssuredCore.ExecuteWithPathParams(Runner.pathParams);
			}
			if (Runner.queryParams != null) {
				RestAssuredCore.ExecuteWithQueryParams(Runner.queryParams);
			}
			if (Runner.body != null) {
				request.body(Runner.body);
			}
			response = request.get(url);
		}
		/////////////////////////////////////////////////////////
		else if (httpMethod.equalsIgnoreCase("patch")) {
			if (Runner.pathParams != null) {
				RestAssuredCore.ExecuteWithPathParams(Runner.pathParams);
			}
			if (Runner.queryParams != null) {
				RestAssuredCore.ExecuteWithQueryParams(Runner.queryParams);
			}
			if (Runner.body != null) {
				request.body(Runner.body);
			}
			response = request.patch();
		}
		/////////////////////////////////////////////////////////
		else if (httpMethod.equalsIgnoreCase("put")) {
			if (Runner.pathParams != null) {
				RestAssuredCore.ExecuteWithPathParams(Runner.pathParams);
			}
			if (Runner.queryParams != null) {
				RestAssuredCore.ExecuteWithQueryParams(Runner.queryParams);
			}
			if (Runner.body != null) {
				request.body(Runner.body);
			}
			response = request.put();

		} else {
			System.out.println("Not Configured. Please check you test data!");
		}
		/////////////////////////////////////////////////////////

		actualResponseBody = response.getBody().asString();
		actualStatuscode = Integer.toString(response.getStatusCode());
		actualStatusLine = response.getStatusLine();
		if (Runner.expectedResponseBody != null) {
			Assert.assertEquals(Runner.expectedResponseBody, actualResponseBody);
		}
		if (Runner.expectedStatusCode != null) {
			Assert.assertEquals(Runner.expectedStatusCode, actualStatuscode);
		}
		if (Runner.expectedStatusLine != null) {
			Assert.assertEquals(Runner.expectedStatusLine, actualStatusLine);
		}
		

	}

	public static void Authenticate(String authbody) {
		request.body(authbody);

		System.out.println(request.post().getBody().prettyPrint());
		accessToken = request.post().getBody().jsonPath().getString(Runner.authKey);
		System.out.println(accessToken);
	}

	public static void ExecuteWithQueryParams(Map<String, String> queryParams) {

		request.queryParams(queryParams);
	}

	public static void ExecuteWithPathParams(Map<String, String> pathParams) {
		Set<String> paramKeySet = Runner.pathParams.keySet();
		Iterator<String> paramIterate = paramKeySet.iterator();
		while (paramIterate.hasNext()) {
			String path = paramIterate.next().toString();
			url = url + "{" + path + "}";

		}

		request.pathParams(pathParams);
	}

	

}
