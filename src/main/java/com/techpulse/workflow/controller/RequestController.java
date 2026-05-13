package com.techpulse.workflow.controller;

import com.techpulse.workflow.dto.CreateRequestDto;
import com.techpulse.workflow.entity.ApprovalHistory;
import com.techpulse.workflow.entity.WorkflowRequest;
import com.techpulse.workflow.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public WorkflowRequest createRequest(
            @RequestBody CreateRequestDto dto,
            @RequestParam String username
    ) {

        return requestService.createRequest(dto, username);
    }

    @PostMapping("/{id}/approve")
    public WorkflowRequest approveRequest(
            @PathVariable Long id,
            @RequestParam String username
    ) {

        return requestService.approveRequest(id, username);
    }

    @PostMapping("/{id}/reject")
    public WorkflowRequest rejectRequest(
            @PathVariable Long id,
            @RequestParam String username
    ) {

        return requestService.rejectRequest(id, username);
    }

    @GetMapping("/history/{id}")
    public List<ApprovalHistory> getHistory(
            @PathVariable Long id
    ) {

        return requestService.getHistory(id);
    }

    @GetMapping("/{id}")
    public WorkflowRequest getRequest(
            @PathVariable Long id
    ) {

        return requestService.getRequest(id);
    }
}