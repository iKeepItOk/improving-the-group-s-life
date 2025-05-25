package org.example.firsttry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.firsttry.DTO.AddStudentRequestDto;
import org.example.firsttry.DTO.DeleteStudentRequestDto;
import org.example.firsttry.DTO.UpdateStudentRequestDto;
import org.example.firsttry.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @Test
    void addStudentTest() throws Exception {
        AddStudentRequestDto request = new AddStudentRequestDto("Ivanov","IT-404");
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Студент успешно добавлен"))
                .andExpect(jsonPath("$.studentSurname").value("Ivanov"));
        verify(studentService, times(1)).addStudent(request);
    }

    @Test
    void deleteStudentTest() throws Exception {
        DeleteStudentRequestDto request = new DeleteStudentRequestDto("Ivanov","IT-404");
        mockMvc.perform(delete("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Студент успешно удален"))
                .andExpect(jsonPath("$.studentSurname").value("Ivanov"));
        verify(studentService, times(1)).deleteStudent(request);
    }
    @Test
    void updateStudentTest() throws Exception {
        UpdateStudentRequestDto request = new UpdateStudentRequestDto("Ivanov","Petrov","IT-404");
        mockMvc.perform(put("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Студент успешно обнавлен"))
                .andExpect(jsonPath("$.studentSurname").value("Petrov"));
        verify(studentService, times(1)).updateStudent(request);
    }

}
