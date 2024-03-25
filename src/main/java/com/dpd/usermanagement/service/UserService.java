package com.dpd.usermanagement.service;

import com.dpd.usermanagement.dto.AddressDto;
import com.dpd.usermanagement.dto.PhoneNumberDto;
import com.dpd.usermanagement.dto.UserDto;
import com.dpd.usermanagement.entity.Address;
import com.dpd.usermanagement.entity.PhoneNumber;
import com.dpd.usermanagement.entity.User;
import com.dpd.usermanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private EntityMapperService mapperService;

    public List<UserDto> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> mapperService.userToDto(user))
                .collect(Collectors.toList());
    }

    public void saveUser(UserDto userDto) {
        User user = mapperService.dtoToUser(userDto);
        userRepository.save(user);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
