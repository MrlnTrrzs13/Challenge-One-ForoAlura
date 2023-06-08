package com.alura.challengeforoalura.domain.course;

import jakarta.validation.constraints.NotBlank;

public record CourseData(@NotBlank String name_course, @NotBlank String category) {
}
