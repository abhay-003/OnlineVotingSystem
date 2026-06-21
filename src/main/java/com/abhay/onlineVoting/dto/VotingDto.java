package com.abhay.onlineVoting.dto;

import com.abhay.onlineVoting.entity.Candidate;
import com.abhay.onlineVoting.entity.Voter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VotingDto {
	
	private Long id;
	private Voter voter;
	private Candidate candidate;
	private boolean voted;

}
