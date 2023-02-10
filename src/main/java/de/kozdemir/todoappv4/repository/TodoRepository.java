package de.kozdemir.todoappv4.repository;


import de.kozdemir.todoappv4.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("SELECT t FROM Todo t WHERE lower(t.description) LIKE %?1%")
    public List<Todo> search(String keyword);

    @Query("SELECT t FROM Todo t  where t.user.id = ?1")
    public List<Todo>  findByAllWithUserId(Long id);


}
