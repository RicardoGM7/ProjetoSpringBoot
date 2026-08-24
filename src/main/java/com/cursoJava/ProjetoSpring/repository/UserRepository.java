package com.cursoJava.ProjetoSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cursoJava.ProjetoSpring.entities.User;


//ganho metodos crud automaticamente quando eu passo a classe e a chave primaria
public interface UserRepository extends JpaRepository<User, Long>{
    
}
