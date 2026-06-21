package com.abhay.onlineVoting.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhay.onlineVoting.dto.CandidateDto;
import com.abhay.onlineVoting.entity.Candidate;
import com.abhay.onlineVoting.entity.Voter;
import com.abhay.onlineVoting.entity.Voting;
import com.abhay.onlineVoting.entity.VotingResult;
//import com.abhay.onlineVoting.entity.VotingResult;
import com.abhay.onlineVoting.exception.CandidateNotEligibleToElectException;
import com.abhay.onlineVoting.exception.VoterNotEligibleToVoteException;
import com.abhay.onlineVoting.repository.CandidateRepository;
import com.abhay.onlineVoting.repository.VoterRepository;
import com.abhay.onlineVoting.repository.VotingRepostitory;
import com.abhay.onlineVoting.repository.VotingResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VotingService {

	private final VotingRepostitory votingRepo;
	private final VoterRepository voterRepo;
	private final CandidateRepository candidateRepo;
	private final VotingResultRepository votingresultRepo;
//	public VotingService(VotingRepostitory votingRepo) {
//		// TODO Auto-generated constructor stub\
//		this.votingRepo = votingRepo;
//	}
	
	public List<CandidateDto> getFullBallot(Long voterId){
		
		Voter voter = voterRepo.findById(voterId).orElseThrow();
		
		if(voter.getVoted() || !voter.getRightToVote()) {
			throw new VoterNotEligibleToVoteException("Voter already casted their vote or loses its rightToVote of id:"+voterId);
		}
		
		List<Candidate> candidate = candidateRepo.findAllByRightToElectTrue();
		
		return candidate.stream()
				.map(x-> this.mapToDto(x))
				.toList();
	}
	
	private CandidateDto mapToDto(Candidate candidate) {
		CandidateDto candidateDto = new CandidateDto();
		candidateDto.setName(candidate.getName());
		candidateDto.setPartyName(candidate.getPartyName());
		candidateDto.setId(candidate.getId());
		return candidateDto;
	}
	
	@Transactional
	public String castvote(Long voterid, Long candidateid) {
		Voter voter = voterRepo.findById(voterid).orElseThrow();
		
		Candidate candidate = candidateRepo.findById(candidateid).orElseThrow();
		
		if(voter.getVoted() || !voter.getRightToVote()) {
			throw new VoterNotEligibleToVoteException("Voter already casted their vote or loses its rightToVote of id:"+voterid);
		}
		
		if(!candidate.isRightToElect()) {
			throw new CandidateNotEligibleToElectException("Candidate loses its rightToElect of id:"+candidateid);
		}
		
//		votingresult.setCandidate(candidate);
//		votingresult.setVoteCasted(votingresult.getVoteCasted()+1);
		
		Voting voting = new Voting();
		voting.setCandidate(candidate);
		voting.setVoter(voter);
		votingRepo.save(voting);
//		candidate.setTotalVoteCount(candidate.getTotalVoteCount()+1);
		voter.setVoted(true);
		
		VotingResult votingResult = votingresultRepo.findByCandidateId(candidateid).orElseThrow(()-> new RuntimeException("Candidate is not present"));
//		votingResult.setCandidate(candidate);
		votingResult.setVoteCasted(votingResult.getVoteCasted()+1);
//		
//		candidateRepo.save(candidate);
		votingresultRepo.save(votingResult);
		voterRepo.save(voter);
		
		return "Vote has been successfully casted for candidate "+candidate.getPartyName()+" by voter "+voter.getName();
		
	}
	
}




















