package home.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {

	/**
	 * jsonObjectToList
	 * @param <T>
	 * @param o
	 * @return
	 */
	public static <T> List<T> jsonObjectToList(Object o) {
		JSONArray arr = new JSONArray();
		JSONObject _o = new JSONObject();
		if (o instanceof JSONArray == true) {
			List<T> list = (List<T>) ((JSONArray) o).toList();
			return list;
		}
		if (o instanceof JSONObject == true) {
			_o = (JSONObject) o;
		}
		for (String key : _o.keySet()) {
			arr.put(_o.get(key));
		}
		return (List<T>) arr.toList();
	}

	/**
	 * getString
	 * @param object
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getString(JSONObject object, String name, String defaultValue) {
		if (object == null || !object.has(name) || object.isNull(name)) {
			return defaultValue;
		}

		try {
			return object.getString(name);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * getString
	 * @param object
	 * @param name
	 * @return
	 */
	public static String getString(JSONObject object, String name) {
		return getString(object, name, null);
	}

	/**
	 * 
	 * @param object
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static Integer getInt(JSONObject object, String name, Integer defaultValue) {
		if (object == null || !object.has(name)) {
			return defaultValue;
		}

		try {
			return object.getInt(name);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * getLong
	 * @param object
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static Long getLong(JSONObject object, String name, Long defaultValue) {
		if (object == null || !object.has(name)) {
			return defaultValue;
		}

		try {
			return object.getLong(name);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * getLong
	 * @param object
	 * @param name
	 * @return
	 */
	public static Long getLong(JSONObject object, String name) {
		return getLong(object, name, null);
	}

	/**
	 * getInt
	 * @param object
	 * @param name
	 * @return
	 */
	public static Integer getInt(JSONObject object, String name) {
		return getInt(object, name, null);
	}

	/**
	 * getBoolean
	 * @param object
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBoolean(JSONObject object, String name, Boolean defaultValue) {
		if (object == null || !object.has(name)) {
			return defaultValue;
		}

		try {
			return object.getBoolean(name);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * getBoolean
	 * @param object
	 * @param name
	 * @return
	 */
	public static Boolean getBoolean(JSONObject object, String name) {
		return getBoolean(object, name, null);
	}

	/**
	 * getDouble
	 * @param object
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static Double getDouble(JSONObject object, String name, Double defaultValue) {
		if (object == null || !object.has(name)) {
			return defaultValue;
		}

		try {
			return object.getDouble(name);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * getDouble
	 * @param object
	 * @param name
	 * @return
	 */
	public static Double getDouble(JSONObject object, String name) {
		return getDouble(object, name, null);
	}
	
	/**
	 * getJSONObject
	 * @param object
	 * @param name
	 * @return
	 */
	public static JSONObject getJSONObject(JSONObject object, String name) {
		if (object == null || !object.has(name)) {
			return null;
		}

		try {
			return object.getJSONObject(name);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * getJSONArray
	 * @param object
	 * @param name
	 * @return
	 */
	public static JSONArray getJSONArray(JSONObject object, String name) {
		if (object == null || !object.has(name)) {
			return null;
		}

		try {
			return object.getJSONArray(name);
		} catch (Exception e) {
			return null;
		}
	}
}
