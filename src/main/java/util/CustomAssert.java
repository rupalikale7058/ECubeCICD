package util;

import org.testng.Assert;

public class CustomAssert {

	protected CustomAssert() {
	}

	static public void assertTrue(String message, boolean condition) {
		Assert.assertTrue(condition, message);
	}

	static public void assertTrue(boolean condition) {
		assertTrue(null, condition);
	}

	static public void assertFalse(String message, boolean condition) {
		assertTrue(message, !condition);
	}

	static public void assertFalse(boolean condition) {
		assertFalse(null, condition);
	}

	static public void fail(String message) {
		Assert.fail(message);
	}

	static public void assertEquals(String message, Object expected, Object actual) {
		if (equalsRegardingNull(expected, actual)) {
			return;
		} else if (expected instanceof String && actual instanceof String) {
			String cleanMessage = message == null ? "" : message;
			Assert.assertEquals((String) expected, (String) actual, cleanMessage);
		} else {
			failNotEquals(message, expected, actual);
		}
	}

	private static boolean equalsRegardingNull(Object expected, Object actual) {
		if (expected == null) {
			return actual == null;
		}

		return isEquals(expected, actual);
	}

	private static boolean isEquals(Object expected, Object actual) {
		return expected.equals(actual);
	}

	static private void failNotEquals(String message, Object expected, Object actual) {
		fail(format(message, expected, actual));
	}

	static String format(String message, Object expected, Object actual) {
		String formatted = "";
		if (message != null && !message.equals("")) {
			formatted = message + " ";
		}
		String expectedString = String.valueOf(expected);
		String actualString = String.valueOf(actual);
		if (expectedString.equals(actualString)) {
			return formatted + "\nEXPECTED: " + formatClassAndValue(expected, expectedString) + "\n  ACTUAL: "
					+ formatClassAndValue(actual, actualString);
		} else {
			return formatted + "\nEXPECTED:<" + expectedString + ">\n  ACTUAL:<" + actualString + ">";
		}
	}

	private static String formatClassAndValue(Object value, String valueString) {
		String className = value == null ? "null" : value.getClass().getName();
		return className + "<" + valueString + ">";
	}

	static public void assertEquals(Object expected, Object actual) {
		assertEquals(null, expected, actual);
	}

	static public void assertNotEquals(String message, Object unexpected, Object actual) {
		if (equalsRegardingNull(unexpected, actual)) {
			failEquals(message, actual);
		}
	}

	static public void assertNotEquals(Object unexpected, Object actual) {
		assertNotEquals(null, unexpected, actual);
	}

	private static void failEquals(String message, Object actual) {
		String formatted = "Values should be different. ";
		if (message != null) {
			formatted = message + ". ";
		}

		formatted += "Actual: " + actual;
		fail(formatted);
	}
}