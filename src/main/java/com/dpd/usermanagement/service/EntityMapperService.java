package com.dpd.usermanagement.service;

import com.dpd.usermanagement.dto.UserDto;
import com.dpd.usermanagement.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EntityMapperService {

    private ModelMapper mapper;

    public UserDto userToDto(User user) {
        return mapper.map(user, UserDto.class);
    }
    public User dtoToUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.getPhoneNumbers().forEach(pn -> pn.setUser(user));
        user.getAddresses().forEach(add -> add.setUser(user));
        return user;
    }
}
