package com.example.BackendMutuario.rest.controller;

import com.example.BackendMutuario.rest.dto.UserLoginDto;
import com.example.BackendMutuario.rest.model.UserModel;
import com.example.BackendMutuario.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/v1/aluno")
@Api(value = "API REST Login")
public class SingInUsersController {

    private final String TOKEN_KEY = "seplan";

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "Faz login de um aluno previamente cadastrado")
    public LoginResponse authenticate( UserLoginDto user) throws ServletException{

        UserModel userCheck = userService.findByCpf(user.getCpf());

        if (userCheck == null){
            throw new ServletException("User not found!");
        }

        if(!userCheck.getPassword().equals(user.getPassword())){
            throw new ServletException("Password is invalid!");
        }

        String token = Jwts.builder().signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)).compact();

        return new LoginResponse(token);
    }

    private class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }
}
