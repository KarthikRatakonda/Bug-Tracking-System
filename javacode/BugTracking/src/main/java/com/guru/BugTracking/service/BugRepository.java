package com.guru.BugTracking.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.BugTracking.model.BugModel;

public interface BugRepository extends JpaRepository<BugModel, Integer> {

}
