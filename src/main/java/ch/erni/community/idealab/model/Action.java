package ch.erni.community.idealab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author rap
 */
@Entity
public class Action {

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String icon;

}
