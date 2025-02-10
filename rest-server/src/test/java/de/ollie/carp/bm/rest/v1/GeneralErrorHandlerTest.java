package de.ollie.carp.bm.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.ollie.carp.bm.client.v1.dto.ErrorMessageDTO;
import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.exception.UniqueConstraintViolationException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;

@ExtendWith(MockitoExtension.class)
class GeneralErrorHandlerTest {

	private static final String ENTITY_NAME = "entity-name";
	private static final String ID_ATTRIBUTE_NAME = "id-attribute-name";
	private static final String VALUE = "value";

	@Mock
	private ServletWebRequest webRequest;

	@InjectMocks
	private GeneralErrorHandler unitUnderTest;

	@Nested
	class handleNoSuchRecordException_NoSuchRecordException_WebRequest {

		@Test
		void returnsAHttpServletResponse_withExceptionInTheBody() {
			assertEquals(
				new ErrorMessageDTO()
					.setMessage(GeneralErrorHandler.DEFAULT_MESSAGE_NO_SUCH_RECORD_FOUND)
					.setMessageId(GeneralErrorHandler.DEFAULT_MESSAGE_ID_NO_SUCH_RECORD_FOUND),
				unitUnderTest
					.handleNoSuchRecordException(new NoSuchRecordException(VALUE, ENTITY_NAME, ID_ATTRIBUTE_NAME), webRequest)
					.getBody()
			);
		}

		@Test
		void returnsAHttpServletResponse_withHttpStatusNotFound() {
			assertEquals(
				HttpStatus.NOT_FOUND,
				unitUnderTest
					.handleNoSuchRecordException(new NoSuchRecordException(VALUE, ENTITY_NAME, ID_ATTRIBUTE_NAME), webRequest)
					.getStatusCode()
			);
		}
	}

	@Nested
	class handleNoSuchRecordException_UniqueConstraintViolationException_WebRequest {

		@Test
		void returnsAHttpServletResponse_withExceptionInTheBody() {
			assertEquals(
				new ErrorMessageDTO()
					.setMessage(GeneralErrorHandler.DEFAULT_MESSAGE_UNIQUE_CONSTRAINT_VIOLATION)
					.setMessageId(GeneralErrorHandler.DEFAULT_MESSAGE_ID_UNIQUE_CONSTRAINT_VIOLATION),
				unitUnderTest
					.handleUniqueConstraintViolationException(
						new UniqueConstraintViolationException(VALUE, ENTITY_NAME, ID_ATTRIBUTE_NAME),
						webRequest
					)
					.getBody()
			);
		}

		@Test
		void returnsAHttpServletResponse_withHttpStatusNotFound() {
			assertEquals(
				HttpStatus.BAD_REQUEST,
				unitUnderTest
					.handleUniqueConstraintViolationException(
						new UniqueConstraintViolationException(VALUE, ENTITY_NAME, ID_ATTRIBUTE_NAME),
						webRequest
					)
					.getStatusCode()
			);
		}
	}
}
