package br.com.julio.ApiTest.service;

import br.com.julio.ApiTest.domain.DTO.UserDTO;
import br.com.julio.ApiTest.domain.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    List<User> findAll();
    User create(UserDTO data);
}
