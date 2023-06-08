package com.alura.challengeforoalura.domain.course;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private String name_course;
    private String category;

    public Course(CourseData courseData) {
        this.name_course = courseData.name_course();
        this.category = courseData.category();
    }

    public Course updateData(CourseData courseData) {
        this.name_course = courseData.name_course();
        this.category = courseData.category();
        return this;
    }
}
