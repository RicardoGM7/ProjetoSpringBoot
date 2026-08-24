package com.cursoJava.ProjetoSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoJava.ProjetoSpring.entities.Category;
import com.cursoJava.ProjetoSpring.repository.CategoryRepository;

@Service // registra a classe como service do Spring e possibilita a injeçao automatica por Autowired
public class CategoryService {
    
    @Autowired //instancia o objeto automaticamente
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

}
