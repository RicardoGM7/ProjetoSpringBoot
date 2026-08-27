package com.cursoJava.ProjetoSpring.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cursoJava.ProjetoSpring.entities.User;
import com.cursoJava.ProjetoSpring.services.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    @Autowired
    private UserService Service;


    @GetMapping // define como metodo get
    public ResponseEntity<List<User>> findAll() {
        List<User> list = Service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}") // metodo get com argumento
    public ResponseEntity<User> findById(@PathVariable Long id){ // determina que o id é o parametro da URL
        User obj = Service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = Service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

   @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Service.delete(id);
        return ResponseEntity.noContent().build();
   }
   @PutMapping(value = "/{id}")
   public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
    obj = Service.update(id, obj);
    return ResponseEntity.ok().body(obj);
   }

}


