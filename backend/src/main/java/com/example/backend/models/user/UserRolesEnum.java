package com.example.backend.models.user;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static com.example.backend.models.user.UserPermission.ADMIN_CRUD;
import static com.example.backend.models.user.UserPermission.CLERK_CRUD;
import static com.example.backend.models.user.UserPermission.CLERK_READ;

@RequiredArgsConstructor
public enum UserRolesEnum {
    ADMIN(Set.of(ADMIN_CRUD, CLERK_CRUD)),
    CLERK(Set.of(CLERK_CRUD, CLERK_READ));

    @Getter
    private final Set<UserPermission> userPermissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getUserPermissions()
                .stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getUserPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;

    }
}
