package util;

import java.lang.reflect.Field;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtil {
	static ReportLogger reportLogger = ReportLogger.getInstance();

	public static String readProperty(String key) {
		return System.getProperty(key);
	}

	public static String getValueFromClass(String className, String fieldName) {
		try {
			Class<?> c = Class.forName(className);
			Field field = c.getField(fieldName);
			field.setAccessible(true);
			return (String) field.get(c);
		} catch (Exception e) {
			return "";
		}
	}

	public static void processHeaders(RequestSpecification requestSpecification, String headersString) {
		String[] headers = headersString.split(",");
		for (String header : headers) {
			if (header.contains(":")) {
				String[] headerString = header.split(":");
				if (headerString.length > 1)
					requestSpecification.header(headerString[0].trim(), headerString[1].trim());
			}
		}
	}

	public static Integer verifyStatusCode(Response res, int statusCode) {
		try {
			reportLogger.log("response::::::" + res.asString());
			int resCode = res.getStatusCode();
			if (resCode == statusCode) {
				reportLogger.log(
						"Actual Status Code is '" + resCode + "' And Expected Status Code is '" + statusCode + "'");
				return null;
			} else {
				reportLogger.log(
						"Actual Status Code is '" + resCode + "' But Expected Status Code is '" + statusCode + "'");
				return resCode;
			}
		} catch (Exception e) {
			reportLogger.log(e.getMessage());
			return 0;
		}
	}
}
