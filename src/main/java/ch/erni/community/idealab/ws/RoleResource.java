package ch.erni.community.idealab.ws;

import ch.erni.community.idealab.service.RoleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author rap
 */
@Path(RoleResource.PATH)
@Stateless
public class RoleResource {

	public static final String PATH = "/role";

	@Inject
	private RoleService roleService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listRoles() {
		return Response.ok(roleService.fetchAllRoles()).build();
	}

	@GET
	@Path("/id/{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRole(Integer id) {
		return Response.ok(roleService.fetchRole(id)).build();
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/add")
	public Response createRole(String roleName) {
		roleService.addNewRole(roleName);

		return Response.ok().build();
	}

}
