package amateur.shaobig.table_nuevo_complex.exception;

import amateur.shaobig.table_nuevo_complex.dto.exception.ExceptionDto;
import amateur.shaobig.table_nuevo_complex.exception.types.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionDto(e.getMessage(), LocalDateTime.now().toString()));
    }

}
