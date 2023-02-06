package com.caito.gestionrestaurante.service.impl;

import com.caito.gestionrestaurante.dto.PageableResponseDTO;
import com.caito.gestionrestaurante.dto.RestauranteDTO;
import com.caito.gestionrestaurante.dto.RestauranteNuevoDTO;
import com.caito.gestionrestaurante.entity.Restaurante;
import com.caito.gestionrestaurante.enums.RoleName;
import com.caito.gestionrestaurante.exception.BadRequestException;
import com.caito.gestionrestaurante.exception.NotAuthorizeExcepcion;
import com.caito.gestionrestaurante.exception.NotFoundException;
import com.caito.gestionrestaurante.mapper.RestauranteMapper;
import com.caito.gestionrestaurante.mapper.RestauranteNuevoMapper;
import com.caito.gestionrestaurante.repository.RestauranteRepository;
import com.caito.gestionrestaurante.service.contrac.RestauranteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteServiceImpl  implements RestauranteService {

    private static final Logger logger = LoggerFactory.getLogger(RestauranteServiceImpl.class);

    @Autowired
    private RestauranteRepository repository;
    @Autowired
    private RestauranteMapper restauranteMapper;
    @Autowired
    private RestauranteNuevoMapper restauranteNuevoMapper;
    @Autowired
    private AutorizacionService autorizacionService;


    @Override
    public RestauranteDTO createRestaurante(RestauranteNuevoDTO dto) {
        logger.info("inicio servicio creacion restaurante");
        if(!autorizacionService.authorize(RoleName.ROLE_USER))
            throw new NotAuthorizeExcepcion("no autorizado");
        if (repository.existsByNombre(dto.getNombre())){
            logger.error("el nombre de restaurente ya esta registrado");
            throw new BadRequestException("el nombre de restaurente ya esta registrado");
        }
        return restauranteMapper.restauranteToRestauranteDTO(repository.save(
                restauranteNuevoMapper.restauranteNuevoDTOToRestaurante(dto)
        ));
    }

    @Override
    public List<RestauranteDTO> getAll() {
        logger.info("inicio servicio ver todos los restaurantes");
        return restauranteMapper.restauranteListToRestauranteDTOList(repository.findAll());
    }

    @Override
    public RestauranteDTO getById(Long id) {
        logger.info("inicio servicio busqueda de restaurante por id");
        Restaurante restaurante = repository.findById(id).orElseThrow(()->{
            logger.error("el restaurante no existe");
            throw new NotFoundException("el restaurante no existe");
        });
        return restauranteMapper.restauranteToRestauranteDTO(restaurante);
    }

    @Override
    public RestauranteDTO getByNombre(String nombre) {
        logger.info("inicio de servicio busqueda de restaurante por nombre");
        Restaurante restaurante = repository.findByNombreContaining(nombre);
        if (restaurante == null){
            logger.error("no existe un restaurante con ese nombre");
            throw new NotFoundException("no existe un restaurante con ese nombre");
        }
        return restauranteMapper.restauranteToRestauranteDTO(restaurante);
    }

    @Override
    public PageableResponseDTO verTodosPaginado(int page, int size) {
        logger.info("inicio servicio ver todos los restaurantes paginados");
        if (page <= 0){
            logger.error("la pagina debe ser mayor que 0");
            throw new BadRequestException("la pagina debe ser mayor que 0");
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Restaurante> restaurantes = repository.findAll(pageable);
        List<RestauranteDTO> r = restauranteMapper.restauranteListToRestauranteDTOList(restaurantes.getContent());
        PageableResponseDTO<RestauranteDTO> response = new PageableResponseDTO<>();
        response.setPage(restaurantes.getNumber() +1);
        response.setResults(restaurantes.getTotalElements());
        response.setTotalPages(restaurantes.getTotalPages());
        response.setContent(r);
        return response;
    }

    @Override
    public void deleteResturante(Long id) {
        logger.info("inicio servicio eliminar restaurante por id");
        if(!autorizacionService.authorize(RoleName.ROLE_USER))
            throw new NotAuthorizeExcepcion("no autorizado");
        Restaurante restaurante = repository.findById(id).orElseThrow(()->{
            logger.error("el restaurante no existe");
            throw new NotFoundException("el restaurante no existe");
        });
        repository.deleteById(id);
    }
}
