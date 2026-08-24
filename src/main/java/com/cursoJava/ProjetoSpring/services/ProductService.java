package com.cursoJava.ProjetoSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoJava.ProjetoSpring.entities.Product;
import com.cursoJava.ProjetoSpring.repository.ProductRepository;

@Service // registra a classe como service do Spring e possibilita a injeçao automatica por Autowired
public class ProductService {
    
    @Autowired //instancia o objeto automaticamente
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }

}
