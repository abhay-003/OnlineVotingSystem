package com.abhay.onlineVoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
public class VoterNotEligibleToVoteException extends RuntimeException{
	public VoterNotEligibleToVoteException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
