package com.abhay.onlineVoting.service;

import org.springframework.stereotype.Service;

//import com.abhay.onlineVoting.dto.CandidateDto;
import com.abhay.onlineVoting.dto.VotingResultDto;
import com.abhay.onlineVoting.entity.Candidate;
import com.abhay.onlineVoting.entity.VotingResult;
import com.abhay.onlineVoting.repository.VotingResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotingResultService {
	
	private final VotingResultRepository votingResultRepo;
	
//	private final 
	
	public VotingResultDto getWinner() {
		VotingResult result = votingResultRepo.findFirstByOrderByVoteCastedDesc().orElseThrow();
		return mapToDto(result);
	}
	
	private VotingResultDto mapToDto(VotingResult result) {
		Candidate candidate = result.getCandidate();
		VotingResultDto votingResultDto = new VotingResultDto();
		votingResultDto.setCandidateId(candidate.getId());
		votingResultDto.setCandidateName(candidate.getName());
		votingResultDto.setPartyName(candidate.getPartyName());
		votingResultDto.setVoteCasted(result.getVoteCasted());
		return votingResultDto;
	}

}
