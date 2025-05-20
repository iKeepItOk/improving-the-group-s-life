package org.example.firsttry.mapper;

import org.example.firsttry.DTO.AddGroupRequestDto;
import org.example.firsttry.DTO.GetGroupFromAllResponseDto;
import org.example.firsttry.entity.UniversityGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface GroupMapper {

    UniversityGroup toEntity(AddGroupRequestDto addGroupRequestDto);

    @Mapping(target = "groupId", source = "id")
    @Mapping(target = "quantity",
            expression = "java(universityGroup.getStudents() != null" +
                    " ? universityGroup.getStudents().size() : 0)")
    GetGroupFromAllResponseDto toDto(UniversityGroup universityGroup);

    List<GetGroupFromAllResponseDto> toDtoList(List<UniversityGroup> universityGroups);


}
