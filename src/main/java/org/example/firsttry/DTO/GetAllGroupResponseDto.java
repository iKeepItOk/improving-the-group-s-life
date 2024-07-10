package org.example.firsttry.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выдает DTO всех групп с количеством студентов в каждой")
public class GetAllGroupResponseDto {
    @Schema(description = "коллекция групп")
    private List<GetGroupFromAllResponseDto> groups;

}
