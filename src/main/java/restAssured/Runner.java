package restAssured;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;

public class Runner {
	public static String baseURL;
	public static String basePath;
	public static String authentication;
	public static String body = null;
	public static String expectedStatusCode;
	public static String expectedStatusLine;
	public static String expectedResponseBody;
	public static HashMap<String, String> pathParams;
	public static HashMap<String, String> queryParams = new HashMap<>();;
	static String seperateKey;
	static String seperateValue;
	static String[] path;
	static String[] pathPair;
	static String token;
	public static String authBody;
	public static HashMap<String, String> auth_payload = new HashMap<>();
	public static String auth_key1;
	public static String auth_key2;
	public static String auth_value1;
	public static String auth_value2;
	static String method;
	public static String authKey;

	public static void main(String[] args) throws ParseException {
		try {
			
			new Base();
		} catch (IOException | InterruptedException e) {
			System.out.println("Configuration/Test Data File Not Found");
			e.printStackTrace();
		}

		ExcelReader.getDataSet(0, ExcelReader.totalRows, 0, ExcelReader.totalColumns);

		for (int a = 0; a <= ExcelReader.dataset.size() - 1; a++) {
			for (int b = 2; b <= ExcelReader.coloumnDataSet.size() - 1; b++) {
				System.out.println("core classssss");
				String value = ExcelReader.dataset.get(a).get(ExcelReader.dataHeader.get(b));

				if (b == 2) {
					if (value.equalsIgnoreCase("NA")) {

						baseURL = null;
					} else {

						baseURL = value;
					}
				}
				if (b == 3) {
					switch (value) {
					case ("NA"):
						basePath = "";
					break;
					default:
						basePath = value;
					}
				}
				if (b == 4) {
					if (value.equalsIgnoreCase("NA")) {
						pathParams = null; 
					}

					else {
						path = value.split("\\|"); // key,value and key, vaue
						//path=value.split(Pattern.quote("|") );
						
						// pathParams = new HashMap<>();
						pathParams = new HashMap<>();
						for (int i = 0; i <= path.length - 1; i++) {
							pathPair = path[i].split("\\s*,\\s*");
							for (int j = 0; j <= pathPair.length - 1; j++) {

								if (j % 2 == 0) {

									seperateKey = pathPair[j];
								} else {
									//
									seperateValue = pathPair[j];
								}

							pathParams.put(seperateKey, seperateValue);
						}
					}

					}
				}
				if (b == 5) {
					if (value.equalsIgnoreCase("NA")) {
						queryParams = null;
					} else {
						path = value.split("\\|");
						queryParams = new HashMap<>();

						for (int i = 0; i <= path.length - 1; i++) {
							pathPair = path[i].split("\\s*,\\s*");
							for (int j = 0; j <= pathPair.length - 1; j++) {

								if (j % 2 == 0) {

									seperateKey = pathPair[j];
								} else {

									seperateValue = pathPair[j];
								}
							queryParams.put(seperateKey, seperateValue);
						}
						

					}
				}}
				if (b == 6) {
					if (value.equalsIgnoreCase("NA")) {
						token = null;

					} else {
						token = value;
					}
				}

				
				if (b == 7) {
					if ( value.equalsIgnoreCase("NA")
							) {
						authKey = null;
					} else {
						authKey = value;
					}

				}
				
				if (b == 8) {
					if (value.equalsIgnoreCase("NA")) {
						body = "";
					} else {
						body = value;
					}
				}
				if (b == 9) {
					if (value.equalsIgnoreCase("NA")) {
						expectedStatusCode = null;
					} else {
						expectedStatusCode = value;
					}
				}
				if (b == 10) {
					if (value.equalsIgnoreCase("NA")) {
						expectedStatusLine = null;
					} else {
						expectedStatusLine = value;
					}
				}
				if (b == 11) {
					if (value.equalsIgnoreCase("NA")) {

						expectedResponseBody = null;
					} else {
						expectedResponseBody = value;
					}
				}
				if (b == 12) {
					if (value.equalsIgnoreCase("NA")) {
						method = null;
					} else {
						method = value;
					}
				}
				
			}
			RestAssuredCore.initialize(baseURL, basePath, method, token);
		}
	}
}