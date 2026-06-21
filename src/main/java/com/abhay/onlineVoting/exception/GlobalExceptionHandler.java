package com.abhay.onlineVoting.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CandidateAlreadyExistException.class)
	public ResponseEntity<?> CandidateAlreadyException(CandidateAlreadyExistException ex, WebRequest req){
		ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.CONFLICT.value(), "Already exist", ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(VoterAlreadyExistException.class)
	public ResponseEntity<?> VoterAlreadyExistException(VoterAlreadyExistException ex, WebRequest req){
		ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.CONFLICT.value(), "Already Exist", ex.getMessage(),req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(VoterNotEligibleToVoteException.class)
	public ResponseEntity<?> VoterNotEligibleToVoteException(VoterNotEligibleToVoteException ex, WebRequest req){
		ErrorResponse errorDetails= new ErrorResponse(new Date(), 
				HttpStatus.UNPROCESSABLE_CONTENT.value(), 
				"Unprocessable_content", 
				ex.getMessage(),
				req.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_CONTENT);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> Exception(Exception ex, WebRequest req){
		ErrorResponse errorDetails= new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Already Exist", ex.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CandidateNotEligibleToElectException.class)
	public ResponseEntity<?> CandidateNotEligibleToElectException(CandidateNotEligibleToElectException ex, WebRequest req){
		ErrorResponse errorDetails = new ErrorResponse(new Date(), 
				HttpStatus.UNPROCESSABLE_CONTENT.value(),
				"UNnprocessable_content",
				ex.getMessage(),
				req.getDescription(false)
				);
		return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_CONTENT);
	}

}
