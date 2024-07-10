package org.example.firsttry.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.firsttry.DTO.AddStudentRequestDto;
import org.example.firsttry.DTO.DeleteStudentRequestDto;
import org.example.firsttry.DTO.UpdateStudentRequestDto;
import org.example.firsttry.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "работа со студентами", description = "Позволяет добавлять,обновлять и удалять студентов из группы")
@RequestMapping(path = "/students")
public class StudentController {
    private final StudentService studentService;

    @Operation(summary = "создать нового студента в группе")
    @PostMapping
    public void addStudent(@RequestBody AddStudentRequestDto addStudentRequestDto) {
        log.info("Start method add with group: {} surname {}", addStudentRequestDto.getNumberGroup(), addStudentRequestDto.getSurname());
        studentService.addStudent(addStudentRequestDto);
    }
    @Operation(summary = "Удалить студента из группы")
    @DeleteMapping
    public void deleteStudent(@RequestBody DeleteStudentRequestDto deleteStudentRequestDto) {
        log.info("Start method delete with group {} student {}", deleteStudentRequestDto.getNumberGroup(), deleteStudentRequestDto.getSurname());
        studentService.deleteStudent(deleteStudentRequestDto);
    }
    @Operation(summary = "обновить данные студента")
    @PutMapping
    public void updateStudent(@RequestBody UpdateStudentRequestDto updateStudentRequestDto) {
        log.info("Start method update with group: {} surname {}", updateStudentRequestDto.getGroupNumber(), updateStudentRequestDto.getOldSurname());
        studentService.updateStudent(updateStudentRequestDto);
    }
}
