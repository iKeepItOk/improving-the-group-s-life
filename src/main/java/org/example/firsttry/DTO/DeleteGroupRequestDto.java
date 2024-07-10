package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "группа DTO для удаления группы")
public class DeleteGroupRequestDto {
    @Schema(description = "Номер группы", example = "IT-404")
    private String number;
}
