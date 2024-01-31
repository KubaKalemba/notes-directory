package com.kumaiscoding.notesappspring.mapper;

import com.kumaiscoding.notesappspring.dto.UserDto;
import com.kumaiscoding.notesappspring.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public class UserMapper {

    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        return userDto;
    }

}
