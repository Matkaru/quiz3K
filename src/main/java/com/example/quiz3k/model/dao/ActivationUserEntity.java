package com.example.quiz3k.model.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
@Entity
@Data
@Table(name = "users_activation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivationUserEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activation_token")
    private String activationToken;

    @OneToOne
    private UserEntity user;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
