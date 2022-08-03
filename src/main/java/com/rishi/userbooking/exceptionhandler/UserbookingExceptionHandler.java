package com.rishi.userbooking.exceptionhandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rishi.userbooking.customexception.DuplicateEntryException;


@ControllerAdvice
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class UserbookingExceptionHandler {

	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<ResponseException> handleDuplicateEntry(DuplicateEntryException ex, HttpServletRequest httpRequest,HttpServletResponse httpResponse ) {
		ResponseException resExc = new ResponseException();
		resExc.setMsg(ex.getMessage());
		resExc.setCode(HttpStatus.BAD_REQUEST.toString());
		resExc.setStatus(HttpStatus.BAD_REQUEST.value());
		resExc.setTitle("Duplicate Entry");
		
		
		return new ResponseEntity<ResponseException>(resExc,HttpStatus.BAD_REQUEST);
	}
}
