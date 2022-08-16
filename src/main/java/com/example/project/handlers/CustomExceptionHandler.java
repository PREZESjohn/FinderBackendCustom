package com.example.project.handlers;

import com.example.project.exceptions.*;
import com.example.project.exceptions.validation.*;
import com.example.project.model.ErrorCodeMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorCodeMsg> userNotFound(UserNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<ErrorCodeMsg> groupNotFound(GroupNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }

    @ExceptionHandler(NotGroupLeaderException.class)
    public ResponseEntity<ErrorCodeMsg> notGroupLeader(NotGroupLeaderException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }

    @ExceptionHandler({CodeDoesntExistException.class})
    public ResponseEntity<ErrorCodeMsg> codeDoesntExist(CodeDoesntExistException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }

    @ExceptionHandler({AlreadyInGroupException.class})
    public ResponseEntity<ErrorCodeMsg> alreadyInGroup(AlreadyInGroupException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorCodeMsg> usernameNotFound(UsernameNotFoundException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().build());
    }
    @ExceptionHandler({BadAgeException.class})
    public ResponseEntity<ErrorCodeMsg> badAge(BadAgeException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadCityException.class})
    public ResponseEntity<ErrorCodeMsg> badCity(BadCityException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadEmailException.class})
    public ResponseEntity<ErrorCodeMsg> badEmail(BadEmailException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadGroupDescException.class})
    public ResponseEntity<ErrorCodeMsg> badGroupDesc(BadGroupDescException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadGroupNameException.class})
    public ResponseEntity<ErrorCodeMsg> badGroupName(BadGroupNameException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadPasswordException.class})
    public ResponseEntity<ErrorCodeMsg> badPassword(BadPasswordException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadPhoneException.class})
    public ResponseEntity<ErrorCodeMsg> badPhone(BadPhoneException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadProfileInfoException.class})
    public ResponseEntity<ErrorCodeMsg> badProfileInfo(BadProfileInfoException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadProfileNameException.class})
    public ResponseEntity<ErrorCodeMsg> badProfileName(BadProfileNameException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadUserLimitException.class})
    public ResponseEntity<ErrorCodeMsg> badUserLimit(BadUserLimitException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({BadUsernameException.class})
    public ResponseEntity<ErrorCodeMsg> badUsername(BadUsernameException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({EmailAlreadyTakenException.class})
    public ResponseEntity<ErrorCodeMsg> emailAlreadyTaken(EmailAlreadyTakenException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({GroupnameAlreadyTakenException.class})
    public ResponseEntity<ErrorCodeMsg> groupnameAlreadyTaken(GroupnameAlreadyTakenException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
    @ExceptionHandler({UsernameAlreadyTakenException.class})
    public ResponseEntity<ErrorCodeMsg> usernameAlreadyTaken(UsernameAlreadyTakenException e){
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(ErrorCodeMsg.builder().code(e.getCode()).build());
    }
}
