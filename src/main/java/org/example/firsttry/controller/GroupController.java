package org.example.firsttry.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.firsttry.DTO.*;
import org.example.firsttry.service.GroupService;
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
    public void addGroup(@RequestBody AddGroupRequestDto addGroupRequestDto) {
        log.info("Start method add with number: {}", addGroupRequestDto.getNumber());
        groupService.addGroup(addGroupRequestDto);
    }
    @Operation(summary = "Удалить группу")
    @DeleteMapping
    public void deleteGroup(@RequestBody DeleteGroupRequestDto deleteGroupRequestDto) {
        log.info("Start method delete with number: {}", deleteGroupRequestDto.getNumber());
        groupService.deleteGroup(deleteGroupRequestDto);
    }
    @Operation(summary = "Показать список всех групп с количеством студентов")
    @GetMapping(path = "/all")
    public GetAllGroupResponseDto getAllGroups() {
        log.info("Start method get all group");
        return groupService.getAllGroups();
    }
    @Operation(summary = "Показать список группы с информацией о студентах в этой группе")
    @GetMapping
    public GetGroupResponseDto getGroup(@RequestParam @Parameter(description = "Номер группы", example = "IT-404") String number) {
        log.info("Start method get group with number: {}", number);
        return groupService.getGroup(number);
    }
    @Operation(summary = "Обновить данные о группе")
    @PutMapping
    public void updateGroup(@RequestBody UpdateGroupRequestDto updateGroupRequestDto) {
        log.info("Start method update group with number: {}", updateGroupRequestDto.getOldNumber());
        groupService.updateGroup(updateGroupRequestDto);
    }

}
