package de.kozdemir.todoappv3.service;

import de.kozdemir.todoappv3.model.Todo;
import de.kozdemir.todoappv3.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repo;

    @Autowired
    public TodoService() {

    }

    public List<Todo> findAll() {
        return repo.findAll();
    }

    public void save(Todo todo) {
        repo.save(todo);
    }

    public void delete(Todo todo) {
        repo.delete(todo);
    }



    public Optional<Todo> findById(Integer id) {

        return repo.findById(id);

        /*
        Product product = null;
        for(Product p : products) {
            if(p.getId() == id) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
        */
    }

}
