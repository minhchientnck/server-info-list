package home.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import home.constants.ConstantKeys;
import home.payload.ResponsePayload;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
		ResponsePayload response = new ResponsePayload()
				.setKeyValue(ConstantKeys.STATUS_CODE, HttpStatus.NOT_FOUND.value())
				.setKeyValue(ConstantKeys.STATUS_KEY, HttpStatus.NOT_FOUND)
				.setKeyValue(ConstantKeys.MESSAGE_KEY, e.getMessage()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.getResponse());
	}
	
}
