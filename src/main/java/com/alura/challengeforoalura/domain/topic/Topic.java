package com.alura.challengeforoalura.domain.topic;


import com.alura.challengeforoalura.domain.course.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table (name = "topicos")
@Entity (name = "Topic")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date creation_date = new Date();
    @Enumerated(EnumType.STRING)
    private Status status = Status.NO_RESPONDIDO;
    private String author;
    private Course course;


    public Topic(TopicRegistrationData topicRegistrationData) {
        this.title = topicRegistrationData.title();
        this.message = topicRegistrationData.message();
        this.author = topicRegistrationData.author();
        this.course = new Course(topicRegistrationData.course());
    }

    public void updateData(TopicUpdateData topicUpdateData) {
        if (topicUpdateData.title() != null) {
            this.title = topicUpdateData.title();
        }
        if (topicUpdateData.message() != null) {
            this.message = topicUpdateData.message();
        }
        if (topicUpdateData.course() != null) {
            this.course = course.updateData(topicUpdateData.course());
        }

    }
}
