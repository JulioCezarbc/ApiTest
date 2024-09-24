package br.com.julio.ApiTest.controller;

import br.com.julio.ApiTest.domain.DTO.UserDTO;
import br.com.julio.ApiTest.domain.User;
import br.com.julio.ApiTest.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @InjectMocks
    private UserController controller;
    @Mock
    private UserServiceImpl service;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userData;

    public static final Long ID = 1L;
    public static final String NAME = "juy";
    public static final String MAIL = "juy@gmail.com";
    public static final String PASSWORD = "123";

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startUser();
    }


    private void startUser(){
        user = new User(ID, NAME, MAIL, PASSWORD);
        userData = new UserDTO(ID, NAME, MAIL, PASSWORD);
    }

}