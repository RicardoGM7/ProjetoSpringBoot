package com.cursoJava.ProjetoSpring.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursoJava.ProjetoSpring.entities.Product;
import com.cursoJava.ProjetoSpring.services.ProductService;


@RestController
@RequestMapping(value = "/products")
public class ProductResource {
    
    @Autowired
    private ProductService Service;


    @GetMapping // define como metodo get
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = Service.findAll();
        return ResponseEntity.ok().body(list);
    };

    @GetMapping(value = "/{id}") // metodo get com argumento
    public ResponseEntity<Product> findById(@PathVariable Long id){ // determina que o id é o parametro da URL
        Product obj = Service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
