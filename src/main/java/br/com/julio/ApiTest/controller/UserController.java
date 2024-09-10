package br.com.julio.ApiTest.controller;

import br.com.julio.ApiTest.domain.DTO.UserDTO;
import br.com.julio.ApiTest.domain.User;
import br.com.julio.ApiTest.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(mapper.map(service.findById(id),UserDTO.class));
    }
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> dto = service.findAll().stream().map(x -> mapper.map(x, UserDTO.class)).toList();
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO data){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(data).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id ,@RequestBody UserDTO data){
        data.setId(id);
        return ResponseEntity.ok().body(mapper.map(service.update(data), UserDTO.class));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
