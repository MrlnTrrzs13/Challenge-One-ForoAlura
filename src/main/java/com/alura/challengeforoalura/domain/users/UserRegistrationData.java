package com.alura.challengeforoalura.domain.users;

import jakarta.validation.constraints.NotBlank;

public record UserRegistrationData(
        @NotBlank String name, @NotBlank String password, @NotBlank String email) {
}
