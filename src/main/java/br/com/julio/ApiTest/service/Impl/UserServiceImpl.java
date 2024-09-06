package br.com.julio.ApiTest.service.Impl;

import br.com.julio.ApiTest.domain.DTO.UserDTO;
import br.com.julio.ApiTest.domain.User;
import br.com.julio.ApiTest.repository.UserRepository;
import br.com.julio.ApiTest.service.UserService;
import br.com.julio.ApiTest.service.exception.DataIntegrityViolationException;
import br.com.julio.ApiTest.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("User not found"));
    }
    @Override
    public List<User> findAll() {
        return repository.findAll();
    }
    @Override
    public User create(UserDTO data) {
        return repository.save(mapper.map(data,User.class));
    }

}
