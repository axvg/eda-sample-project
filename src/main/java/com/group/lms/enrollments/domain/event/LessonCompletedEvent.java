package com.group.lms.enrollments.domain.event;

import com.group.lms.shared.domain.event.DomainEvent;

import lombok.Getter;

@Getter
public class LessonCompletedEvent extends DomainEvent{
    private final String enrollmentId;
    private final String lessonId;
    private final int newProgressPercentage;

    public LessonCompletedEvent(String enrollmentId, String lessonId, int newProgressPercentage) {
        super();
        this.enrollmentId = enrollmentId;
        this.lessonId = lessonId;
        this.newProgressPercentage = newProgressPercentage;
    }
}
