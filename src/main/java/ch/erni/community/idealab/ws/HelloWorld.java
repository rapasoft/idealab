package ch.erni.community.idealab.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author rap
 */
@Path("helloworld")
public class HelloWorld {

	@GET
	public String message() {
		return "It works!";
	}

}
