package org.example.firsttry.service;

import lombok.extern.slf4j.Slf4j;
import org.example.firsttry.DTO.AddStudentRequestDto;
import org.example.firsttry.DTO.DeleteStudentRequestDto;
import org.example.firsttry.DTO.UpdateStudentRequestDto;
import org.example.firsttry.entity.Student;
import org.example.firsttry.entity.UniversityGroup;
import org.example.firsttry.error.Exception.NotFoundGroupException;
import org.example.firsttry.error.Exception.NotFoundStudentException;
import org.example.firsttry.mapper.StudentMapper;
import org.example.firsttry.repository.GroupRepository;
import org.example.firsttry.repository.StudentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@ConditionalOnProperty(value = "test.mode", havingValue = "fouls")
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, GroupRepository groupRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void addStudent(AddStudentRequestDto addStudentRequestDto) {
        Student student = studentMapper.toEntity(addStudentRequestDto);
        UniversityGroup group = groupRepository.findUniversityGroupByNumber(addStudentRequestDto.getNumberGroup());
        if (group == null) {
            throw new NotFoundGroupException();
        }
        student.setGroup(group);
        group.addStudentToUniversityGroup(student);
        studentRepository.save(student);
        log.info("the student was saved with a surname: {}", student.getSurname());
        groupRepository.save(group);
        log.info("the group was saved with a number: {}", group.getNumber());
    }
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void deleteStudent(DeleteStudentRequestDto deleteStudentRequestDto) {
        UniversityGroup group = groupRepository.findUniversityGroupByNumber(deleteStudentRequestDto.getNumberGroup());
        if (group == null) {
            throw new NotFoundGroupException();
        }
        Student student = studentRepository.findBySurname(deleteStudentRequestDto.getSurname());
        if (student == null) {
            throw new NotFoundStudentException();
        }
        group.deleteStudentFromUniversityGroup(student);
        studentRepository.delete(student);
        log.info("the student was deleted with a surname: {}", student.getSurname());
        groupRepository.save(group);
        log.info("the group was saved with  number: {}", group.getNumber());
    }
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED)
    public void updateStudent(UpdateStudentRequestDto updateStudentRequestDto) {
        UniversityGroup group = groupRepository.findUniversityGroupByNumber(updateStudentRequestDto.getGroupNumber());
        if (group == null) {
            throw new NotFoundGroupException();
        }
        Student student = studentRepository.findBySurname(updateStudentRequestDto.getOldSurname());
        if (student == null) {
            throw new NotFoundStudentException();
        }
        group.deleteStudentFromUniversityGroup(student);
        student.setSurname(updateStudentRequestDto.getNewSurname());
        group.addStudentToUniversityGroup(student);
        studentRepository.save(student);
        log.info("the student was updated with a surname: {}", student.getSurname());
        groupRepository.save(group);
        log.info("the group saved with number: {}", group.getNumber());
    }
}
