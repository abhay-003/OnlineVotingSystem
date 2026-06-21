package com.abhay.onlineVoting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.onlineVoting.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{
	Optional<Candidate> findByEmail(String email);

	Optional<Candidate> findByPartyName(String name);
	
    boolean existsByEmail(String email);
    List<Candidate> findAllByRightToElectTrue();


}