package com.cursoJava.ProjetoSpring.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cursoJava.ProjetoSpring.entities.User;
import com.cursoJava.ProjetoSpring.services.UserService;


@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    @Autowired
    private UserService Service;


    @GetMapping // define como metodo get
    public ResponseEntity<List<User>> findAll() {
        List<User> list = Service.findAll();
        return ResponseEntity.ok().body(list);
    };

    @GetMapping(value = "/{id}") // metodo get com argumento
    public ResponseEntity<User> findById(@PathVariable Long id){ // determina que o id é o parametro da URL
        User obj = Service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
