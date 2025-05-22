package org.example.firsttry.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.firsttry.DTO.*;
import org.example.firsttry.entity.Student;
import org.example.firsttry.entity.UniversityGroup;
import org.example.firsttry.error.Exception.GroupNameAlreadyExists;
import org.example.firsttry.error.Exception.NotFoundGroupException;
import org.example.firsttry.mapper.GroupMapper;
import org.example.firsttry.mapper.StudentMapper;
import org.example.firsttry.repository.GroupRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "test.mode", havingValue = "fouls")
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final StudentMapper studentMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void addGroup(AddGroupRequestDto addGroupRequestDto) {
        try {
            groupRepository.save(groupMapper.toEntity(addGroupRequestDto));
            log.debug("Group created with number: {}", addGroupRequestDto.getNumber());
        } catch (DataIntegrityViolationException e) {
            log.error("Group {} already exists", addGroupRequestDto.getNumber());
            throw new GroupNameAlreadyExists(addGroupRequestDto.getNumber());
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void deleteGroup(DeleteGroupRequestDto deleteGroupRequestDto) {
        groupRepository.deleteUniversityGroupByNumber(deleteGroupRequestDto.getNumber());
        log.info("Group deleted with number: {}", deleteGroupRequestDto.getNumber());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, readOnly = true)
    public GetAllGroupResponseDto getAllGroups() {
        GetAllGroupResponseDto getAllGroup = new GetAllGroupResponseDto();
        List<UniversityGroup> groups = groupRepository.findAll();
        log.debug("Number of groups: {}", groups.size());
        getAllGroup.setGroups(groupMapper.toDtoList(groups));
        return getAllGroup;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED, readOnly = true)
    public GetGroupResponseDto getGroup(String number) {
        GetGroupResponseDto getGroup = new GetGroupResponseDto();
        UniversityGroup group = groupRepository.findUniversityGroupByNumber(number);
        if (group == null) {
            log.error("Group {} not found", number);
            throw new NotFoundGroupException(number);
        }
        List<Student> students = group.getStudents();
        log.info("Number of students: {}", students.size());
        getGroup.setStudents(studentMapper.toDtoList(students));
        return getGroup;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = true)
    public void updateGroup(UpdateGroupRequestDto updateGroupRequestDto) {
        UniversityGroup group = groupRepository.findUniversityGroupByNumber(updateGroupRequestDto.getOldNumber());
        if (group == null) {
            log.error("Group {} not found", updateGroupRequestDto.getOldNumber());
            throw new NotFoundGroupException(updateGroupRequestDto.getOldNumber());
        }
        group.setNumber(updateGroupRequestDto.getNewNumber());
        groupRepository.save(group);
        log.info("Group updated with number: {}", group.getNumber());
    }
}
