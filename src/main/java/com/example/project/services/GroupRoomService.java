package com.example.project.services;

import com.example.project.domain.GroupRoom;
import com.example.project.model.GroupRoomUpdateDTO;
import com.example.project.model.JoinCodeDTO;
import com.example.project.chat.model.MessageDTO;
import com.example.project.model.GroupRoomDTO;

import java.util.List;

public interface GroupRoomService {

    List<GroupRoomDTO> getAllGroups();

    List<GroupRoomDTO> getGroupsByGame(String game);

    List<GroupRoomDTO> getDeletedGroups();

    List<GroupRoomDTO> getGroupsByGameCategory(Long gameId,Long categoryId);

    List<GroupRoomDTO> getGroupsByGameCategoryRole(Long gameId,Long categoryId,Long roleId);

    List<GroupRoomDTO> getGroupsByGameRole(Long gameId,Long roleId);

    List<GroupRoomDTO> getGroupsByGameCity(Long gameId, String city);

    List<GroupRoomDTO> getGroupsByGameCategoryCity(Long gameId,Long categoryId, String city);

    void updateVisibility(Long groupId, boolean result);

    GroupRoomDTO getGroupByName(String name);

    GroupRoomDTO save(GroupRoomDTO groupRoomDTO);

    GroupRoomDTO getGroupById(Long id);

    GroupRoomDTO saveAndReturnDTO(GroupRoom groupRoom);

    GroupRoomDTO updateGroupRoomByDTO(Long id, GroupRoomUpdateDTO groupRoomUpdateDTO);

    JoinCodeDTO generateJoinCode(Long groupId);

    GroupRoomDTO joinGroupByCode(String code);

    GroupRoomDTO makePartyLeader(Long groupId,Long userId);

    GroupRoomDTO removeUserFromGroup(Long groupId,Long userId);

    void deleteGroupRoomById(Long id);
}

