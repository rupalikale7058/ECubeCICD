package util;

import java.util.HashMap;
import java.util.Map;

import io.restassured.response.Response;

public class DataCache {
	public static ThreadLocal<Map<String, Response>> serviceResponseCache = new ThreadLocal<>();
	public static ThreadLocal<Map<String, Object>> variableCache = new ThreadLocal<Map<String, Object>>();

	public static void saveVariable(String key, Object value) {
		Map<String, Object> dataMap = variableCache.get();
		if (dataMap == null)
			dataMap = new HashMap<>();
		dataMap.put(key, value);
		variableCache.set(dataMap);
	}

	public static Object readVariable(String key) {
		Map<String, Object> dataMap = variableCache.get();
		if (dataMap == null)
			return key;
		return variableCache.get().get(key);
	}

	public static void saveResponse(String key, Response response) {
		Map<String, Response> data = serviceResponseCache.get();
		if (data == null)
			data = new HashMap<>();
		data.put(key, response);
		serviceResponseCache.set(data);
	}

	public static Response readResponse(String key) {
		return serviceResponseCache.get().get(key);
	}
}
