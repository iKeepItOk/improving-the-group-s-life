package org.example.firsttry.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.firsttry.DTO.AddStudentRequestDto;
import org.example.firsttry.DTO.DeleteStudentRequestDto;
import org.example.firsttry.DTO.SuccessStudentResponseDto;
import org.example.firsttry.DTO.UpdateStudentRequestDto;
import org.example.firsttry.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<SuccessStudentResponseDto> addStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto) {
        log.debug("Start method add with group: {} surname {}", addStudentRequestDto.getNumberGroup(), addStudentRequestDto.getSurname());
        studentService.addStudent(addStudentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessStudentResponseDto(201,"Студент успешно добавлен", addStudentRequestDto.getSurname()));
    }
    @Operation(summary = "Удалить студента из группы")
    @DeleteMapping
    public ResponseEntity<SuccessStudentResponseDto> deleteStudent(@RequestBody @Valid DeleteStudentRequestDto deleteStudentRequestDto) {
        log.debug("Start method delete with group {} student {}", deleteStudentRequestDto.getNumberGroup(), deleteStudentRequestDto.getSurname());
        studentService.deleteStudent(deleteStudentRequestDto);
        return ResponseEntity.ok(new SuccessStudentResponseDto(200,"Студент успешно удален", deleteStudentRequestDto.getSurname()));
    }
    @Operation(summary = "обновить данные студента")
    @PutMapping
    public ResponseEntity<SuccessStudentResponseDto> updateStudent(@RequestBody @Valid UpdateStudentRequestDto updateStudentRequestDto) {
        log.debug("Start method update with group: {} surname {}", updateStudentRequestDto.getGroupNumber(), updateStudentRequestDto.getOldSurname());
        studentService.updateStudent(updateStudentRequestDto);
        return ResponseEntity.ok(new SuccessStudentResponseDto(200,"Студент успешно обнавлен", updateStudentRequestDto.getNewSurname()));
    }
}
