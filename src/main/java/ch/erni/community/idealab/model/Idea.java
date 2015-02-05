package ch.erni.community.idealab.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author rap
 */
@Entity
public class Idea {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User owner;

	@OneToOne
	private State state;

	@OneToMany
	@JoinColumn(name = "IDEA_ID")
	private List<Rating> ratings;

	@OneToOne
	private Artifact artifact;

}
