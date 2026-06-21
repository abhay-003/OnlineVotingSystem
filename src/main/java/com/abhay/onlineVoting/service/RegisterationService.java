package com.abhay.onlineVoting.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhay.onlineVoting.dto.CandidateDto;
import com.abhay.onlineVoting.dto.VoterDto;
import com.abhay.onlineVoting.entity.Candidate;
import com.abhay.onlineVoting.entity.Voter;
import com.abhay.onlineVoting.entity.VotingResult;
import com.abhay.onlineVoting.exception.CandidateAlreadyExistException;
import com.abhay.onlineVoting.exception.VoterAlreadyExistException;
import com.abhay.onlineVoting.repository.CandidateRepository;
import com.abhay.onlineVoting.repository.VoterRepository;
import com.abhay.onlineVoting.repository.VotingResultRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RegisterationService {
	
	private final CandidateRepository candidateRepo;
	private final VotingResultRepository votingResultRepo;
	private final VoterRepository voterRepo;
	private final PasswordEncoder encoder;
	

	@Transactional
	public CandidateDto registerCandidate(CandidateDto candidatedto) {
		
		if(candidateRepo.existsByEmail(candidatedto.getEmail())) {
			throw new CandidateAlreadyExistException("Candidate already exists with email id:"+candidatedto.getEmail());
		}
		
		Candidate candidate = mapToEntity(candidatedto);
		VotingResult result = new VotingResult();
		Candidate saveCandidate = candidateRepo.save(candidate);
		result.setCandidate(saveCandidate);
		votingResultRepo.save(result);
		return mapToDto(saveCandidate);
		
	}
	
	@Transactional
	public VoterDto registerVoter(VoterDto voterdto) {
		if(voterRepo.existsByEmail(voterdto.getEmail())) {
			throw new VoterAlreadyExistException("voter already existd with email id:"+voterdto.getEmail());
		}
		
		Voter voter = mapToEntity(voterdto);
		Voter saveVoter = voterRepo.save(voter);
		return mapToDto(saveVoter);
	}
	
	public Candidate mapToEntity(CandidateDto candidateDto) {
		Candidate candidate = new Candidate();
		candidate.setPartyName(candidateDto.getPartyName());
		candidate.setEmail(candidateDto.getEmail());
		candidate.setName(candidateDto.getName());
		candidate.setPassword(encoder.encode(candidateDto.getPassword()));
//		candidate.setPassword(candidateDto.getPassword());
		candidate.setRightToElect(true);
		return candidate;
	}

	public Voter mapToEntity(VoterDto voterDto) {
		Voter voter = new Voter();
		voter.setEmail(voterDto.getEmail());
		voter.setName(voterDto.getName());
		voter.setPassword(encoder.encode(voterDto.getPassword()));
//		voter.setPassword(voterDto.getPassword());
		voter.setVoted(false);
		voter.setRightToVote(true);
		return voter;
	}
	
	
	public CandidateDto mapToDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setId(candidate.getId());
		candidateDto.setName(candidate.getName());
		candidateDto.setEmail(candidate.getEmail());
		candidateDto.setPartyName(candidate.getPartyName());
		return candidateDto;
	}
	
	public VoterDto mapToDto(Voter voter) {
		VoterDto voterDto = new VoterDto();
		voterDto.setId(voter.getId());
		voterDto.setName(voter.getName());
		voterDto.setEmail(voter.getEmail());
		voterDto.setVoted(voter.getVoted());
		voterDto.setRightToVote(voter.getRightToVote());
		return voterDto;
	}


}
