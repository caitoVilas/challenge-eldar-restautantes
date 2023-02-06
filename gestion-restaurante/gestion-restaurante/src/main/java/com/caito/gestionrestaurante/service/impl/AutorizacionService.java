package com.caito.gestionrestaurante.service.impl;

import com.caito.gestionrestaurante.enums.RoleName;
import com.caito.gestionrestaurante.security.jwt.JwtFilter;
import com.caito.gestionrestaurante.security.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Service
public class AutorizacionService {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtFilter jwtFilter;

    @Value("${jwt.secret}")
    private String secret;

    private static final Logger logger = LoggerFactory.getLogger(AutorizacionService.class);

    public  boolean authorize(RoleName roleName){

        logger.info("obteniendo autorizacion...");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String token = jwtFilter.getToken(request);

        Claims claims =
        Jwts.parserBuilder().setSigningKey(jwtProvider.geyKey(secret)).build().parseClaimsJws(token)
                .getBody();
       Object rol =claims.get("roles");
       Object r = ((ArrayList) rol).get(0);
       if (r.toString().equals(roleName.toString())){
           logger.info("autoizado rol : " + r.toString());
           return true;
       }
        logger.error("no autorizado rol: " + r.toString());
        return false;
    }
}
