package com.example.BackendMutuario.rest.controller;

import com.example.BackendMutuario.rest.dto.UserOutputDto;
import com.example.BackendMutuario.rest.model.UserModel;
import com.example.BackendMutuario.rest.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.jni.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/v1/user"})
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/")
    @ResponseBody
    @ApiOperation(value="Create a user")
    public ResponseEntity<UserModel> create(@RequestBody UserModel user) {
        if (userService.findByCpf(user.getCpf()) != null) {
            throw new InternalError("User already signed up");
        }

        UserModel newUser = userService.create(user);

        if (newUser == null) {
            throw new InternalError("Something went wrong");
        }

        return new ResponseEntity<UserModel>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value ="/allusers")
    @ResponseBody
    @ApiOperation(value = "return all users")
    public ResponseEntity<List<UserOutputDto>> findAllAlunos() {
        List<UserOutputDto> usersOutput = userService.findAll();

        return new ResponseEntity<List<UserOutputDto>>(usersOutput, HttpStatus.OK);
    }
}
