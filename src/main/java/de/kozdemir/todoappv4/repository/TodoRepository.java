package de.kozdemir.todoappv3.repository;

import de.kozdemir.todoappv3.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {


}
