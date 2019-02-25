package com.lambdaschool.javatodos.controllers;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = {"/todos"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController
{

    @Autowired
    TodoRepository todorepos;

    @GetMapping("")
    public List<Todo> findAllTodos()
    {
        return todorepos.findAll();
    }

    @GetMapping("/todoid/{todoid}")
    public Todo findTodoById(@PathVariable long todoid)
    {
        var foundTodo = todorepos.findById(todoid);

        return foundTodo.isPresent() ? foundTodo.get() : null;
    }

    @GetMapping("/users")
    public List<Object[]> findTodosWithUsername()
    {
        return todorepos.findTodosWithUsername();
    }

    @GetMapping("/active")
    public List<Todo> findAllUncompletedTodos()
    {
        return todorepos.findAllUncompletedTodos();
    }

    @PostMapping("")
    public Todo addTodo(@RequestBody Todo newTodo) throws URISyntaxException
    {
        return todorepos.save(newTodo);
    }

    @PutMapping("/todoid/{todoid}")
    public Todo updateTodo(@RequestBody Todo updatedTodo, @PathVariable long todoid)
    {
        var todoById = todorepos.findById(todoid);
        if (todoById.isPresent())
        {
            updatedTodo.setTodoid(todoid);
            return todorepos.save(updatedTodo);
        }
        else
        {
            return null;
        }
    }

    @DeleteMapping("/todoid/{todoid}")
    public Optional<Todo> deleteTodoById(@PathVariable long todoid)
    {
        Optional<Todo> todoToBeDeleted = todorepos.findById(todoid);
        if (todoToBeDeleted.isPresent())
        {
            todorepos.deleteById(todoid);
            return todoToBeDeleted;
        }
        else
        {
            return null;
        }
    }

}
