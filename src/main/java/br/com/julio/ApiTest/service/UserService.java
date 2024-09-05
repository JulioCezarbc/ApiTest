package br.com.julio.ApiTest.service;

import br.com.julio.ApiTest.domain.User;

public interface UserService {

    User findById(Long id);

}
