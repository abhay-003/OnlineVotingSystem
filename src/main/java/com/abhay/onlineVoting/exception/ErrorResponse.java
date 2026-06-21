package com.abhay.onlineVoting.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
	
	
	private Date timeStamp;
	private int status;
	private String error;
	private String message;
	private String path;

}
