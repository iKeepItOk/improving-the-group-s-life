package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Дто об успешном выполнении дейстия c групой (удаление, дабавление, обновление)")
public class SuccessGroupResponseDto {
    @Schema(description = "статус выполненого дейсвтия", example = "201")
    private int status;
    @Schema(description = "сообщение об выполненом действии", example = "группа успешно добавлена")
    private String message;
    @Schema(description = "Номер группы", example = "IT-404")
    private String groupNumber;
}
