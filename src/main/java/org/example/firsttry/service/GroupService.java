package org.example.firsttry.service;

import org.example.firsttry.DTO.*;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

    void addGroup(AddGroupRequestDto addGroupRequestDto);

    void deleteGroup(DeleteGroupRequestDto deleteGroupRequestDto);

    GetAllGroupResponseDto getAllGroups();

    GetGroupResponseDto getGroup(String number);

    void updateGroup(UpdateGroupRequestDto updateGroupRequestDto);


}
