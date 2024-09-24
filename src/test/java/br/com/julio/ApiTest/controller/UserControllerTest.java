package br.com.julio.ApiTest.controller;

import br.com.julio.ApiTest.domain.DTO.UserDTO;
import br.com.julio.ApiTest.domain.User;
import br.com.julio.ApiTest.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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

    @Test
    void whenFindByIdThenReturnSuccess(){
        when(service.findById(anyLong())).thenReturn(user);
        when(mapper.map(any(),any())).thenReturn(userData);

        ResponseEntity<UserDTO> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID,response.getBody().getId());
        assertEquals(NAME,response.getBody().getName());
        assertEquals(MAIL,response.getBody().getEmail());
        assertEquals(PASSWORD,response.getBody().getPassword());
    }

    @Test
    void whenFindAllThenReturnAListOfUserDTO(){
        when(service.findAll()).thenReturn(List.of(user));
        when(mapper.map(any(),any())).thenReturn(userData);

        ResponseEntity<List<UserDTO>> response = controller.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getFirst().getClass());
        assertTrue(response.getBody() instanceof List);

        assertEquals(ID,response.getBody().getFirst().getId());
        assertEquals(NAME,response.getBody().getFirst().getName());
        assertEquals(MAIL,response.getBody().getFirst().getEmail());
        assertEquals(PASSWORD,response.getBody().getFirst().getPassword());
    }

    @Test
    void whenCreateThenReturnCreated(){
        when(service.create(any())).thenReturn(user);

        ResponseEntity<UserDTO> response = controller.create(userData);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
        assertEquals(ResponseEntity.class, response.getClass());

    }

    @Test
    void whenUpdateThenReturnSuccess(){
        when(service.update(userData)).thenReturn(user);
        when(mapper.map(any(),any())).thenReturn(userData);

        ResponseEntity<UserDTO> response = controller.update(ID,userData);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID,response.getBody().getId());
        assertEquals(NAME,response.getBody().getName());
        assertEquals(MAIL,response.getBody().getEmail());
    }

    private void startUser(){
        user = new User(ID, NAME, MAIL, PASSWORD);
        userData = new UserDTO(ID, NAME, MAIL, PASSWORD);
    }

}