package com.caito.gestionrestaurante.service.impl;

import com.caito.gestionrestaurante.dto.*;
import com.caito.gestionrestaurante.entity.Rol;
import com.caito.gestionrestaurante.entity.Usuario;
import com.caito.gestionrestaurante.enums.RoleName;
import com.caito.gestionrestaurante.exception.BadRequestException;
import com.caito.gestionrestaurante.exception.NotAuthorizeExcepcion;
import com.caito.gestionrestaurante.exception.NotFoundException;
import com.caito.gestionrestaurante.mapper.UsuarioMapper;
import com.caito.gestionrestaurante.mapper.UsuarioNuevoMapper;
import com.caito.gestionrestaurante.repository.UsuarioRepository;
import com.caito.gestionrestaurante.security.jwt.JwtProvider;
import com.caito.gestionrestaurante.service.contrac.RolService;
import com.caito.gestionrestaurante.service.contrac.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private UsuarioNuevoMapper usuarioNuevoMapper;
    @Autowired
    private RolService rolService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private AutorizacionService autorizacionService;

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);


    @Override
    public UsuarioDTO createUsuario(UsuarioNuevoDTO dto) {
        logger.info("inicio servicio crear usuario");
        if (usuarioRepository.existsByUsername(dto.getUsername())){
            logger.error("el nombre de usuario ya exite");
            throw new BadRequestException("el nombre de usuario ya existe");
        }
        if (usuarioRepository.existsByEmail(dto.getEmail())){
            logger.error("el email ya esta registrado");
            throw new BadRequestException("el email ya esta registrado");
        }
        List<Rol> roles = new ArrayList<>();
        for (RoleName rol: dto.getRoles()) {
            Rol r = rolService.getByRolNombre(rol);
            roles.add(r);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRoles(roles);
        return usuarioMapper.usuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO getUsuarioById(Long id) {
        logger.info("inicio servicio buscar usuario por id");
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->{
            logger.error("usuario no encontrado");
            throw new NotFoundException("usuario no encontrado");
        });
        return usuarioMapper.usuarioToUsuarioDTO(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        logger.info("inicio servicio elimanacion de usuario");
        if(!autorizacionService.authorize(RoleName.ROLE_ADMIN))
            throw new NotAuthorizeExcepcion("no autorizado");
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->{
            logger.error("usuario no encontrado");
            throw new NotFoundException("usuario no encontrado");
        });
    }

    @Override
    public JwtDTO login(LogginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new JwtDTO(token);
    }

    @Override
    public PageableResponseDTO verTodos(int page, int size) {
        logger.info("inicio servicio ver usuarios paginado");
        if (page <= 0){
            logger.error("la pagina debe ser mayor que 0");
            throw new BadRequestException("la pagina debe ser mayor que 0");
        }
        Pageable pageable = PageRequest.of(page -1, size);
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        List<UsuarioDTO> us = usuarioMapper.usuarioListToUsuarioDTOList(usuarios.getContent());
        PageableResponseDTO<UsuarioDTO> response = new PageableResponseDTO();
        response.setResults(usuarios.getTotalElements());
        response.setPage(usuarios.getNumber() + 1);
        response.setTotalPages(usuarios.getTotalPages());
        response.setContent(us);
        return response;
    }

    @Override
    public void cambiarRol(Long userId) {
        logger.info("inicio servicio cambio rol");
        if(!autorizacionService.authorize(RoleName.ROLE_ADMIN))
            throw new NotAuthorizeExcepcion("no autorizado");
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(()->{
            logger.error("usuario no encontrado");
            throw new NotFoundException("usuario no encontrado");
        });
        Rol rol = rolService.getByRolNombre(RoleName.ROLE_USER);
        List<Rol> roles = new ArrayList<>();
        roles.add(rol);
        usuario.setRoles(roles);
        usuarioRepository.save(usuario);
    }
}
