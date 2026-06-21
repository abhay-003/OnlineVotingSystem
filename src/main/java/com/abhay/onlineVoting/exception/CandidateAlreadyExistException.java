package com.abhay.onlineVoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CandidateAlreadyExistException extends RuntimeException{
	public CandidateAlreadyExistException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
