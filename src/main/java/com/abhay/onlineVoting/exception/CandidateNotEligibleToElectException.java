package com.abhay.onlineVoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CandidateNotEligibleToElectException extends RuntimeException{
	public CandidateNotEligibleToElectException(String message) {
		super(message);
	}

}
