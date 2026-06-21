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
public class CandidateDto {
	
	@JsonView(Views.Public.class)
	private Long id;
	
	@JsonView(Views.Public.class)
	private String name;
	
	@JsonView(Views.Admin.class)
	private String email;
	
	@JsonView(Views.Public.class)
	private String partyName;
	
	@JsonView(Views.Admin.class)
	private Boolean rightToElect;


	private String password;

}
