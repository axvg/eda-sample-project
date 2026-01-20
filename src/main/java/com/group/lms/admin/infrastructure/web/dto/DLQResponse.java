package com.group.lms.admin.infrastructure.web.dto;

import java.util.List;

import com.group.lms.shared.infrastructure.dlq.FailedEvent;

import lombok.Data;

@Data
public class DLQResponse {
    private List<FailedEvent> events;
}
