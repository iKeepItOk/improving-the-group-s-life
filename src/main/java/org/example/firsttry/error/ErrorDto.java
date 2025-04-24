package org.example.firsttry.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Дто об ошибке")
public class ErrorDto {
    @Schema(description = "код ошибки", example = "404")
    private int code;
    @Schema(description = "сообщение ошибки", example = "Группа не найдена")
    private String errorMessage;
}
