package org.example.firsttry.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.firsttry.DTO.*;
import org.example.firsttry.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Работа с группами", description = "Позволяет создавать, удалять, обновлять, и вызывать группы")
@RequestMapping(path = "/groups")
public class GroupController {
    private final GroupService groupService;

    @Operation(summary = "Добавить новую группу")
    @PostMapping
    public ResponseEntity<SuccessGroupResponseDto> addGroup(@RequestBody @Valid AddGroupRequestDto addGroupRequestDto) {
        log.debug("Start method add with number: {}", addGroupRequestDto.getNumber());
        groupService.addGroup(addGroupRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessGroupResponseDto(201,"Группа успешно добавлена",addGroupRequestDto.getNumber()));
    }
    @Operation(summary = "Удалить группу")
    @DeleteMapping
    public ResponseEntity<SuccessGroupResponseDto> deleteGroup(@RequestBody @Valid DeleteGroupRequestDto deleteGroupRequestDto) {
        log.debug("Start method delete with number: {}", deleteGroupRequestDto.getNumber());
        groupService.deleteGroup(deleteGroupRequestDto);
        return ResponseEntity.ok(new SuccessGroupResponseDto(200,"Группа успешно удалена",deleteGroupRequestDto.getNumber()));
    }
    @Operation(summary = "Показать список всех групп с количеством студентов")
    @GetMapping(path = "/all")
    public GetAllGroupResponseDto getAllGroups() {
        log.debug("Start method get all group");
        return groupService.getAllGroups();
    }
    @Operation(summary = "Показать список группы с информацией о студентах в этой группе")
    @GetMapping
    public GetGroupResponseDto getGroup(@RequestParam @Parameter(description = "Номер группы", example = "IT-404") String number) {
        log.debug("Start method get group with number: {}", number);
        return groupService.getGroup(number);
    }
    @Operation(summary = "Обновить данные о группе")
    @PutMapping
    public ResponseEntity<SuccessGroupResponseDto> updateGroup(@RequestBody @Valid UpdateGroupRequestDto updateGroupRequestDto) {
        log.debug("Start method update group with number: {}", updateGroupRequestDto.getOldNumber());
        groupService.updateGroup(updateGroupRequestDto);
        return ResponseEntity.ok(new SuccessGroupResponseDto(200,"Группа успешно обновлена",updateGroupRequestDto.getNewNumber()));
    }

}
