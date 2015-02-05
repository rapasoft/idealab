package ch.erni.community.idealab.service;

import ch.erni.community.idealab.model.Role;
import ch.erni.community.idealab.model.dto.RoleDTO;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rap
 */
@Stateless
public class RoleService extends CrudService<Role> {

	public void addNewRole(String roleName) {
		Role role = new Role();
		role.setName(roleName);

		entityManager.persist(role);
	}

	public List<RoleDTO> fetchAllRoles() {
		List<Role> roles = findAll();
		return roles.stream().map(Role::toDTO).collect(Collectors.toList());
	}

	public RoleDTO fetchRole(Integer id) {
		Role role = findOne(Role.ID, id);
		return role.toDTO();
	}

	@Override
	public Class<Role> entityClass() {
		return Role.class;
	}
}
