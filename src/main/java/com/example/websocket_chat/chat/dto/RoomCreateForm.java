package com.example.websocket_chat.chat.dto;

import jakarta.validation.constraints.NotBlank;

public record RoomCreateForm(
        @NotBlank String name) {
}
