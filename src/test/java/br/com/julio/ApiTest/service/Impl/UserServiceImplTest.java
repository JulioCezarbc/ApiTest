package br.com.julio.ApiTest.service.Impl;

import br.com.julio.ApiTest.domain.DTO.UserDTO;
import br.com.julio.ApiTest.domain.User;
import br.com.julio.ApiTest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "juy";
    public static final String MAIL = "juy@gmail.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserServiceImpl service;
    @Mock
    private UserRepository repository;
    @Mock
    private ModelMapper mapper;


    private User user;
    private UserDTO userData;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance(){
        when(repository.findById(anyLong())).thenReturn(optionalUser);

        User responde = service.findById(ID);

        assertNotNull(responde);
        assertEquals(User.class, responde.getClass());
        assertEquals(ID, responde.getId());
        assertEquals(NAME, responde.getName());
        assertEquals(MAIL, responde.getEmail());
        assertEquals(PASSWORD, responde.getPassword());
    }

    private void startUser(){
        user = new User(ID, NAME, MAIL, PASSWORD);
        userData = new UserDTO(ID, NAME, MAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, MAIL, PASSWORD));
    }
}