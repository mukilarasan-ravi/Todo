package com.mukil.todo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mukil.todo.bean.Todo;

@Repository
public interface TodoDAO extends JpaRepository<Todo, Long> {
}