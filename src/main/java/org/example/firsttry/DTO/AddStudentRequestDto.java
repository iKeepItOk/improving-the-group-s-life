package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для добавления студента в группу")
public class AddStudentRequestDto {
    @Schema(description = "фамилия студента", example = "Ivanov")
    @NotBlank(message = "Неккоректные данные в фамилии студента")
    private String surname;

    @Schema(description = "фамилия студента", example = "Иванов")
    @NotBlank(message = "Неккоректные данные в номере группы")
    @Pattern(
            regexp = "^[a-zA-Z]{2,}-\\d{3,}$",
            message = "Номер группы должен быть в формате 'it-404'"
    )
    private String numberGroup;

}
