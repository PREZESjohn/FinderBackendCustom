package com.example.project.services;

import com.example.project.exceptions.AlreadyInGroupException;
import com.example.project.exceptions.UserNotFoundException;
import com.example.project.model.NotificationMsgDTO;
import com.example.project.domain.GroupRoom;
import com.example.project.domain.Role;
import com.example.project.domain.User;
import com.example.project.exceptions.GroupNotFoundException;
import com.example.project.mappers.UserGroupListMapper;
import com.example.project.mappers.UserMapper;
import com.example.project.model.UserDTO;
import com.example.project.model.UserGroupsListDTO;
import com.example.project.model.UserProfileDTO;
import com.example.project.repositories.GroupRepository;
import com.example.project.repositories.RoleRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.utils.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final RoleRepository roleRepository;
    private final UserGroupListMapper userGroupListMapper;
    private final SseService sseService;



    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user->userMapper.mapUserToUserDTO(user))
                .collect(Collectors.toList());
    }



    @Override
    public UserDTO save(UserDTO userDTO) {

        Role userRole = roleRepository.findByName("ROLE_USER");
        User user = userMapper.mapUserDTOToUser(userDTO);
        user.setRole(userRole);
        return userMapper.mapUserToUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO saveAndReturnDTO(User user) {
        return userMapper.mapUserToUserDTO(userRepository.save(user));
    }


    @Override
    public UserDTO updateUserByDTO(UserDTO userDTO) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = currentUser.getId();
        User user = userRepository.findById(id).orElseThrow(() -> new GroupNotFoundException("User not found id:"+id));
        return saveAndReturnDTO(userMapper.updateUserFromUserDTO(userDTO,user));
    }

    @Override
    public UserDTO getLoggedUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = currentUser.getId();
        return userRepository.findById(id)
                .map(userMapper::mapUserToUserDTO)
                .map(userDTO -> {
                    userDTO.setId(id);
                    return userDTO;})
                .orElseThrow(() -> new UserNotFoundException("User not found id:" +id));
    }

    @Override
    public UserGroupsListDTO getUserGroups() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long id = currentUser.getId();
        return userRepository.findById(id)
                .map(userGroupListMapper::mapuserToUserGroupsListDTO).orElseThrow(() -> new UserNotFoundException("User not found id:"+id));
    }

    @Override
    public UserDTO joinGroupRoom(Long groupRoomId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(currentUser.getId()).orElseThrow(() -> new UserNotFoundException("User not found id:"+currentUser.getId()));;


        GroupRoom groupRoom = groupRepository.findById(groupRoomId)
                .orElseThrow(() -> new GroupNotFoundException("Group room not found"));

        if(user.getGroupRooms().contains(groupRoom)){
            throw new AlreadyInGroupException("User id:"+user.getId()+" is already in group");
        }else{
        user.getGroupRooms().add(groupRoom);
        userRepository.save(user);
            sseService.sendSseEventToUser(NotificationMsgDTO.builder().text(user.getUsername()+" joined group").isNegative(false).build(),groupRoom,null);
            return userMapper.mapUserToUserDTO(user);
        }
    }

    @Override
    public void changeProfilePicture(MultipartFile profilePicture) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(currentUser.getId()).orElseThrow(() -> new UserNotFoundException("User not found id:"+currentUser.getId()));
        //TODO zrobic usuwanie aktualnego zdjecia z folderu zeby nie bylo syfu
        user.setProfileImgName(user.getId()+"-"+FileHandler.save(profilePicture,user.getId()));
        userRepository.save(user);
    }

    @Override
    public Resource getProfilePicture(Long userId) {
            User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found id:" + userId));
            if (user.getProfileImgName() != null) {
                return FileHandler.load(user.getProfileImgName());
            }
        return null;
    }

    @Override
    public UserProfileDTO getUserProfile(Long userId) {

        return userRepository.findById(userId)
                .map(userMapper::mapUserToUserProfileDTO)
                .orElseThrow(() -> new GroupNotFoundException("Group room not found"));
    }

    @Override
    public void getOutOfGroup(Long groupRoomId) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(currentUser.getId()).orElseThrow(() -> new GroupNotFoundException("User not found id:"+currentUser.getId()));;


        GroupRoom groupRoom = groupRepository.findById(groupRoomId)
                .orElseThrow(() -> new GroupNotFoundException("Group room not found"));

        user.getGroupRooms().remove(groupRoom);
        if(Objects.equals(groupRoom.getGroupLeader(),user)){
        groupRoom.setGroupLeader(groupRoom.getUsers().get(0));}
        if(groupRoom.getUsers().size()==1){
            groupRepository.softDeleteById(groupRoom.getId());
        }else
        {
            groupRepository.save(groupRoom);
        }
        userRepository.save(currentUser);

    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
