package de.kozdemir.todoappv4.controller;

import de.kozdemir.todoappv4.model.Todo;
import de.kozdemir.todoappv4.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private TodoService service;

    @GetMapping("/todos")
    public List<Todo> getAllProducts() {
        return service.findAll();
    }

    @GetMapping("/todos/{id}")
    public Todo getOneProduct(@PathVariable Integer id) {
        return service.findById(id).orElse(new Todo());
    }

}
