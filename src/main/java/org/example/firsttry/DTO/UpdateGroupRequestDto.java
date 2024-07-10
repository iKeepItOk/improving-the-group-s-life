package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO для обновления номера группы")
public class UpdateGroupRequestDto {
    @Schema(description = "Старый номер группы", example = "IT-404")
    private String oldNumber;
    @Schema(description = "новый номер группы", example = "IT-504")
    private String newNumber;
}
