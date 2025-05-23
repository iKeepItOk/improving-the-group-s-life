package org.example.firsttry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.firsttry.DTO.AddGroupRequestDto;
import org.example.firsttry.DTO.DeleteGroupRequestDto;
import org.example.firsttry.DTO.GetAllGroupResponseDto;
import org.example.firsttry.DTO.GetGroupFromAllResponseDto;
import org.example.firsttry.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupController.class)
class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GroupService groupService;

    @Test
    void addGroupTest() throws Exception {
       AddGroupRequestDto request = new AddGroupRequestDto("IT-404");
       mockMvc.perform(post("/groups")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.status").value(201))
               .andExpect(jsonPath("$.message").value("Группа успешно добавлена"))
               .andExpect(jsonPath("$.groupNumber").value("IT-404"));
       verify(groupService, times(1)).addGroup(request);
    }

    @Test
    void deleteGroupTest() throws Exception {
        DeleteGroupRequestDto request = new DeleteGroupRequestDto("IT-404");
        mockMvc.perform(delete("/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Группа успешно удалена"))
                .andExpect(jsonPath("$.groupNumber").value("IT-404"));
        verify(groupService, times(1)).deleteGroup(request);

    }
    @Test
    void getAllGroupsTest() throws Exception {
        GetAllGroupResponseDto response = new GetAllGroupResponseDto();
        response.setGroups(List.of(
                new GetGroupFromAllResponseDto(1,"IT-404",10),
                new GetGroupFromAllResponseDto(2,"IT-505",15)
        ));
        when(groupService.getAllGroups()).thenReturn(response);
        mockMvc.perform(get("/groups/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groups.length()").value(2))
                .andExpect(jsonPath("$.groups[0].groupId").value(1))
                .andExpect(jsonPath("$.groups[0].number").value("IT-404"))
                .andExpect(jsonPath("$.groups[0].quantity").value(10))
                .andExpect(jsonPath("$.groups[1].groupId").value(2))
                .andExpect(jsonPath("$.groups[1].number").value("IT-505"))
                .andExpect(jsonPath("$.groups[1].quantity").value(15));
        verify(groupService, times(1)).getAllGroups();
    }

}
