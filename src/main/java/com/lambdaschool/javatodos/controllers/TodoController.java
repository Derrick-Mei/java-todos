package com.lambdaschool.javatodos.controllers;

import com.lambdaschool.javatodos.models.Todo;
import com.lambdaschool.javatodos.repositories.TodoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Api(value = "Todos Swagger Application by DKM - Todo Controller", description = "Todo Controller by DKM")
@RestController
@RequestMapping(path = {"/todos"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class TodoController
{

    @Autowired
    TodoRepository todorepos;

    @ApiOperation(value = "find all todos", response = Todo.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully received customer - DKM"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource - DKM"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden - DKM"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found - DKM")
            })
    @GetMapping("")
    public List<Todo> findAllTodos()
    {
        return todorepos.findAll();
    }

    @ApiOperation(value = "find todo by todoid", response = Todo.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully received customer - DKM"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource - DKM"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden - DKM"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found - DKM")
            })
    @GetMapping("/todoid/{todoid}")
    public Todo findTodoById(@PathVariable long todoid)
    {
        var foundTodo = todorepos.findById(todoid);

        return foundTodo.isPresent() ? foundTodo.get() : null;
    }

    @ApiOperation(value = "find all todos paired with their user associated", response = Todo.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully received customer - DKM"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource - DKM"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden - DKM"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found - DKM")
            })
    @GetMapping("/users")
    public List<Object[]> findTodosWithUsername()
    {
        return todorepos.findTodosWithUsername();
    }

    @ApiOperation(value = "find all uncompleted todos", response = Todo.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully received customer - DKM"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource - DKM"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden - DKM"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found - DKM")
            })
    @GetMapping("/active")
    public List<Todo> findAllUncompletedTodos()
    {
        return todorepos.findAllUncompletedTodos();
    }

    @ApiOperation(value = "add new todo", response = Todo.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully received customer - DKM"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource - DKM"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden - DKM"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found - DKM")
            })
    @PostMapping("")
    public Todo addTodo(@RequestBody Todo newTodo) throws URISyntaxException
    {
        return todorepos.save(newTodo);
    }

    @ApiOperation(value = "update a todo by todoid", response = Todo.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully received customer - DKM"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource - DKM"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden - DKM"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found - DKM")
            })
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

    @ApiOperation(value = "delete todo by todoid", response = Todo.class)
    @ApiResponses(value =
            {
                    @ApiResponse(code = 200, message = "Successfully received customer - DKM"),
                    @ApiResponse(code = 401, message = "You are not authorized to the view the resource - DKM"),
                    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden - DKM"),
                    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found - DKM")
            })
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
