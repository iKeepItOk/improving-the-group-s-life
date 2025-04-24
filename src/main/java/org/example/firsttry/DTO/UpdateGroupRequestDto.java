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
@Schema(description = "DTO для обновления номера группы")
public class UpdateGroupRequestDto {

    @Schema(description = "Старый номер группы", example = "IT-404")
    @Pattern(
            regexp = "^[a-zA-Z]{2,}-\\d{3,}$",
            message = "Номер группы должен быть в формате 'it-404'"
    )
    @NotBlank(message = "Неккоректные данные в номере группы")
    private String oldNumber;

    @Schema(description = "новый номер группы", example = "IT-504")
    @Pattern(
            regexp = "^[a-zA-Z]{2,}-\\d{3,}$",
            message = "Номер группы должен быть в формате 'it-404'"
    )
    @NotBlank(message = "Неккоректные данные в номере группы")
    private String newNumber;
}
