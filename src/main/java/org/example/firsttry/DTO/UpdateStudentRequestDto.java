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
@Schema(description = "DTO для обновления данных студента")
public class UpdateStudentRequestDto {

    @Schema(description = "фамилия студента старая", example = "Иванов")
    @NotBlank(message = "Неккоректные данные в  старой фамилии студента")
    private String oldSurname;

    @Schema(description = "фамилия студента новая", example = "Иванов")
    @NotBlank(message = "Неккоректные данные в новой фамилии студента")
    private String newSurname;

    @Schema(description = "номер группы", example = "IT-404")
    @Pattern(
            regexp = "^[a-zA-Z]{2,}-\\d{3,}$",
            message = "Номер группы должен быть в формате 'it-404'"
    )
    @NotBlank(message = "Неккоректные данные в номере группы")
    private String groupNumber;
}
