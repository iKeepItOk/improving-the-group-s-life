package org.example.firsttry.error.Exception;

public class NotFoundStudentException extends RuntimeException {
    public NotFoundStudentException(String studentSurname) {
        super("студент с фамилией " + studentSurname + " не найден");
    }
}
