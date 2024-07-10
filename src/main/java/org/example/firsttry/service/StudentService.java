package org.example.firsttry.service;

import org.example.firsttry.DTO.AddStudentRequestDto;
import org.example.firsttry.DTO.DeleteStudentRequestDto;
import org.example.firsttry.DTO.UpdateStudentRequestDto;
import org.example.firsttry.entity.Student;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    void addStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudent(DeleteStudentRequestDto deleteStudentRequestDto);

    void updateStudent(UpdateStudentRequestDto updateStudentRequestDto);
}
