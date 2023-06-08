package com.alura.challengeforoalura.domain.topic;

import com.alura.challengeforoalura.domain.course.CourseData;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateData(
        @NotNull Long id, String title, String message,
        String author, CourseData course) {
}
