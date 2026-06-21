package com.abhay.onlineVoting.dto;

//import com.abhay.onlineVoting.entity.Candidate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VotingResultDto {
	
//	private Long id;
//	private Candidate candidate;
	private Long candidateId;
	private String candidateName;
	private String partyName;
	private Integer voteCasted;

}
