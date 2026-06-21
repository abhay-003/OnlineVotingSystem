package com.abhay.onlineVoting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.abhay.onlineVoting.dto.CandidateDto;
import com.abhay.onlineVoting.dto.Views;
import com.abhay.onlineVoting.dto.VoterDto;
import com.abhay.onlineVoting.service.RegisterationService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
	
	
	private final RegisterationService register;
	
	public RegisterController(RegisterationService register) {
		// TODO Auto-generated constructor stub
		this.register=register;
	}
	
	@PostMapping("/voter")
	@JsonView(Views.Public.class)
	public ResponseEntity<VoterDto> registerVoter(@Valid @RequestBody VoterDto voterdto) {
		return new ResponseEntity<>(register.registerVoter(voterdto), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/candidate")
	@JsonView(Views.Public.class)
	public ResponseEntity<CandidateDto> registerCandidate(@Valid @RequestBody CandidateDto candidateDto) {
		return new ResponseEntity<>(register.registerCandidate(candidateDto), HttpStatus.CREATED);
		
	}

}
