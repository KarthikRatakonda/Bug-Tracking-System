package com.guru.BugTracking.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.BugTracking.model.DeveloperUserModel;

public interface DeveloperUserRepository extends JpaRepository<DeveloperUserModel, Integer> {

}
