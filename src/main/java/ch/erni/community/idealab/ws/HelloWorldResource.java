package ch.erni.community.idealab.ws;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author rap
 */
@Path(HelloWorldResource.PATH)
@Stateless
public class HelloWorldResource {

	public static final String PATH = "/helloworld";

	@GET
	public String message() {
		return "It works!";
	}

}
