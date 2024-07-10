package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetGroupFromAllResponseDto {
    @Schema(description = "id группы", example = "1")
    private Integer groupId;
    @Schema(description = "Номер группы", example = "IT-404")
    private String number;
    @Schema(description = "количество студентов в группе", example = "20")
    private Integer quantity;
}
