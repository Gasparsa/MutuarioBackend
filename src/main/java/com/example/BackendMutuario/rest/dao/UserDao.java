package com.example.BackendMutuario.rest.dao;

import com.example.BackendMutuario.rest.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.util.List;

@Repository
public interface UserDao<T, ID extends Serializable> extends JpaRepository<UserModel, String> {

    UserModel deleteByCpf(String cpf);
    UserModel save(UserModel userModel);
    UserModel findByCpf(String cpf);
    @Query( value = "select * from User_Model order by name",nativeQuery = true)
    List<UserModel> findByOrdernameAsc();
}
