package com.example.project.samples;

import com.example.project.domain.Category;
import com.example.project.domain.Game;
import com.example.project.domain.GroupRoom;
import com.example.project.model.*;

import java.util.Collections;
import java.util.List;

import static com.example.project.chat.samples.ChatSample.getChatMock;
import static com.example.project.samples.UserMockSample.*;

public class GroupRoomSample {

    public static GroupRoom getGroupRoomMock(){
        return GroupRoom.builder()
                .id(1L)
                .city("Lublin")
                .maxUsers(5)
                .name("Group Mock")
                .open(true)
                .chat(getChatMock())
                .game(Game.builder().name("CSGO").build())
                .category(Category.builder().name("Match Making").build())
                .groupLeader(getCurrentUserMock())
                .joinCode("sersad23")
                .users(List.of(getUserMock(),getCurrentUserMock()))
                .inGameRolesActive(false)
                .build();
    }
    public static GroupRoom getGroupRoomGameRolesTrueMock(){
        return GroupRoom.builder()
                .id(1L)
                .city("Lublin")
                .maxUsers(5)
                .name("Group Mock")
                .open(true)
                .chat(getChatMock())
                .game(Game.builder().name("CSGO").build())
                .category(Category.builder().name("Match Making").build())
                .groupLeader(getCurrentUserMock())
                .joinCode("sersad23")
                .users(List.of(getUserMock(),getCurrentUserMock()))
                .inGameRolesActive(true)
                .build();
    }
    public static GroupRoom getCurrentUserGroupRoomMock(){
        return GroupRoom.builder()
                .id(2L)
                .city("Lublin")
                .maxUsers(5)
                .name("Group Mock")
                .open(true)
                .joinCode("sersad23")
                .users(List.of(getCurrentUserMock()))
                .inGameRolesActive(false)
                .build();
    }

    public static GroupRoomDTO getGroupRoomDTOMock() {
        return GroupRoomDTO.builder()
                .id(1L)
                .city("Lublin")
                .maxUsers(5)
                .name("Group Mock")
                .open(true)
                .joinCode("sersad23")
                .users(List.of(getUserDTOMock()))
                .inGameRolesActive(false)
                .build();
    }
    public static GroupRoomDTO getGroupRoomDTOGameRolesTrueMock() {
        return GroupRoomDTO.builder()
                .id(1L)
                .city("Lublin")
                .maxUsers(5)
                .name("Group Mock")
                .open(true)
                .joinCode("sersad23")
                .category(CategoryDTO.builder().basicMaxUsers(3).build())
                .users(List.of(getUserDTOMock()))
                .inGameRolesActive(true)
                .build();
    }

    public static GroupRoomUpdateDTO getGroupRoomUpdateDTOMock() {
        return GroupRoomUpdateDTO.builder()
                .maxUsers(5)
                .name("Group Mock")
                .description("HEH")
                .build();
    }


    public static UserGroupsListDTO getUserGroupListDTOMock() {
        return UserGroupsListDTO.builder()
                .groupRooms(Collections.singletonList(getGroupRoomDTOMock()))
                .id(1L)
                .build();
    }

    public static JoinCodeDTO getJoinCodeDTOMock() {
        return JoinCodeDTO.builder()
                .code("Heh")
                .build();
    }
}
