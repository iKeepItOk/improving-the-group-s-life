package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentResponseDto {
    @Schema(description = "id студента", example = "1")
    private Integer id;
    @Schema(description = "Фамилия студента", example = "Иванов")
    private String surname;
}
