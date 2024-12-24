package de.ollie.carp.bm.rest.v1;

import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.exception.UniqueConstraintViolationException;
import de.ollie.carp.bm.rest.v1.dto.ErrorMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ NoSuchRecordException.class })
	public ResponseEntity<Object> handleNoSuchRecordException(NoSuchRecordException ex, WebRequest request) {
		System.out.println("ERROR: " + ex.getMessage());
		return handleExceptionInternal(
			ex,
			new ErrorMessageDTO().setMessage("message").setMessageId("message-id"),
			null,
			HttpStatus.NOT_FOUND,
			request
		);
	}

	@ExceptionHandler({ UniqueConstraintViolationException.class })
	public ResponseEntity<Object> handleUniqueConstraintViolationException(
		UniqueConstraintViolationException ex,
		WebRequest request
	) {
		System.out.println("ERROR: " + ex.getMessage());
		return handleExceptionInternal(
			ex,
			new ErrorMessageDTO().setMessage("message").setMessageId("message-id"),
			null,
			HttpStatus.BAD_REQUEST,
			request
		);
	}
}
