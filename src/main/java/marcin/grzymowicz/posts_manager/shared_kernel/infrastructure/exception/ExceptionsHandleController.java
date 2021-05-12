package marcin.grzymowicz.posts_manager.shared_kernel.infrastructure.exception;

import marcin.grzymowicz.posts_manager.shared_kernel.domain.custom_exception.EntityNotFoundException;
import marcin.grzymowicz.posts_manager.shared_kernel.domain.custom_exception.ResourceNotFoundException;
import org.junit.platform.commons.util.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionsHandleController {

    @ResponseBody
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(),
                                      ExceptionUtils.readStackTrace(ex)));
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleInternalException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), ex.getMessage(),
                                      ExceptionUtils.readStackTrace(ex)));
    }

    @ResponseBody
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<Object> noHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(),
                                      ExceptionUtils.readStackTrace(ex)));
    }

    @ResponseBody
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(new ErrorMessage(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(),
                                      ExceptionUtils.readStackTrace(ex)));
    }
}
