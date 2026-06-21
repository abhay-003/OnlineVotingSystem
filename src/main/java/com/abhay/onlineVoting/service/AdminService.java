package com.abhay.onlineVoting.service;

import org.springframework.stereotype.Service;

import com.abhay.onlineVoting.dto.CandidateDto;
import com.abhay.onlineVoting.dto.VoterDto;
import com.abhay.onlineVoting.entity.Candidate;
import com.abhay.onlineVoting.entity.Voter;
import com.abhay.onlineVoting.repository.CandidateRepository;
import com.abhay.onlineVoting.repository.VoterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final CandidateRepository candidateRepo;
	private final VoterRepository voterRepo;
	
	
	public CandidateDto findCandidate(Long id) {
		Candidate candidate = candidateRepo.findById(id).orElseThrow();
		return mapToDto(candidate);
	}

	public CandidateDto findCandidateByEmail(String email) {
		Candidate candidate = candidateRepo.findByEmail(email).orElseThrow();
		return mapToDto(candidate);
	}
	
	public CandidateDto findCandidateByPartyName(String name) {
		Candidate candidate = candidateRepo.findByPartyName(name).orElseThrow();
		return mapToDto(candidate);
	}
	
	public CandidateDto updateCandidate(Long id, CandidateDto candidatedto) {
		Candidate candidate = candidateRepo.findById(id).orElseThrow();
		if(candidatedto.getName()!=null) {
			candidate.setName(candidatedto.getName());
		}
		if(candidatedto.getEmail()!=null) {
			candidate.setEmail(candidatedto.getEmail());
		}
		if(candidatedto.getPartyName()!=null) {
			candidate.setPartyName(candidatedto.getPartyName());
		}
		if(candidatedto.getRightToElect() != null) {
			candidate.setRightToElect(candidatedto.getRightToElect());
		}
		candidateRepo.save(candidate);

		return mapToDto(candidate);
	}
	
	public VoterDto updateVoter(Long id, VoterDto voterDto) {
		Voter voter = voterRepo.findById(id).orElseThrow();
		if(voterDto.getEmail()!=null) {
			voter.setEmail(voterDto.getEmail());
		}
		if(voterDto.getName()!=null) {
			voter.setName(voterDto.getName());
		}
		if(voterDto.getRightToVote()!=null) {
			voter.setRightToVote(voterDto.getRightToVote());
		}
		if(voterDto.getVoted()!=null) {
			voter.setVoted(voterDto.getVoted());
		}
		voterRepo.save(voter);
		return mapToDto(voter);
	}
	
	public VoterDto findVoterByEmail(String email) {
		Voter voter = voterRepo.findByEmail(email).orElseThrow();
		return mapToDto(voter);
	}

	public VoterDto findVoter(Long id) {
		Voter voter = voterRepo.findById(id).orElseThrow();
		return mapToDto(voter);
	}
	private CandidateDto mapToDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setId(candidate.getId());
		candidateDto.setEmail(candidate.getEmail());
		candidateDto.setName(candidate.getName());
		candidateDto.setPartyName(candidate.getPartyName());
		candidateDto.setRightToElect(candidate.isRightToElect());
		return candidateDto;
	}
	
	private VoterDto mapToDto(Voter voter) {
		VoterDto voterDto = new VoterDto();
		voterDto.setEmail(voter.getEmail());
		voterDto.setName(voter.getName());
		voterDto.setRightToVote(voter.getRightToVote());
		voterDto.setVoted(voter.getVoted());
		voterDto.setId(voter.getId());
		return voterDto;
	}

}
