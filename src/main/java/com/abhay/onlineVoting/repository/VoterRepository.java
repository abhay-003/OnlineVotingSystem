package com.abhay.onlineVoting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhay.onlineVoting.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, Long>{
	
	Optional<Voter> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Voter> findByVotedTrue();

    List<Voter> findByVotedFalse();

}
