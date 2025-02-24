package com.guru.BugTracking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ComplaintModel {
	
	@Id
	@GeneratedValue
	int id;
	String compltype;
	String compldesc;
	String status;
	
	@ManyToOne
	@JoinColumn(name="developerid")
	@JsonBackReference
	public DeveloperModel developerModel;
	
	
	
	

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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
	public String getCompltype() {
		return compltype;
	}
	public void setCompltype(String compltype) {
		this.compltype = compltype;
	}
	public String getCompldesc() {
		return compldesc;
	}
	public void setCompldesc(String compldesc) {
		this.compldesc = compldesc;
	}
	
	

}
