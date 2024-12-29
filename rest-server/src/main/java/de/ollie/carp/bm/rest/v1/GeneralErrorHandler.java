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

	static final String DEFAULT_MESSAGE_NO_SUCH_RECORD_FOUND = "No such record found!";
	static final String DEFAULT_MESSAGE_ID_NO_SUCH_RECORD_FOUND = "exceptions.no-such-record.text";
	static final String DEFAULT_MESSAGE_UNIQUE_CONSTRAINT_VIOLATION = "Unique constraint violation!";
	static final String DEFAULT_MESSAGE_ID_UNIQUE_CONSTRAINT_VIOLATION = "exceptions.unique-constraint-violation.text";

	@ExceptionHandler({ NoSuchRecordException.class })
	public ResponseEntity<Object> handleNoSuchRecordException(NoSuchRecordException ex, WebRequest request) {
		System.out.println("ERROR: " + ex.getMessage());
		return handleExceptionInternal(
			ex,
			new ErrorMessageDTO()
				.setMessage(DEFAULT_MESSAGE_NO_SUCH_RECORD_FOUND)
				.setMessageId(DEFAULT_MESSAGE_ID_NO_SUCH_RECORD_FOUND),
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
			new ErrorMessageDTO()
				.setMessage(DEFAULT_MESSAGE_UNIQUE_CONSTRAINT_VIOLATION)
				.setMessageId(DEFAULT_MESSAGE_ID_UNIQUE_CONSTRAINT_VIOLATION),
			null,
			HttpStatus.BAD_REQUEST,
			request
		);
	}
}
