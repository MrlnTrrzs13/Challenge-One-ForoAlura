package com.alura.challengeforoalura.domain.users;

import com.alura.challengeforoalura.domain.topic.Topic;

public record DataUserList(Long id, String name, String password, String email) {
    public DataUserList(User user) {
        this(user.getId(), user.getName(), user.getPassword(), user.getEmail());
    }
}
