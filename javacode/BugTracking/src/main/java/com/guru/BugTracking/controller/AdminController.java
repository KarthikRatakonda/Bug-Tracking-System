package com.guru.BugTracking.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guru.BugTracking.model.BugModel;
import com.guru.BugTracking.model.ComplaintModel;
import com.guru.BugTracking.model.DeveloperModel;
import com.guru.BugTracking.model.ProjectModel;
import com.guru.BugTracking.service.BugRepository;
import com.guru.BugTracking.service.ComplaintRepository;
import com.guru.BugTracking.service.DeveloperRepository;
import com.guru.BugTracking.service.ProjectRepository;
@RestController
public class AdminController {
	
	@Autowired
	DeveloperRepository developerRepository;
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	BugRepository bugRepository;
	
	
	@RequestMapping(value="/addDeveloper",method=RequestMethod.POST)
	public String addDeveloper(@RequestParam("empid") String empid,@RequestParam("name") String name,
			@RequestParam("mobile") String mobile,@RequestParam("email") String email,@RequestParam("role") String role) {
		String s="";
		DeveloperModel dm1=developerRepository.findByempidAndRole(empid,role);
		if((dm1==null)) {
		
		DeveloperModel dm=new DeveloperModel();
		dm.setEmpid(empid);dm.setName(name);dm.setMobile(mobile);dm.setEmail(email);dm.setRole(role);
		 dm=developerRepository.save(dm);
		 s="success";
		}else {
			 s="found";
		}
		return s;
		}
	@RequestMapping(value="/getDeveloper",method=RequestMethod.GET)
	public List<DeveloperModel> getDeveloper(@RequestParam("developer") String  developer) {
		
		
		return developerRepository.findByrole(developer);
		
		}
	
	@RequestMapping(value="/addProjects",method=RequestMethod.POST)
	public ProjectModel addProjects(@RequestParam("projectname") String projectname,@RequestParam("projectdesc") String projectdesc,
			@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate) {
		
		ProjectModel pm=new ProjectModel();
		pm.setProjectname(projectname); pm.setProjectdesc(projectdesc);pm.setStartdate(startdate);pm.setEnddate(enddate);
		pm.setEmpid("no");
		
		
		return projectRepository.save(pm);
		
		}
	
	
	
	@RequestMapping(value="/getProjects",method=RequestMethod.GET)
	public List<ProjectModel> getProjects() {
		

		return projectRepository.findAll();		
	
		
		}
	
	
	@RequestMapping(value="/assignProjects",method=RequestMethod.POST)
	public ProjectModel assignProjects(@RequestParam("projectid") int id,@RequestParam("developerid") String developerid) {
		
	DeveloperModel dm=developerRepository.findByempid(developerid);
	ProjectModel pm=projectRepository.findOne(id);
	pm.setEmpid(developerid);
	pm.setDeveloperModel(dm);
		return projectRepository.save(pm);
		
	
		
		}
	
	
	
	@RequestMapping(value="/getAssignProjects",method=RequestMethod.GET)
	public List<ProjectModel> assignProjects() {
		
		List<ProjectModel> pm=projectRepository.findAll();
		List<ProjectModel> projectModels=new ArrayList<>();
		
		for(ProjectModel  pm1:pm) {
			if(pm1.getEmpid().equalsIgnoreCase("no")) {
				
			}else {
				projectModels.add(pm1);
			}
		}
		
		
return projectModels;
		
	
		
		}
	
	
@RequestMapping(value="/viewCompl",method=RequestMethod.GET)
	public List<ComplaintModel>  viewCompl() {
		
	return complaintRepository.findAll();
		}

@RequestMapping(value="/updateCompl",method=RequestMethod.GET)
public ComplaintModel  updateCompl(@RequestParam("compid")int compid) {
	
	ComplaintModel cm=complaintRepository.findOne(compid);
	cm.setStatus("completed");
	complaintRepository.save(cm);
	
return cm;
}



@RequestMapping(value="/viewBugs",method=RequestMethod.GET)
public List<BugModel>  viewBugs() {
	
return bugRepository.findAll();
	}


@RequestMapping(value="/viewDevelopers",method=RequestMethod.GET)
public List<DeveloperModel>  viewDevelopers() {
	
return developerRepository.findAll();
	}

}
