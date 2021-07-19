package com.mukil.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mukil.todo.bean.Todo;
import com.mukil.todo.dao.TodoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService{
	@Autowired
	TodoDAO dao;

	public List<Todo> getAllTodo(){
		List<Todo> todos = dao.findAll();
		return todos;
	}

	public Todo getById(Long id){
		Optional<Todo> todo = dao.findById(id);
         
        if(todo.isPresent()) {
            return todo.get();
        } else {
        	return null;
        }
	}
	public Todo create(Todo todo){
		todo = dao.save(todo);
		return todo;
	}
	public Todo update(Todo todo){
		Optional<Todo> dbTodo = dao.findById(todo.getId());
		if(dbTodo.isPresent()) {
			Todo updateTodo = dbTodo.get();
			updateTodo.setTitle(todo.getTitle());
			updateTodo.setDescription(todo.getDescription());

			updateTodo = dao.save(updateTodo);
			return updateTodo;
		}else{
			return null;
		}
	}
	public Todo createOrUpdate(Todo todo){
		Long id = todo.getId();
		if( id == null ){
			return create(todo);
		}
		Todo updated = update(todo);
		if(updated==null) {
			updated=create(todo);
		}
		return updated;

	}

	public void deleteById(Long id){
		Optional<Todo> dbTodo = dao.findById(id);
		if(dbTodo.isPresent()) {
			dao.deleteById(id);
		}
	}
}