package br.com.julio.ApiTest.service.Impl;

import br.com.julio.ApiTest.domain.User;
import br.com.julio.ApiTest.repository.UserRepository;
import br.com.julio.ApiTest.service.UserService;
import br.com.julio.ApiTest.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("User not found"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
}
