package com.alura.challengeforoalura.domain.users;

import jakarta.validation.constraints.NotNull;

public record UserUpdateData(@NotNull Long id, String name, String password) {
}
