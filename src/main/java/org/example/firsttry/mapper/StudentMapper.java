package org.example.firsttry.mapper;

import org.example.firsttry.DTO.AddStudentRequestDto;
import org.example.firsttry.DTO.GetStudentResponseDto;
import org.example.firsttry.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(AddStudentRequestDto addStudentRequestDto);

    GetStudentResponseDto toDto(Student student);

    List<GetStudentResponseDto> toDtoList(List<Student> students);

}
