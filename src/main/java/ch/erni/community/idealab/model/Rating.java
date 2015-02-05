package ch.erni.community.idealab.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author rap
 */
@Entity
public class Rating {

	@Id
	@GeneratedValue
	private Integer id;

	@Min(value = 1)
	@Max(value = 5)
	private Integer value;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

}
