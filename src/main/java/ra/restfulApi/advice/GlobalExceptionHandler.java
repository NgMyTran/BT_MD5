package ra.restfulApi.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.restfulApi.exception.DupplicateException;
import ra.restfulApi.exception.NotFoundElementException;
import ra.restfulApi.model.dto.res.ResponseError;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundElementException.class)
    public ResponseEntity<ResponseError> handleNotFound(NotFoundElementException e) {
        return new ResponseEntity<>(new ResponseError(404, HttpStatus.NOT_FOUND, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DupplicateException.class)
    public ResponseEntity<ResponseError> handleDupplicateData(DupplicateException e) {
        return new ResponseEntity<>(new ResponseError(409, HttpStatus.CONFLICT, e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> handleNotValid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return new ResponseEntity<>(new ResponseError(400, HttpStatus.BAD_REQUEST, errors), HttpStatus.BAD_REQUEST);
    }
}