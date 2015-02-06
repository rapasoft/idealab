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
import java.util.List;
import java.util.stream.Collectors;

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

	private Collection<? extends RoleDTO> fetchRoles() {
		WebClient role = createWebClient();
		return role
				.accept(MediaType.APPLICATION_JSON)
				.path("/role")
				.getCollection(RoleDTO.class);
	}

	private Response createRole(String name) {
		WebClient createRequest = createWebClient();
		return createRequest
				.type(MediaType.TEXT_PLAIN_TYPE)
				.path("/role/create")
				.post(name);
	}

	private Response readRole(Integer id) {
		WebClient deleteRequest = createWebClient();
		return deleteRequest
				.type(MediaType.TEXT_PLAIN_TYPE)
				.path("/role/read/" + id)
				.get();
	}

	private Response deleteRole(Integer id) {
		WebClient deleteRequest = createWebClient();
		return deleteRequest
				.type(MediaType.TEXT_PLAIN_TYPE)
				.path("/role/delete/" + id)
				.delete();
	}

	@Test
	public void testListRoles() {
		Collection roles = fetchRoles();

		Assert.assertTrue(roles != null);
	}

	@Test
	public void testAddRole() {
		Response role1 = createRole("Role1");
		Assert.assertEquals(Response.status(201).build().getStatus(), role1.getStatus());

		Response role2 = createRole("Role2");
		Assert.assertEquals(Response.status(201).build().getStatus(), role2.getStatus());

		Collection<? extends RoleDTO> roles = fetchRoles();
		Assert.assertTrue(roles != null);
		List<String> roleNames = roles.stream().map(role -> role.getName()).collect(Collectors.toList());
		Assert.assertTrue(roleNames.contains("Role1"));
		Assert.assertTrue(roleNames.contains("Role2"));
	}

	@Test
	public void testReadRole() {
		Response role1 = createRole("Read");
		Assert.assertEquals(Response.status(201).build().getStatus(), role1.getStatus());

		Integer roleId = role1.readEntity(Integer.TYPE);

		Response readRole = readRole(roleId);
		RoleDTO roleDTO = readRole.readEntity(RoleDTO.class);

		Assert.assertEquals("Read", roleDTO.getName());
	}

	@Test
	public void testDeleteRole() {
		Response role1 = createRole("Delete");
		Assert.assertEquals(Response.status(201).build().getStatus(), role1.getStatus());
		Integer roleId = role1.readEntity(Integer.TYPE);

		Response deleteRole = deleteRole(roleId);
		Assert.assertEquals(Response.accepted().build().getStatus(), deleteRole.getStatus());

		Collection<? extends RoleDTO> roles = fetchRoles();
		Assert.assertTrue(roles != null);
		List<String> roleNames = roles.stream().map(role -> role.getName()).collect(Collectors.toList());
		Assert.assertFalse(roleNames.contains("Delete"));
	}

}
