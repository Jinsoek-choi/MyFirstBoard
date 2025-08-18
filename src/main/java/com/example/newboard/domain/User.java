package com.example.newboard.domain;


import com.example.newboard.repository.UserRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Entity @Table(name="users", uniqueConstraints=@UniqueConstraint(columnNames="email"))
    @Getter @NoArgsConstructor @AllArgsConstructor @Builder
    public class User {
        @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;

        @Column(nullable=false, length=100)
        private String email;

        @Column(nullable=false, length=60)
        private String password; // BCrypt 해시

        @Column(nullable=false, length=50)
        private String name;

        @Column(nullable=false, length=20)
        private String role; // "USER"

    private User(String email, String name) {
        this.email = email;
        this.name = name;
        this.password = ""; // OAuth 가입자는 비번 공란 가능
        this.role = "USER";
    }

    public static User create(String email, String name) {
        return new User(email, name);
    }


    }

