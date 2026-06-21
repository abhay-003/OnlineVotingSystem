package com.abhay.onlineVoting.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhay.onlineVoting.dto.CandidateDto;
import com.abhay.onlineVoting.dto.Views;
import com.abhay.onlineVoting.service.VotingService;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/voting")
public class VotingController {
	private final VotingService votingservice;
	
	@GetMapping("/candidates")
	@JsonView(Views.Public.class)
	public ResponseEntity<List<CandidateDto>> geFullBallot(@RequestParam Long voterId){
		return new ResponseEntity<>(votingservice.getFullBallot(voterId), HttpStatus.OK);
	}
	
	@PostMapping("/cast")
	public ResponseEntity<String> castVote(@RequestParam Long voterId, @RequestParam Long candidateId){
		return new ResponseEntity<>(votingservice.castvote(voterId, candidateId), HttpStatus.CREATED);
	}

}
