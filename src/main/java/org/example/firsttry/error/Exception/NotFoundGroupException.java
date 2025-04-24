package org.example.firsttry.error.Exception;

public class NotFoundGroupException extends RuntimeException {
    public NotFoundGroupException(String groupNumber) {
        super("Группа с номером " + groupNumber + " не найдена");
    }


}
