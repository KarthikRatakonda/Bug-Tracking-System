package com.guru.BugTracking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BugModel {
	
	@Id
	@GeneratedValue
	int id;
	String bugdesc;
	String bugpriority;
	
	@ManyToOne
	@JoinColumn(name="projectid")
	@JsonBackReference
	public ProjectModel projectModel;
	
	@ManyToOne
	@JoinColumn(name="developerid")
	@JsonBackReference
	public DeveloperModel developerModel;
	
	
	
	public DeveloperModel getDeveloperModel() {
		return developerModel;
	}
	public void setDeveloperModel(DeveloperModel developerModel) {
		this.developerModel = developerModel;
	}
	public ProjectModel getProjectModel() {
		return projectModel;
	}
	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBugdesc() {
		return bugdesc;
	}
	public void setBugdesc(String bugdesc) {
		this.bugdesc = bugdesc;
	}
	public String getBugpriority() {
		return bugpriority;
	}
	public void setBugpriority(String bugpriority) {
		this.bugpriority = bugpriority;
	}
	
	
	
	

}
