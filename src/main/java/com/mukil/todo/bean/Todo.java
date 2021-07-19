package com.mukil.todo.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Todo")
public class Todo{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="title", nullable=false, length=200)
	private String title;
	@Column(name="description", nullable=true, length=2000)
	private String description;

	public Todo(){
	}
	public Todo(String title,String description){
		this.title = title;
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return this.id;
	}

	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}

	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}

}