package ch.erni.community.idealab.model;

import ch.erni.community.idealab.model.dto.RoleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rap
 */
@Entity
@Data
@NoArgsConstructor
public class Role {

	public static final String ID = "id";

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	@ManyToMany(mappedBy = "permittedRoles")
	private List<State> states;

	public RoleDTO toDTO() {
		return new RoleDTO(id, name, users.stream().map(User::toDTO).collect(Collectors.toList()), states.stream().map(State::toDTO).collect(Collectors.toList()));
	}
}
