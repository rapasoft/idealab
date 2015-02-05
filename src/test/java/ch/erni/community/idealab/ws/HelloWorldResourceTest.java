package ch.erni.community.idealab.ws;

import ch.erni.community.idealab.WebServiceApplication;
import org.apache.cxf.jaxrs.client.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

/**
 * @author rap
 */
@RunWith(Arquillian.class)
public class HelloWorldResourceTest {

	private WebClient client;

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "idealab.war")
				.addClass(HelloWorldResource.class)
				.addClass(WebServiceApplication.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void init() {
		client = WebClient.create(WebServiceApplication.TESTING_ENDPOINT_ADDRESS);
	}

	@Test
	public void testHelloWorld() {
		client.path("/helloworld");

		Response response = client.get();

		Assert.assertEquals(Response.ok().build().getStatus(), response.getStatus());
	}

	@Test
	public void testAnythingElse() {
		client.path("/nonsense");

		Response response = client.get();

		Assert.assertEquals(Response.status(404).build().getStatus(), response.getStatus());
	}

}
