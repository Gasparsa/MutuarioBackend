package com.example.BackendMutuario.rest.service;

import com.example.BackendMutuario.exceptions.UserNotFoundException;
import com.example.BackendMutuario.rest.dao.UserDao;
import com.example.BackendMutuario.rest.dto.UserOutputDto;
import com.example.BackendMutuario.rest.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;

    UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserModel create(UserModel User) {
        return userDao.save(User);
    }

    public UserModel update(UserModel UserToUpdate) throws UserNotFoundException {

        UserModel user = userDao.findByCpf(UserToUpdate.getEmail());
        if (user == null)
            throw new UserNotFoundException("Could not update. The User does not exist.");

        return userDao.save(UserToUpdate);
    }

    public UserModel findByCpf(String cpf) {
        return userDao.findByCpf(cpf);
    }

    public void deleteByCpf(String cpf) {
        userDao.deleteByCpf(cpf);
    }

    public List<UserOutputDto> findAll() {
        List<UserModel> users = userDao.findAll();
        List<UserOutputDto> usersOutput = new ArrayList<>();
        for (UserModel user : users){
            UserOutputDto user2 = new UserOutputDto(user.getName(), user.getCpf(), user.getEmail());
            usersOutput.add(user2);
        }
        return usersOutput;
    }
}
