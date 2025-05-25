package org.example.firsttry.error;

import lombok.extern.slf4j.Slf4j;
import org.example.firsttry.error.Exception.GroupNameAlreadyExists;
import org.example.firsttry.error.Exception.NotFoundGroupException;
import org.example.firsttry.error.Exception.NotFoundStudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Comparator;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

@ExceptionHandler(value
        = {NotFoundGroupException.class})
    protected ResponseEntity<ErrorDto> handleConflict() {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorDto(400, "Группа не найдена"));
    }
    @ExceptionHandler(value
            = {NotFoundStudentException.class})
    protected ResponseEntity<ErrorDto> handleConflict1() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(400, "Студент не найден"));
    }
    @ExceptionHandler(value
            = {GroupNameAlreadyExists.class})
    protected ResponseEntity<ErrorDto> handleConflict2() {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDto(409, "Группа с таким номером уже существует"));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorDto> handleConflict3(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .sorted(Comparator.comparing(FieldError::getDefaultMessage))
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.error(errorMessage);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(400, errorMessage));
    }
}
