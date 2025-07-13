package com.example.springSecurity.dto.userDTO;

import java.util.UUID;

public record UserResponse(UUID id, String userName, String password) {
}
