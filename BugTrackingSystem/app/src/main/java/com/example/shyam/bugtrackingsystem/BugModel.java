package com.example.shyam.bugtrackingsystem;



public class BugModel {
	

	int id;
	String bugdesc;
	String bugpriority;
	String projectid;


	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
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
