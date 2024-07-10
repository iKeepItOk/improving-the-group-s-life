package org.example.firsttry.error;

import org.example.firsttry.error.Exception.NotFoundGroupException;
import org.example.firsttry.error.Exception.NotFoundStudentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

@ExceptionHandler(value
        = {NotFoundGroupException.class})
    protected ResponseEntity<ErrorDto> handleConflict() {
    return ResponseEntity
            .status(400)
            .header("ero", "rr")
            .body(new ErrorDto(400, "Группа не найдена"));
    }
    @ExceptionHandler(value
            = {NotFoundStudentException.class})
    protected ResponseEntity<ErrorDto> handleConflict1() {
        return ResponseEntity
                .status(400)
                .header("ero", "rr")
                .body(new ErrorDto(400, "Студент не найден"));
    }
}
