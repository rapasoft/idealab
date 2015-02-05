package ch.erni.community.idealab.model;

import ch.erni.community.idealab.model.dto.StateDTO;

import javax.persistence.*;
import java.util.List;

/**
 * @author rap
 */
@Entity
public class State {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	@ManyToMany
	@JoinTable(name = "STATE_ROLE")
	private List<Role> permittedRoles;

	@OneToMany
	@JoinColumn(name = "STATE_ID")
	private List<Action> permittedActions;

	public StateDTO toDTO() {
		return null;
	}

}
