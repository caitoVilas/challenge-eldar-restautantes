package com.caito.gestionrestaurante.security;

import com.caito.gestionrestaurante.entity.Usuario;
import com.caito.gestionrestaurante.exception.NotFoundException;
import com.caito.gestionrestaurante.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeatailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDeatailsServiceImpl.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (!usuario.isPresent()){
            logger.error("el usuario no existe");
            throw new NotFoundException("el usuario no existe");
        }
        return UsuarioPrincipal.build(usuario.get());
    }
}
