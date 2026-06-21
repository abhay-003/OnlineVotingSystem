package com.abhay.onlineVoting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhay.onlineVoting.dto.VotingResultDto;
//import com.abhay.onlineVoting.repository.VotingResultRepository;
import com.abhay.onlineVoting.service.VotingResultService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
public class VotingResultController {
	
	private final VotingResultService votingResultService;
//	private final VotingResultRepository votingResultRepo;
	
	@GetMapping("/winner")
	public ResponseEntity<VotingResultDto> getWinner(){
		VotingResultDto winner = votingResultService.getWinner();
		return new ResponseEntity<VotingResultDto>(winner, HttpStatus.OK);
	}

//	public 
	
}
