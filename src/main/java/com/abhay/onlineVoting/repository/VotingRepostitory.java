package com.abhay.onlineVoting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.onlineVoting.entity.Candidate;
import com.abhay.onlineVoting.entity.Voter;
import com.abhay.onlineVoting.entity.Voting;

public interface VotingRepostitory extends JpaRepository<Voting, Long>{

	Optional<Voting> findByVoter(Voter voter);

    boolean existsByVoterId(Long voterId);

    List<Voting> findByCandidate(Candidate candidate);

    long countByCandidateId(Long candidateId);
}
