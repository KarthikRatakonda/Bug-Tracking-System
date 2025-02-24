package com.guru.BugTracking.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.BugTracking.model.DeveloperModel;

public interface DeveloperRepository extends JpaRepository<DeveloperModel, Integer> {

	DeveloperModel findByempid(String developerid);

	List<DeveloperModel> findByrole(String developer);

	DeveloperModel findByempidAndRole(String empid, String role);

}
