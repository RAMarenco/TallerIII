package org.happybaras.taller3.services;

import org.happybaras.taller3.domain.dtos.UserLoginDTO;
import org.happybaras.taller3.domain.entities.Token;
import org.happybaras.taller3.domain.entities.User;

public interface UserService {
    // Token management
    Token registerToken(User user) throws Exception;

    Boolean isTokenValid(User user, String token);

    void cleanTokens(User user) throws Exception;

    User findUserAuthenticated() throws Exception;

    User findOneByIdentifier(String identifier);
}
