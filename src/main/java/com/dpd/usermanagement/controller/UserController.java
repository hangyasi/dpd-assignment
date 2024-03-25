package com.dpd.usermanagement.controller;

import com.dpd.usermanagement.dto.UserDto;
import com.dpd.usermanagement.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService service;

    @CrossOrigin
    @PostMapping(value = "/user", produces = "application/json")
    public void saveUser(@RequestBody UserDto userDto) {
        service.saveUser(userDto);
    }

    @CrossOrigin
    @GetMapping(value = "/user", produces = "application/json")
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    @CrossOrigin
    @DeleteMapping(value = "/user/{id}", produces = "application/json")
    public void delete(@PathVariable("id") Integer id) {
        service.deleteById(id);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    public Map<Path, String> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Map<Path, String> fieldException = new HashMap<>();
        constraintViolations.stream().forEach(e -> {
            fieldException.put(e.getPropertyPath(), e.getMessage());
        });
        return fieldException;
    }
}
