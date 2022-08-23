package com.example.project.model;

import com.example.project.chat.model.ChatDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class GroupRoomDTO {
    private Long id;
    private String name;
    private String description;
    private int maxUsers;
    private boolean open;
    private List<UserDTO> users;
    private CategoryDTO category;
    private ChatDTO chatDTO;
    private UserDTO groupLeader;
    private String city;
    private GameDTO game;
    private String joinCode;
}
