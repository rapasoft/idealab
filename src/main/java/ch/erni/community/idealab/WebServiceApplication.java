package ch.erni.community.idealab;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author rap
 */
@ApplicationPath(WebServiceApplication.APPLICATION_PATH)
public class WebServiceApplication extends Application {
	public static final String APPLICATION_PATH = "/rest";

	public static final String TESTING_ENDPOINT_ADDRESS = "http://localhost:8080/idealab/" + APPLICATION_PATH;

}
