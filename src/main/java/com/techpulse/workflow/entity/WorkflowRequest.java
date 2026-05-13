package com.techpulse.workflow.entity;

import com.techpulse.workflow.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String createdBy;

    private LocalDateTime createdAt;

    private Integer currentStep;
}