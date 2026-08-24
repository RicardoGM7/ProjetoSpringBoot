package com.cursoJava.ProjetoSpring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursoJava.ProjetoSpring.entities.Order;
import com.cursoJava.ProjetoSpring.repository.OrderRepository;

@Service // registra a classe como service do Spring e possibilita a injeçao automatica por Autowired
public class OrderService {
    

    @Autowired //instancia o objeto automaticamente
    private OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }

}
