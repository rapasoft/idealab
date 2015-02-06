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

	@Override
	public Class<Role> entityClass() {
		return Role.class;
	}

	public Role addNewRole(String roleName) {
		Role role = new Role();
		role.setName(roleName);

		entityManager.persist(role);

		return role;
	}

	public List<RoleDTO> fetchAllRoles() {
		List<Role> roles = findAll();
		return roles.stream().map(Role::toDTO).collect(Collectors.toList());
	}

	public RoleDTO fetchRole(Integer id) {
		Role role = findOne(Role.ID, id);
		return role.toDTO();
	}

	public void deleteRole(Integer id) {
		Role roleToDelete = entityManager.find(Role.class, id);

		entityManager.remove(roleToDelete);
	}

	public void changeRoleName(Integer id, String name) {
		Role roleToChange = entityManager.find(Role.class, id);

		roleToChange.setName(name);
	}
}
