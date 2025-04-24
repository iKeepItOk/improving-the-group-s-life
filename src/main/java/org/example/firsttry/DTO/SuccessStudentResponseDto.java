package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Дто об успешном выполнении дейстия со студентом (удаление, дабавление, обновление)")
public class SuccessStudentResponseDto {
    @Schema(description = "статус выполненого дейсвтия", example = "201")
    private int status;
    @Schema(description = "сообщение об выполненом действии", example = "Студент успешно добавлен в группу")
    private String message;
    @Schema(description = "фамилия студента", example = "Иванов")
    private String studentSurname;
}
