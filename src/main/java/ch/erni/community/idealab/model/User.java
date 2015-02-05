package ch.erni.community.idealab.model;

import ch.erni.community.idealab.model.dto.UserDTO;

import javax.persistence.*;
import java.util.List;

/**
 * @author rap
 */
@Entity
public class User {

	@Id
	private String email;

	private String name;

	@ManyToMany
	@JoinTable(name = "USER_ROLE")
	private List<Role> roles;

	@OneToMany(mappedBy = "user")
	private List<Rating> ratings;

	@OneToMany(mappedBy = "owner")
	private List<Idea> ideas;

	public UserDTO toDTO() {
		return null;
	}
}
