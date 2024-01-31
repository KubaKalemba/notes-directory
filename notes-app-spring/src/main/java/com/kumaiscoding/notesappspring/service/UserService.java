package com.kumaiscoding.notesappspring.service;

import com.kumaiscoding.notesappspring.dao.UserRepository;
import com.kumaiscoding.notesappspring.dto.CredentialsDto;
import com.kumaiscoding.notesappspring.dto.UserDto;
import com.kumaiscoding.notesappspring.entity.User;
import com.kumaiscoding.notesappspring.exception.AppException;
import com.kumaiscoding.notesappspring.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;

@Service
public class UserService {

    private final UserRepository userRepository;


    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.getUsername())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if(new String(credentialsDto.getPassword()).equals(user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto findByLogin(String login) {
        User user = userRepository.findByUsername(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

}
