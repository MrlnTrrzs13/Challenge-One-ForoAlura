package com.alura.challengeforoalura.domain.topic;

public record DataTopicList(Long id, String title, String message,
                            String creation_date, String status,
                            String author, String name_course) {
    public DataTopicList(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getCreation_date().toString(),
                topic.getStatus().toString(), topic.getAuthor(), topic.getCourse().getName_course());
    }
}
