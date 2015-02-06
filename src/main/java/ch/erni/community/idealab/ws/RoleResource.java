package ch.erni.community.idealab.ws;

import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.service.RoleService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

/**
 * @author rap
 */
@Path(RoleResource.PATH)
@Stateless
public class RoleResource {

	public static final String PATH = "/role";

	@Context
	UriInfo uriInfo;

	@Inject
	private RoleService roleService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listRoles() {
		return Response.ok(roleService.fetchAllRoles()).build();
	}

	@GET
	@Path("/read/{id: \\d+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRole(@PathParam("id") Integer id) {
		return Response.ok().entity(roleService.fetchRole(id)).build();
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createRole(String roleName) {
		Role role = roleService.addNewRole(roleName);
		URI uri = uriInfo.getAbsolutePathBuilder().path(role.getId().toString()).build();

		return Response.created(uri).entity(role.getId()).build();
	}

	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/update/name")
	public Response changeRoleName(Integer roleId, String name) {
		roleService.changeRoleName(roleId, name);

		return Response.accepted().entity("Role named change successfully to " + name).build();
	}

	@DELETE
	@Path("/delete/{id: \\d+}")
	public Response deleteRole(@PathParam("id") Integer id) {
		roleService.deleteRole(id);

		return Response.accepted().build();
	}

}
