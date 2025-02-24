package com.guru.BugTracking.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class ProjectModel {
	
	@Id
	@GeneratedValue
	int id;
	String projectname;
	String projectdesc;
	String startdate;
	String enddate;
	String  empid;
	
	@ManyToOne
	@JoinColumn(name="developerid")
	@JsonBackReference
	public DeveloperModel developerModel;
	
	@OneToMany(mappedBy="projectModel")
	@JsonManagedReference
	public List<BugModel> bugModel;
	
	
	
	public List<BugModel> getBugModel() {
		return bugModel;
	}
	public void setBugModel(List<BugModel> bugModel) {
		this.bugModel = bugModel;
	}
	public DeveloperModel getDeveloperModel() {
		return developerModel;
	}
	public void setDeveloperModel(DeveloperModel developerModel) {
		this.developerModel = developerModel;
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
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getProjectdesc() {
		return projectdesc;
	}
	public void setProjectdesc(String projectdesc) {
		this.projectdesc = projectdesc;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	
	

}
