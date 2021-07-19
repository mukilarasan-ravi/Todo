package com.mukil.todo.bean;

public class Todo{

	private int id;
	private String title;
	private String description;

	public Todo(String title,String description){
		this.title = title;
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
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