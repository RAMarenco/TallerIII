package org.happybaras.taller3.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTTools {
    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.exptime}")
    private Integer exp;
}
