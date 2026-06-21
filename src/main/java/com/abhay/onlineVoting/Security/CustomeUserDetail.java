package com.abhay.onlineVoting.Security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abhay.onlineVoting.entity.Candidate;
import com.abhay.onlineVoting.entity.Voter;
import com.abhay.onlineVoting.repository.CandidateRepository;
import com.abhay.onlineVoting.repository.VoterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomeUserDetail implements UserDetailsService{

	private final VoterRepository voterRepo;
	private final CandidateRepository candidateRepo;
	private final PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if("owner@voting.com".equalsIgnoreCase(username)) {
			return new User(
					"admin@voting.com", 
					encoder.encode("amcreater"),
					true, true, true, true, 
					List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
				);
		}
		
		if(voterRepo.findByEmail(username).isPresent()) {
			Voter voter = voterRepo.findByEmail(username).orElseThrow();
			return new User(voter.getEmail(),voter.getPassword(), true, true, true, true, List.of(new SimpleGrantedAuthority("ROLE_VOTER")));
		}
		
		if(candidateRepo.findByEmail(username).isPresent()) {
			Candidate candidate = candidateRepo.findByEmail(username).orElseThrow();
			return new User(candidate.getEmail(),candidate.getPassword(), true, true, true, true, List.of(new SimpleGrantedAuthority("ROLE_CANDIDATE")));

		}
		
		throw new UsernameNotFoundException("No voter or candidate found with email: " + username);	}

}
