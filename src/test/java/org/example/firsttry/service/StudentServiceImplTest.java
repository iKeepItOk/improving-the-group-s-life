package org.example.firsttry.service;

import org.example.firsttry.DTO.AddStudentRequestDto;
import org.example.firsttry.DTO.DeleteStudentRequestDto;
import org.example.firsttry.DTO.UpdateStudentRequestDto;
import org.example.firsttry.entity.Student;
import org.example.firsttry.entity.UniversityGroup;
import org.example.firsttry.mapper.StudentMapper;
import org.example.firsttry.repository.GroupRepository;
import org.example.firsttry.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StudentServiceImpl.class)
public class StudentServiceImplTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private StudentMapper studentMapper;

    private AddStudentRequestDto addDto;
    private DeleteStudentRequestDto deleteDto;
    private UpdateStudentRequestDto updateDto;
    private UniversityGroup universityGroup;
    private Student student;

    @BeforeEach
    void setUp() {
        addDto = new AddStudentRequestDto("Иванов", "IT-404");
        deleteDto = new DeleteStudentRequestDto("Иванов", "IT-404");
        updateDto = new UpdateStudentRequestDto("Иванов", "Петров", "IT-404");

        universityGroup = new UniversityGroup(1,"IT-404",null);
        student = new Student(1,"Иванов",universityGroup);
        universityGroup.addStudentToUniversityGroup(student);
    }

    @Test
    void addStudentTest() {
        when(studentMapper.toEntity(addDto)).thenReturn(student);
        when(groupRepository.findUniversityGroupByNumber("IT-404")).thenReturn(universityGroup);
        studentService.addStudent(addDto);
        verify(groupRepository, times(1)).findUniversityGroupByNumber("IT-404");
        verify(studentRepository, times(1)).save(student);
        verify(groupRepository, times(1)).save(universityGroup);
    }

    @Test
    void deleteStudentTest() {
        when(groupRepository.findUniversityGroupByNumber("IT-404")).thenReturn(universityGroup);
        when(studentRepository.findBySurname("Иванов")).thenReturn(student);
        studentService.deleteStudent(deleteDto);
        verify(groupRepository, times(1)).findUniversityGroupByNumber("IT-404");
        verify(studentRepository, times(1)).findBySurname("Иванов");
        verify(studentRepository, times(1)).delete(student);
        verify(groupRepository, times(1)).save(universityGroup);
    }

    @Test
    void updateStudentTest() {
        when(groupRepository.findUniversityGroupByNumber("IT-404")).thenReturn(universityGroup);
        when(studentRepository.findBySurname("Иванов")).thenReturn(student);

        studentService.updateStudent(updateDto);

        verify(groupRepository, times(1)).findUniversityGroupByNumber("IT-404");
        verify(studentRepository, times(1)).findBySurname("Иванов");
        verify(studentRepository, times(1)).save(student);
        verify(groupRepository, times(1)).save(universityGroup);

        assertThat(student.getSurname()).isEqualTo("Петров");
    }
}