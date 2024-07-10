package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для добавления студента в группу")
public class AddStudentRequestDto {
    @Schema(description = "номер группы", example = "IT-404")
    private String surname;
    @Schema(description = "фамилия студента", example = "Иванов")
    private String numberGroup;

}
