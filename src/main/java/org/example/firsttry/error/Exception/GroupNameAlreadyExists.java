package org.example.firsttry.error.Exception;

public class GroupNameAlreadyExists extends RuntimeException {
    public GroupNameAlreadyExists(String groupNumber) {
        super("Группа с номером " + groupNumber + " уже существует");
    }
}
