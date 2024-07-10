package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для обновления данных студента")
public class UpdateStudentRequestDto {
    @Schema(description = "фамилия студента старая", example = "Иванов")
    private String oldSurname;
    @Schema(description = "фамилия студента новая", example = "Иванов")
    private String newSurname;
    @Schema(description = "номер группы", example = "IT-404")
    private String groupNumber;
}
