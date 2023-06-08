package com.alura.challengeforoalura.controller;

import com.alura.challengeforoalura.domain.course.CourseData;
import com.alura.challengeforoalura.domain.topic.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")

public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<TopicResponseData> registerTopics(@RequestBody @Valid TopicRegistrationData topicRegistrationData,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        Topic topic = topicRepository.save(new Topic(topicRegistrationData));
        TopicResponseData topicResponseData = new TopicResponseData(topic.getId(), topic.getTitle(),
                topic.getMessage(), topic.getCreation_date().toString(),
                topic.getStatus().toString(), topic.getAuthor(), new CourseData(
                topic.getCourse().getName_course(), topic.getCourse().getCategory()));
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topicResponseData);
    }

    @GetMapping
    public ResponseEntity<Page<DataTopicList>> ListOfTopic(@PageableDefault(size = 3) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findAll(pageable).map(DataTopicList::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTopic(@RequestBody @Valid TopicUpdateData topicUpdateData) {
        Topic topic = topicRepository.getReferenceById(topicUpdateData.id());
        topic.updateData(topicUpdateData);
        return ResponseEntity.ok(new TopicResponseData(topic.getId(), topic.getTitle(),
                topic.getMessage(), topic.getCreation_date().toString(),
                topic.getStatus().toString(), topic.getAuthor(), new CourseData(
                        topic.getCourse().getName_course(), topic.getCourse().getCategory())));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topicRepository.delete(topic);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseData> returnTopicDate(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        var topicData = new TopicResponseData(topic.getId(), topic.getTitle(),
                topic.getMessage(), topic.getCreation_date().toString(),
                topic.getStatus().toString(), topic.getAuthor(), new CourseData(
                topic.getCourse().getName_course(), topic.getCourse().getCategory()));
        return ResponseEntity.ok(topicData);
    }
}
