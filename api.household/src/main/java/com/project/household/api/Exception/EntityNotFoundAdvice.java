package com.project.household.api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.household.api.Exception.NotFound.AppointmentNotFoundException;
import com.project.household.api.Exception.NotFound.BillNotFoundException;
import com.project.household.api.Exception.NotFound.HouseNotFoundException;
import com.project.household.api.Exception.NotFound.RequestNotFoundException;
import com.project.household.api.Exception.NotFound.RoomNotFoundException;
import com.project.household.api.Exception.NotFound.UserNotFoundException;

//Use to send error in the response body
@ControllerAdvice
public class EntityNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(RequestNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String requestNotFoundHandler(RequestNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(AppointmentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String appointmentNotFoundHandler(AppointmentNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(BillNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String billNotFoundHandler(BillNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(HouseNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String houseNotFoundHandler(HouseNotFoundException ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(RoomNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String roomNotFoundHandler(RoomNotFoundException ex) {
		return ex.getMessage();
	}
}
