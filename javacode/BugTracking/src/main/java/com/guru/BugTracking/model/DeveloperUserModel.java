package com.guru.BugTracking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class DeveloperUserModel {
	
	@Id
	@GeneratedValue
	int id;
	String password;
	
	@OneToOne
	@JoinColumn(name="developerid")
	@JsonBackReference
	public DeveloperModel developerModel;
	
	
	
	
	public DeveloperModel getDeveloperModel() {
		return developerModel;
	}
	public void setDeveloperModel(DeveloperModel developerModel) {
		this.developerModel = developerModel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
