package com.techpulse.workflow.entity;

import com.techpulse.workflow.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestType;

    private Integer stepOrder;

    @Enumerated(EnumType.STRING)
    private Role role;
}