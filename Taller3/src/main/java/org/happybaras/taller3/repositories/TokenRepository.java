package org.happybaras.taller3.repositories;

import org.happybaras.taller3.domain.entities.Token;
import org.happybaras.taller3.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    List<Token> findByUserAndActive(User user, Boolean active);
}
