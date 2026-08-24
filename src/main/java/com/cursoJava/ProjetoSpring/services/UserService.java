package com.cursoJava.ProjetoSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoJava.ProjetoSpring.entities.User;
import com.cursoJava.ProjetoSpring.repository.UserRepository;

@Service // registra a classe como service do Spring e possibilita a injeçao automatica por Autowired
public class UserService {
    

    @Autowired //instancia o objeto automaticamente
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

}
