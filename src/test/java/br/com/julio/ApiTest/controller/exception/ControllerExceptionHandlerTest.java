package br.com.julio.ApiTest.controller.exception;

import br.com.julio.ApiTest.service.exception.DataIntegrityViolationException;
import br.com.julio.ApiTest.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity(){
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(new ObjectNotFoundException("Object not found"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Object not found", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void dataIntegrityViolationException(){
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityViolationException(new DataIntegrityViolationException("Email already used")
                , new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals("Email already used", response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
    }


}