package com.example.st.model;


import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    private String id;   // use same UUID string as frontend

    @Column(nullable = false)
    private String username;   // from JWT

    private String name;
    private double estimatedHours;
    private double actualHours;
    private boolean completed;

    public Subject() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(double estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public double getActualHours() {
		return actualHours;
	}

	public void setActualHours(double actualHours) {
		this.actualHours = actualHours;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

    // getters & setters
    
}
