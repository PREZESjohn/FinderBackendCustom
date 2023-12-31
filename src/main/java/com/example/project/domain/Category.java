package com.example.project.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private int basicMaxUsers;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="game_id")
    private Game game;

    @Builder.Default
    private boolean canAssignRoles = true;

}
