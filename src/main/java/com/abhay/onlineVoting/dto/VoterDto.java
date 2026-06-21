package com.abhay.onlineVoting.dto;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoterDto {
	@JsonView(Views.Public.class)
	private Long id;

	@JsonView(Views.Public.class)
    private String name;
   
	@JsonView(Views.Public.class)
	private String email;

	private String password;
	@JsonView(Views.Admin.class)
	private Boolean voted;
	@JsonView(Views.Admin.class)
	private Boolean rightToVote;

}
