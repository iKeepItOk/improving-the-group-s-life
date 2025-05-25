package org.example.firsttry.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.firsttry.DTO.AddGroupRequestDto;
import org.example.firsttry.controller.GroupController;
import org.example.firsttry.service.GroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GroupController.class)
@Import(RestExceptionHandler.class)
public class RestExceptionHandlerTest {

    private final RestExceptionHandler exceptionHandler = new RestExceptionHandler();
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GroupService groupService;

    @Test
    void handleNotFoundGroupException() throws Exception {
        ResponseEntity<ErrorDto> response = exceptionHandler.handleConflict();
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getBody().getCode());
        assertEquals("Группа не найдена", response.getBody().getErrorMessage());
    }

    @Test
    void handleNotFoundStudentException() throws Exception {
        ResponseEntity<ErrorDto> response = exceptionHandler.handleConflict1();
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getBody().getCode());
        assertEquals("Студент не найден", response.getBody().getErrorMessage());
    }

    @Test
    void handleGroupNameAlreadyExists() throws Exception {
        ResponseEntity<ErrorDto> response = exceptionHandler.handleConflict2();
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals(409, response.getBody().getCode());
        assertEquals("Группа с таким номером уже существует", response.getBody().getErrorMessage());
    }
    @Test
    void MethodArgumentNotValidExceptionGroupTest1() throws Exception {
        AddGroupRequestDto request = new AddGroupRequestDto("IT404");
        mockMvc.perform(post("/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.errorMessage").value("number: Номер группы должен быть в формате 'it-404'"));
    }

    @Test
    void MethodArgumentNotValidExceptionGroupTest2() throws Exception {
        AddGroupRequestDto request = new AddGroupRequestDto("");
        mockMvc.perform(post("/groups")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.errorMessage").value("number: Неккоректные данные в номере группы, number: Номер группы должен быть в формате 'it-404'"));
    }
}