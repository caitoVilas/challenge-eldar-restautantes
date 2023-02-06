package com.caito.gestionrestaurante.service.impl;

import com.caito.gestionrestaurante.dto.*;
import com.caito.gestionrestaurante.entity.Plato;
import com.caito.gestionrestaurante.entity.Restaurante;
import com.caito.gestionrestaurante.enums.RoleName;
import com.caito.gestionrestaurante.exception.BadRequestException;
import com.caito.gestionrestaurante.exception.NotAuthorizeExcepcion;
import com.caito.gestionrestaurante.exception.NotFoundException;
import com.caito.gestionrestaurante.mapper.PlatoMapper;
import com.caito.gestionrestaurante.mapper.PlatoSoloMapper;
import com.caito.gestionrestaurante.mapper.RestauranteMapper;
import com.caito.gestionrestaurante.repository.PlatoRepository;
import com.caito.gestionrestaurante.repository.RestauranteRepository;
import com.caito.gestionrestaurante.service.contrac.PlatoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoServiceImpl implements PlatoService {

    private static final Logger logger = LoggerFactory.getLogger(PlatoServiceImpl.class);

    @Autowired
    private PlatoRepository platoRepository;
    @Autowired
    private PlatoMapper platoMapper;
    @Autowired
    private PlatoSoloMapper platoSoloMapper;
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private RestauranteMapper restauranteMapper;
    @Autowired
    private AutorizacionService autorizacionService;

    @Override
    public PlatoDTO createPlato(PlatoNuevoDTO dto) {
        logger.info("inicio servicio alta de platos");
        if(!autorizacionService.authorize(RoleName.ROLE_USER))
            throw new NotAuthorizeExcepcion("no autorizado");
        Restaurante restaurante = restauranteRepository.findById(dto.getRestauranteId()).orElseThrow(()->{
            logger.error("el restaurante no existe");
            throw new NotFoundException("el restaurante no existe");
        });
        Plato plato = new Plato();
        plato.setNombre(dto.getNombre());
        plato.setDescripcion(dto.getDescripcion());
        plato.setPrecio(dto.getPrecio());
        plato.setRestaurante(restaurante);
        return platoMapper.platoToPlatoDTO(platoRepository.save(plato));
    }

    @Override
    public List<PlatoDTO> verTodos() {
        logger.info("inicio servicio ver todos lo platos");
        return platoMapper.platoListToPlatoDTOList(platoRepository.findAll());
    }

    @Override
    public PageableResponseDTO vertodosPaginado(int page, int size) {
        logger.info("ver todos los platos paginados");
        if (page <= 0){
            logger.error("la pagina debe ser mayor a 0");
            throw new BadRequestException("la pagina debe ser mayor a 0");
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Plato> platos = platoRepository.findAll(pageable);
        List<PlatoDTO> p = platoMapper.platoListToPlatoDTOList(platos.getContent());
        PageableResponseDTO<PlatoDTO> response = new PageableResponseDTO<>();
        response.setPage(platos.getNumber() + 1);
        response.setResults(platos.getTotalElements());
        response.setTotalPages(platos.getTotalPages());
        response.setContent(p);
        return response;
    }

    @Override
    public PlatoDTO getById(Long id) {
        logger.info("inicio servicio buscar plato por id");
        Plato plato = platoRepository.findById(id).orElseThrow(()->{
            logger.error("el plato no existe");
            throw new NotFoundException("el plato no existe");
        });
        return platoMapper.platoToPlatoDTO(plato);
    }

    @Override
    public List<PlatoDTO> getByNombre(String nombre) {
        logger.info("inicio servicio buscar plato por nombre");
        List<Plato> platos = platoRepository.findByNombreContaining(nombre);
        if (platos.isEmpty()){
            logger.error("no existen platos con ese nombre");
            throw new NotFoundException("no existen platos con ese nombre");
        }
        return platoMapper.platoListToPlatoDTOList(platos);
    }

    @Override
    public void deletePlato(Long id) {
        logger.info("inicio servicio eliminar plato por id");
        if(!autorizacionService.authorize(RoleName.ROLE_USER))
            throw new NotAuthorizeExcepcion("no autorizado");
        Plato plato = platoRepository.findById(id).orElseThrow(()->{
            logger.error("no existe el plato");
            throw new NotFoundException("no existe el plato");
        });
    }

    @Override
    public PlatoRestauranteDTO buscarporRestaurante(Long idRestaurante, int page, int size) {
        logger.info("inicio servicio buscar platos por restaurante");
        if (page <= 0){
            logger.error("la pagina debe ser mayor que 0");
            throw new BadRequestException("la pagina debe ser mayor que 0");
        }
        Restaurante restaurante = restauranteRepository.findById(idRestaurante).orElseThrow(()->{
            logger.error("no existe el restaurante");
            throw new NotFoundException("no existe el restaurante");
        });
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Plato> p = platoRepository.findByRestaurante(restaurante, pageable);
        List<PlatoSoloDTO> platos = platoSoloMapper.platoListToPlatoSoloDTOList(p.getContent());
        PlatoRestauranteDTO response = new PlatoRestauranteDTO();
        response.setRestaurante(restauranteMapper.restauranteToRestauranteDTO(restaurante));
        response.setPlatos(platos);
        response.setPage(p.getNumber() + 1);
        response.setResults(p.getTotalElements());
        response.setTotalPages(p.getTotalPages());
        return response;
    }
}
