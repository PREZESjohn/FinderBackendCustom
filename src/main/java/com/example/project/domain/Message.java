package com.example.project.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String text;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="chat_id")
    private Chat chat;

}
