package com.abhay.onlineVoting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VoterAlreadyExistException extends RuntimeException{
	public VoterAlreadyExistException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}

}
