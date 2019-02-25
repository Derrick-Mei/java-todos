package com.lambdaschool.javatodos.repositories;

import com.lambdaschool.javatodos.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long>
{
    @Query(value = "SELECT todo.description, users.username FROM todo, users ORDER BY users.username", nativeQuery = true)
    public List<Object[]> findTodosWithUsername();

    @Query(value = "SELECT * FROM todo WHERE todo.completed = 0", nativeQuery = true)
    public List<Todo> findAllUncompletedTodos();
}
