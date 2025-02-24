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
import com.guru.BugTracking.model.DeveloperUserModel;
import com.guru.BugTracking.model.ProjectModel;
import com.guru.BugTracking.service.BugRepository;
import com.guru.BugTracking.service.ComplaintRepository;
import com.guru.BugTracking.service.DeveloperRepository;
import com.guru.BugTracking.service.DeveloperUserRepository;
import com.guru.BugTracking.service.ProjectRepository;

@RestController
public class DeveloperController {
	
	@Autowired
	DeveloperRepository developerRepository;
	@Autowired
	DeveloperUserRepository developerUserRepository;
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	BugRepository bugRepository;
	
	
	@RequestMapping(value="/getDetails",method=RequestMethod.GET)
	public DeveloperModel getDetails(@RequestParam("developerid") String developerid) {
		
		DeveloperModel dm=developerRepository.findByempid(developerid);
		
if(dm==null) {
	return dm;
}else {
return dm;
}
	
	}
	
	
	@RequestMapping(value="/regDeveloper",method=RequestMethod.POST)
	public DeveloperUserModel regDeveloper(@RequestParam("password") String password,@RequestParam("developerid") String developerid) {
		
		DeveloperModel dm=developerRepository.findByempid(developerid);
		
	DeveloperUserModel dum=new DeveloperUserModel();
	dum.setPassword(password);dum.setDeveloperModel(dm);
	developerUserRepository.save(dum);
		return dum;
	}
	
	
	@RequestMapping(value="/loginDeveloper",method=RequestMethod.POST)
	public DeveloperModel loginDeveloper(@RequestParam("password") String password,@RequestParam("developerid") String developerid) {
		
		DeveloperModel dm=developerRepository.findByempid(developerid);
		if(dm==null) {
			return dm;
		}else {
			
			DeveloperUserModel dum=dm.getDeveloperUserModel();
			if(dum.getPassword().equalsIgnoreCase(password)) {
				return dm;
			}else {
				dm=new DeveloperModel();
			return dm;
			}
		}
		
		
}
	
	
	
	@RequestMapping(value="/developerCompl",method=RequestMethod.POST)
	public ComplaintModel developerCompl(@RequestParam("developerid") String developerid,@RequestParam("comptype") String compltype,
			@RequestParam("compdesc") String compDesc
			) {
		DeveloperModel dm=developerRepository.findByempid(developerid);
		ComplaintModel cm=new ComplaintModel();
		cm.setCompltype(compltype);cm.setCompldesc(compDesc);cm.setStatus("submitted");cm.setDeveloperModel(dm);
		complaintRepository.save(cm);
	
		return cm;
	}
	
	
	
	@RequestMapping(value="/getAssignedProjects",method=RequestMethod.GET)
	public List<ProjectModel> assignProjects(@RequestParam("developerid") int id) {
		
		DeveloperModel dm=developerRepository.findOne(id);
		
		return dm.getProjectModel();
		}
	
	@RequestMapping(value="/addBug",method=RequestMethod.POST)
	public BugModel addBug(@RequestParam("developerid") int did,@RequestParam("project id")int pid,
			@RequestParam("buddesc") String bugdesc,@RequestParam("priority") String bugpriority
			) {
		
		DeveloperModel dm=developerRepository.findOne(pid);
		ProjectModel pm=projectRepository.findOne(pid);
		
		BugModel bugModel=new BugModel();
	bugModel.setBugdesc(bugdesc);bugModel.setBugpriority(bugpriority);bugModel.setProjectModel(pm);bugModel.setDeveloperModel(dm);
		
		return bugRepository.save(bugModel);
		}
	
	
	
	
	@RequestMapping("/profile")
	public DeveloperModel getProfile(@RequestParam("userid") int userid) {
		
		return developerRepository.findOne(userid);
		
	}
	
	@RequestMapping("/oldpassword")
	public String oldpassword(@RequestParam("username") String developerid,@RequestParam("oldpassword") String oldpassword){
		
		
		DeveloperModel dm=developerRepository.findByempid(developerid);
		if(dm==null) {
			return "fail";
		}else {
			
			DeveloperUserModel dum=dm.getDeveloperUserModel();
			if(dum.getPassword().equalsIgnoreCase(oldpassword)) {
				return "success";
			}else {
				dm=new DeveloperModel();
			return "fail";
			}
		}
	}
	
	
	@RequestMapping(value="/changepassword",method=RequestMethod.POST)
	public String changepassword(@RequestParam("userid") int userid,@RequestParam("password") String password){
		
		
	DeveloperModel appOwnerUser=developerRepository.findOne(userid);
	DeveloperUserModel dum=appOwnerUser.getDeveloperUserModel();
	dum.setPassword(password);
	DeveloperUserModel aou=developerUserRepository.save(dum);
	if(aou==null) {
		return "fail";
	}else {
		return "success";
	}
	}
	
	
	
	
	@RequestMapping("/viewadminbugs")
	public List<ProjectModel> AdminBugs(@RequestParam("userid") int userid) {
		
		DeveloperModel dm= developerRepository.findOne(userid);
		return dm.getProjectModel();
		
	}
	
	@RequestMapping("/del")
	public void delete() {
		
		developerUserRepository.deleteAll();

		
	}
	

}
