package org.example.firsttry.service;

import org.example.firsttry.DTO.*;
import org.example.firsttry.entity.Student;
import org.example.firsttry.entity.UniversityGroup;
import org.example.firsttry.mapper.GroupMapper;
import org.example.firsttry.mapper.StudentMapper;
import org.example.firsttry.repository.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = GroupServiceImpl.class)
public class GroupServiceImplTest {

    @Autowired
    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private GroupMapper groupMapper;

    @MockBean
    private StudentMapper studentMapper;

    @Test
    void addGroupTest() {
        AddGroupRequestDto addGroupRequestDto = new AddGroupRequestDto("IT-404");
        UniversityGroup universityGroup = new UniversityGroup(1,"IT-404",null);
        when(groupMapper.toEntity(addGroupRequestDto)).thenReturn(universityGroup);
        groupService.addGroup(addGroupRequestDto);
        verify(groupRepository, times(1)).save(universityGroup);
    }
    @Test
    void deleteGroupTest() {
        DeleteGroupRequestDto deleteGroupRequestDto = new DeleteGroupRequestDto("IT-404");
        groupService.deleteGroup(deleteGroupRequestDto);
        verify(groupRepository, times(1)).deleteUniversityGroupByNumber(deleteGroupRequestDto.getNumber());
    }
    @Test
    void getAllGroupsTest() {
        UniversityGroup universityGroup = new UniversityGroup(1,"IT-404",null);
        when(groupRepository.findAll()).thenReturn(List.of(universityGroup));
        when(groupMapper.toDtoList(List.of(universityGroup))).thenReturn(List.of(new GetGroupFromAllResponseDto(1,"IT-404",10)));
        var result = groupService.getAllGroups();
        assertThat(result.getGroups()).isNotNull().hasSize(1);
    }
    @Test
    void getGroupTest() {
        UniversityGroup universityGroup = new UniversityGroup(1,"IT-404",null);
        Student student = new Student(1,"Ivanov",universityGroup);
        universityGroup.addStudentToUniversityGroup(student);
        GetStudentResponseDto studentResponseDto = new GetStudentResponseDto();
        when(groupRepository.findUniversityGroupByNumber("IT-404")).thenReturn(universityGroup);
        when(studentMapper.toDtoList(List.of(student))).thenReturn(List.of(studentResponseDto));
        GetGroupResponseDto result = groupService.getGroup("IT-404");
        assertThat(result).isNotNull();
    }
    @Test
    void updateGroup_shouldUpdateGroup_whenExists() {
        UniversityGroup universityGroup = new UniversityGroup(1,"IT-404",null);
        UpdateGroupRequestDto updateGroupRequestDto = new UpdateGroupRequestDto("IT-404","IT-505");
        when(groupRepository.findUniversityGroupByNumber("IT-404")).thenReturn(universityGroup);
        groupService.updateGroup(updateGroupRequestDto);
        verify(groupRepository, times(1)).save(universityGroup);
        assertThat(universityGroup.getNumber()).isEqualTo("IT-505");
    }
}
