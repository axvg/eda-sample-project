package com.group.lms.admin.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.lms.admin.infrastructure.web.dto.DLQResponse;
import com.group.lms.shared.infrastructure.dlq.DeadLetterQueue;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/dlq")
@RequiredArgsConstructor
public class DLQController {
    private final DeadLetterQueue dlq;

    @GetMapping
    public ResponseEntity<DLQResponse> getFailedEvents() {
        DLQResponse response = new DLQResponse();
        response.setEvents(dlq.getFailedEvents());
        return ResponseEntity.ok(response);
    }
}
