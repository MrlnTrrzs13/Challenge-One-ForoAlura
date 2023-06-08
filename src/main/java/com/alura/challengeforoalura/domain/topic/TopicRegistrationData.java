package com.alura.challengeforoalura.domain.topic;

import com.alura.challengeforoalura.domain.course.CourseData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRegistrationData(
        @NotBlank String title, @NotBlank String message,
        @NotBlank String author,@NotNull @Valid CourseData course) {
}
