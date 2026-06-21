package com.abhay.onlineVoting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.onlineVoting.entity.Candidate;
import com.abhay.onlineVoting.entity.VotingResult;

public interface VotingResultRepository extends JpaRepository<VotingResult, Long>{
	Optional<VotingResult> findByCandidate(Candidate candidate);
	
	Optional<VotingResult> findByCandidateId(Long id);
	
	Optional<VotingResult> findFirstByOrderByVoteCastedDesc();
}
