package com.guru.BugTracking.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guru.BugTracking.model.ComplaintModel;

public interface ComplaintRepository extends JpaRepository<ComplaintModel, Integer> {

}
