package com.techpulse.workflow.service;

import com.techpulse.workflow.dto.CreateRequestDto;
import com.techpulse.workflow.entity.ApprovalHistory;
import com.techpulse.workflow.entity.ApprovalStep;
import com.techpulse.workflow.entity.User;
import com.techpulse.workflow.entity.WorkflowRequest;
import com.techpulse.workflow.enums.RequestStatus;
import com.techpulse.workflow.repository.ApprovalHistoryRepository;
import com.techpulse.workflow.repository.ApprovalStepRepository;
import com.techpulse.workflow.repository.UserRepository;
import com.techpulse.workflow.repository.WorkflowRequestRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final WorkflowRequestRepository workflowRequestRepository;
    private final ApprovalStepRepository approvalStepRepository;
    private final ApprovalHistoryRepository approvalHistoryRepository;
    private final UserRepository userRepository;

    public WorkflowRequest createRequest(
            CreateRequestDto dto,
            String username
    ) {

        ApprovalStep firstStep = approvalStepRepository
                .findByRequestTypeAndStepOrder(
                        dto.getType(),
                        1
                )
                .orElseThrow(() ->
                        new RuntimeException("Workflow not configured"));

        WorkflowRequest request = WorkflowRequest.builder()
                .type(dto.getType())
                .status(RequestStatus.PENDING)
                .createdBy(username)
                .createdAt(LocalDateTime.now())
                .currentStep(firstStep.getStepOrder())
                .build();

        return workflowRequestRepository.save(request);
    }

    @Transactional
    public WorkflowRequest approveRequest(
            Long requestId,
            String username
    ) {

        WorkflowRequest request = workflowRequestRepository
                .findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException("Request not found"));

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if(request.getCreatedBy().equals(username)) {
            throw new RuntimeException(
                    "Requester cannot approve own request");
        }

        ApprovalStep currentStep = approvalStepRepository
                .findByRequestTypeAndStepOrder(
                        request.getType(),
                        request.getCurrentStep()
                )
                .orElseThrow(() ->
                        new RuntimeException("Step not found"));

        if(user.getRole() != currentStep.getRole()) {
            throw new RuntimeException(
                    "Unauthorized for current step");
        }

        ApprovalHistory history = ApprovalHistory.builder()
                .requestId(requestId)
                .action("APPROVED")
                .actionBy(username)
                .actionAt(LocalDateTime.now())
                .build();

        approvalHistoryRepository.save(history);

        int nextStepOrder = request.getCurrentStep() + 1;

        ApprovalStep nextStep = approvalStepRepository
                .findByRequestTypeAndStepOrder(
                        request.getType(),
                        nextStepOrder
                )
                .orElse(null);

        if(nextStep == null) {

            request.setStatus(RequestStatus.APPROVED);

        } else {

            request.setCurrentStep(nextStepOrder);
        }

        return workflowRequestRepository.save(request);
    }

    @Transactional
    public WorkflowRequest rejectRequest(
            Long requestId,
            String username
    ) {

        WorkflowRequest request = workflowRequestRepository
                .findById(requestId)
                .orElseThrow(() ->
                        new RuntimeException("Request not found"));

        request.setStatus(RequestStatus.REJECTED);

        ApprovalHistory history = ApprovalHistory.builder()
                .requestId(requestId)
                .action("REJECTED")
                .actionBy(username)
                .actionAt(LocalDateTime.now())
                .build();

        approvalHistoryRepository.save(history);

        return workflowRequestRepository.save(request);
    }

    public List<ApprovalHistory> getHistory(Long requestId) {

        return approvalHistoryRepository
                .findByRequestId(requestId);
    }

    public WorkflowRequest getRequest(Long id) {

        return workflowRequestRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Request not found"));
    }
}