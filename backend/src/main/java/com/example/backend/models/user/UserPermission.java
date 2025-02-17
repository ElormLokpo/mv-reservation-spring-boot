package com.example.backend.models.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserPermission {
    ADMIN_CRUD("admin:crud"),
    CLERK_READ("clerk:read"),
    CLERK_CRUD("clerk:crud");

    @Getter
    private final String userPermission;
}
