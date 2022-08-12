package com.example.project.handlers;

import com.example.project.exceptions.*;
import com.example.project.model.ErrorCodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorCodeMsg> userNotFound(UserNotFoundException e){
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<ErrorCodeMsg> groupNotFound(GroupNotFoundException e){
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }

    @ExceptionHandler(NotGroupLeaderException.class)
    public ResponseEntity<ErrorCodeMsg> notGroupLeader(NotGroupLeaderException e){
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }

    @ExceptionHandler({CodeDoesntExistException.class})
    public ResponseEntity<ErrorCodeMsg> codeDoesntExist(CodeDoesntExistException e){
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }

    @ExceptionHandler({AlreadyInGroupException.class})
    public ResponseEntity<ErrorCodeMsg> alreadyInGroup(AlreadyInGroupException e){
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
}
