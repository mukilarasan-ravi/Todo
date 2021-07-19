package com.mukil.todo.controller;

import com.mukil.todo.bean.Todo;

import java.util.HashMap;
import java.util.Collection;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController{

	private static HashMap<Integer,Todo> TODOS = new HashMap<>();
	private static AtomicInteger atomicID = new AtomicInteger(1);

	@RequestMapping(value = "/populate", method = RequestMethod.GET)
	public Collection<Todo> getAllTodos(@RequestParam(defaultValue = "5") int siz){
		if(TODOS.size()==0){
			for(int i =1;i<=siz;i++){
				int id = atomicID.getAndIncrement();
				Todo todo = new Todo("This is task "+i, "Description for "+i);
				todo.setId(id);
				TODOS.put(id,todo);
			}
		}
		return TODOS.values();
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Todo> getAllTodos(){
		return TODOS.values();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Todo getTodo(@PathVariable int id){
		Todo todo = TODOS.get(id);
		if(todo == null){
			throw new RuntimeException("No such todo found for id "+id);
		}
		return todo;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Todo createTodo(@RequestBody Todo todo){
		int id = atomicID.getAndIncrement();
		todo.setId(id);
		TODOS.put(id,todo);
		return todo;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Todo updateTodo(@PathVariable int id,@RequestBody Todo todo){
		Todo oldTodo = TODOS.get(id);
		if(oldTodo == null){
			throw new RuntimeException("No such todo found for id "+id);
		}
		todo.setId(id);
		TODOS.put(id,todo);
		return todo;
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Todo deleteTodo(@PathVariable int id){
		Todo oldTodo = TODOS.get(id);
		if(oldTodo == null){
			throw new RuntimeException("No such todo found for id "+id);
		}
		TODOS.remove(id);
		return oldTodo;
	}
}