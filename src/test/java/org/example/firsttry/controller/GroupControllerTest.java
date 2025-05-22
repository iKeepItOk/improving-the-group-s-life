package org.example.firsttry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.firsttry.DTO.AddGroupRequestDto;
import org.example.firsttry.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
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
        doNothing().when(groupService).addGroup(any(AddGroupRequestDto.class));
       mockMvc.perform(post("/groups")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.status").value(201))
               .andExpect(jsonPath("$.message").value("Группа успешно добавлена"))
               .andExpect(jsonPath("$.groupNumber").value("IT-404"));
    }


}
