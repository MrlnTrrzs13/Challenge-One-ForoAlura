package com.alura.challengeforoalura.domain.topic;

import com.alura.challengeforoalura.domain.course.CourseData;

public record TopicResponseData(Long id, String title, String message,
                                String creation_date, String status,
                                String author, CourseData courseData) {
}
