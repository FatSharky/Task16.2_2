package by.training.task16.service.util;

/**
 * Util class for service
 * 
 * @author Vladislav
 *
 */
public class ServiceUtil {
	/**
	 * Waiting for some time
	 * 
	 * if necessary waiting time is not greater than zero quit from method else
	 * flow sleeps certain time
	 * 
	 * @param millis
	 */
	public static void waitMillis(long millis) {
		if (millis <= 0) {
			return;
		}
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {

			Thread.currentThread().interrupt();
		}
	}
}
