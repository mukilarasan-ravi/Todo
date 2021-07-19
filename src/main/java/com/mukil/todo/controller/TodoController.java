package com.mukil.todo.controller;

import java.util.ArrayList;
import java.util.List;

import com.mukil.todo.bean.Todo;
import com.mukil.todo.service.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController{

	@Autowired
	TodoService service;

	@RequestMapping(value = "/populate", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> populateTodos(@RequestParam(defaultValue = "5") int siz){
		for(int i =1 ; i<=siz; i++){
			service.create(new Todo("Title "+i, "This is description for "+i));
		}
		List<Todo> todos = service.getAllTodo();
		return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> getAllTodos(){
		List<Todo> todos = service.getAllTodo();
		return new ResponseEntity<List<Todo>>(todos, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Todo> getTodo(@PathVariable Long id){
		Todo todo = service.getById(id);
		if(todo == null){
			throw new RuntimeException("No such todo found for id "+id);
		}
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
		todo = service.createOrUpdate(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Todo> updateTodo(@PathVariable Long id,@RequestBody Todo todo){
		Todo oldTodo = service.getById(id);
		if(oldTodo == null){
			throw new RuntimeException("No such todo found for id "+id);
		}
		todo.setId(id);
		todo = service.createOrUpdate(todo);
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteTodo(@PathVariable Long id){
		service.deleteById(id);
	}
}