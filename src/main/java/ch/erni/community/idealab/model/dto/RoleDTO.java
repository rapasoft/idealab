package ch.erni.community.idealab.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author rap
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {

	private Integer id;

	private String name;

	private List<UserDTO> users;

	private List<StateDTO> permittedStates;

}
