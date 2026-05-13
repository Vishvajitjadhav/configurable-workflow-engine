package com.techpulse.workflow.repository;

import com.techpulse.workflow.entity.WorkflowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRequestRepository extends JpaRepository<WorkflowRequest, Long> {
}
