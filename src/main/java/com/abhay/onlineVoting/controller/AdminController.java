package com.abhay.onlineVoting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhay.onlineVoting.dto.CandidateDto;
import com.abhay.onlineVoting.dto.VoterDto;
import com.abhay.onlineVoting.service.AdminService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminservice;
	
	@GetMapping("/candidate/email")
	public ResponseEntity<CandidateDto> findCandidateByemail(@Valid @RequestParam String email){
		return new ResponseEntity<CandidateDto>(adminservice.findCandidateByEmail(email), HttpStatus.OK);
	}
	
	@GetMapping("/candidate/{id}")
	public ResponseEntity<CandidateDto> findCandidateById(@PathVariable Long id){
		return new ResponseEntity<>(adminservice.findCandidate(id), HttpStatus.OK);
	}

	@PatchMapping("/modifycandidate/{id}")
	public ResponseEntity<CandidateDto> modifyCandidate(@PathVariable Long id, @Valid @RequestBody CandidateDto candidateDto){
		return new ResponseEntity<CandidateDto>(adminservice.updateCandidate(id, candidateDto), HttpStatus.OK);
	}
	@GetMapping("/voter/email")
	public ResponseEntity<VoterDto> findVoterByemail(@Valid @RequestParam String email){
		return new ResponseEntity<>(adminservice.findVoterByEmail(email), HttpStatus.OK);
	}

	@GetMapping("/voter/{id}")
	public ResponseEntity<VoterDto> findVoterById(@PathVariable Long id){
		return new ResponseEntity<VoterDto>(adminservice.findVoter(id), HttpStatus.OK);
	}
	@PatchMapping("/modifyvoter/{id}")
	public ResponseEntity<VoterDto> modifyVoter(@PathVariable Long id, @Valid @RequestBody VoterDto voterDto){
		return new ResponseEntity<VoterDto>(adminservice.updateVoter(id, voterDto), HttpStatus.OK);
	}

}
