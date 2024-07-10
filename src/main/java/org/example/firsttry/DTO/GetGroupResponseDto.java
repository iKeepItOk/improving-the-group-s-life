package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выдает список студентов в группе")
public class GetGroupResponseDto {
    @Schema(description = "Список студентов в группе")
    private List<GetStudentResponseDto> students;
}
