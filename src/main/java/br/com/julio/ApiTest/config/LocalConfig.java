package br.com.julio.ApiTest.config;

import br.com.julio.ApiTest.domain.User;
import br.com.julio.ApiTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    private UserRepository repository;

    @Bean
    public User startDB(){
        User u1 = new User(null, "jay","jay@gmail.com","1234");
        User u2 = new User(null, "juy","juy@gmail.com","123");
        repository.saveAll(List.of(u1,u2));
        return u1;
    }
}
