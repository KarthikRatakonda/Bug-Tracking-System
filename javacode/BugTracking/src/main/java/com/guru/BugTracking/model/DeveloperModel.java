package com.guru.BugTracking.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class DeveloperModel {
	
	@Id
	@GeneratedValue
	int id;
	String empid;
	String name;
	String mobile;
	String email;
	String role;
	
@OneToOne(mappedBy="developerModel")
@JsonManagedReference
public DeveloperUserModel developerUserModel;


@OneToMany(mappedBy="developerModel")
@JsonManagedReference
public List<ComplaintModel> complaintModel;

@OneToMany(mappedBy="developerModel")
@JsonManagedReference
public List<ProjectModel> projectModel;


@OneToMany(mappedBy="developerModel")
@JsonBackReference
public List<BugModel> bugModel;






	public List<BugModel> getBugModel() {
	return bugModel;
}
public void setBugModel(List<BugModel> bugModel) {
	this.bugModel = bugModel;
}
	public List<ProjectModel> getProjectModel() {
	return projectModel;
}
public void setProjectModel(List<ProjectModel> projectModel) {
	this.projectModel = projectModel;
}
	public List<ComplaintModel> getComplaintModel() {
	return complaintModel;
}
public void setComplaintModel(List<ComplaintModel> complaintModel) {
	this.complaintModel = complaintModel;
}
	public DeveloperUserModel getDeveloperUserModel() {
	return developerUserModel;
}
public void setDeveloperUserModel(DeveloperUserModel developerUserModel) {
	this.developerUserModel = developerUserModel;
}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
