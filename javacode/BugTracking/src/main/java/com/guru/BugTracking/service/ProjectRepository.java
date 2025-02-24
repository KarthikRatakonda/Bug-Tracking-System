package com.guru.BugTracking.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.BugTracking.model.ProjectModel;

public interface ProjectRepository extends JpaRepository<ProjectModel, Integer> {

}
