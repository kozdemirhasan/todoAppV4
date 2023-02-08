package de.kozdemir.todoappv4.repository;


import de.kozdemir.todoappv4.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {


}
