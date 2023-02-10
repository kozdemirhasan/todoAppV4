package de.kozdemir.todoappv4.services;

import de.kozdemir.todoappv4.model.Todo;
import de.kozdemir.todoappv4.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    }

    public List<Todo> search(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public List<Todo> findByAllWithUserId(Long id) {
        if (id != null) {
            return repo.findByAllWithUserId(id);
        }
        return repo.findAll();
    }
}
