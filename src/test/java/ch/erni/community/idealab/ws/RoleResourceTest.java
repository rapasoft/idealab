package ch.erni.community.idealab.ws;

import ch.erni.community.idealab.WebServiceApplication;
import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.model.dto.RoleDTO;
import ch.erni.community.idealab.service.RoleService;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.jaxrs.client.WebClient;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author rap
 */
@RunWith(Arquillian.class)
public class RoleResourceTest {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "idealab.war")
				.addClass(RoleResource.class)
				.addPackage(RoleService.class.getPackage())
				.addPackages(true, Role.class.getPackage())
				.addClass(WebServiceApplication.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}

	public WebClient createWebClient() {
		return WebClient.create(WebServiceApplication.TESTING_ENDPOINT_ADDRESS, Arrays.asList(new JacksonJaxbJsonProvider()));
	}

	@Test
	public void testListRoles() {
		WebClient client = createWebClient();

		client
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.path("/role");

		Collection<? extends RoleDTO> roles = client.getCollection(RoleDTO.class);

		Assert.assertTrue(roles != null);
		Assert.assertTrue(roles.isEmpty());
	}

	@Test
	public void addRole() {
		WebClient owner = createWebClient();
		Response forOwner = owner
				.type(MediaType.TEXT_PLAIN_TYPE)
				.path("/role/add")
				.post("Owner");

		WebClient editor = createWebClient();
		Response forEditor = editor
				.type(MediaType.TEXT_PLAIN_TYPE)
				.path("/role/add")
				.post("Editor");

		Assert.assertEquals(Response.ok().build().getStatus(), forOwner.getStatus());
		Assert.assertEquals(Response.ok().build().getStatus(), forEditor.getStatus());

		WebClient role = createWebClient();
		role
				.accept(MediaType.APPLICATION_JSON)
				.path("/role");

		Collection<? extends RoleDTO> roles = role.getCollection(RoleDTO.class);

		Assert.assertTrue(roles != null);
		Assert.assertEquals(2, roles.size());
	}

}
