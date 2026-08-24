package com.cursoJava.ProjetoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursoJava.ProjetoSpring.entities.Order;


//ganho metodos crud automaticamente quando eu passo a classe e a chave primaria
public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
