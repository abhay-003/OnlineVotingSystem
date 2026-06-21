package com.abhay.onlineVoting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "votings")
@Setter
@Getter
public class Voting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "voter_id", nullable = false, unique = true)
	private Voter voter;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;
	
		
}
