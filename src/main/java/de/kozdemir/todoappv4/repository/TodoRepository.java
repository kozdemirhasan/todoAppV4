package de.kozdemir.todoappv4.repository;


import de.kozdemir.todoappv4.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {


    @Query("SELECT p FROM Todo p WHERE CONCAT(p.description, p.createdDate) LIKE %?1%")
    public List<Todo> search(String keyword);

    public List<Todo> findByFirstnameLike();

}
