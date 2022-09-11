package com.example.project.chat.model;

import com.example.project.model.UserMsgDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageLogsDTO {

    private Long id;
    private String text;
    private UserMsgDTO user;
    @JsonSerialize(as = LocalDateTime.class)
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime date;
    private String groupName;
}
