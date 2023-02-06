package com.caito.gestionrestaurante.service.impl;

import com.caito.gestionrestaurante.dto.CategoriaDTO;
import com.caito.gestionrestaurante.dto.CategoriaNuevaDTO;
import com.caito.gestionrestaurante.dto.PageableResponseDTO;
import com.caito.gestionrestaurante.entity.Categoria;
import com.caito.gestionrestaurante.enums.RoleName;
import com.caito.gestionrestaurante.exception.BadRequestException;
import com.caito.gestionrestaurante.exception.NotAuthorizeExcepcion;
import com.caito.gestionrestaurante.exception.NotFoundException;
import com.caito.gestionrestaurante.mapper.CategoriaMapper;
import com.caito.gestionrestaurante.mapper.CategoriaNuevaMapper;
import com.caito.gestionrestaurante.repository.CategoriaRepository;
import com.caito.gestionrestaurante.service.contrac.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriaServiceImpl implements CategoriaService {

    private static final Logger logger = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private CategoriaNuevaMapper categoriaNuevaMapper;
    @Autowired
    AutorizacionService autorizacionService;

    @Override
    public CategoriaDTO createCategoria(CategoriaNuevaDTO dto) {
        logger.info("inicio servicio crear categoria");
        if(!autorizacionService.authorize(RoleName.ROLE_USER))
            throw new NotAuthorizeExcepcion("no autorizado");
        if (categoriaRepository.existsByNombre(dto.getNombre())){
            logger.error("el nombre de la categoria ya esta registrado");
            throw new BadRequestException("el nombre de la categoria ya esta registrado");
        }
        logger.info("guardando categoria");
        return categoriaMapper.categoriaToCategoriaDTO(categoriaRepository.save(
                categoriaNuevaMapper.categoriaNuevaDTOToCategoria(dto)
        ));
    }

    @Override
    public CategoriaDTO getCategoriaById(Long id) {
        logger.info("inicio servicio buscar categoria por id");
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(()->{
            logger.error("la categoria no existe");
            throw new NotFoundException("la categoria no existe");
        });
        return categoriaMapper.categoriaToCategoriaDTO(categoria);
    }

    @Override
    public CategoriaDTO getCategoriaByNombre(String nombre) {
        logger.info("inicio servicio busqueda de categoria por nombre");
        Categoria categoria = categoriaRepository.findByNombreContaining(nombre);
        if (categoria == null){
            logger.error("no existe una categoria con ese nombre");
            throw new NotFoundException("no existe una categoria con ese nombre");
        }
        return categoriaMapper.categoriaToCategoriaDTO(categoria);
    }

    @Override
    public PageableResponseDTO<CategoriaDTO> getAll(int page, int size) {
        logger.info("inicio servico ver categorias paginado");
        if (page <= 0){
            logger.error("la pagina debe ser mayor que 0");
            throw new BadRequestException("la pagina debe ser mayor que 0");
        }
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Categoria> categorias = categoriaRepository.findAll(pageable);
        List<CategoriaDTO> cat = categoriaMapper.categoriaListToCategoriaListDTO(categorias.getContent());
        PageableResponseDTO<CategoriaDTO> response = new PageableResponseDTO<>();
        response.setResults(categorias.getTotalElements());
        response.setPage(categorias.getNumber() + 1);
        response.setTotalPages(categorias.getTotalPages());
        response.setContent(cat);
        return response;
    }

    @Override
    public void deletecategoria(Long id) {
        logger.info("inicio servicio eliminar categoria por id");
        if(!autorizacionService.authorize(RoleName.ROLE_USER))
            throw new NotAuthorizeExcepcion("no autorizado");
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(()->{
            logger.error("no existe la categoria");
            throw new NotFoundException("no existe la categoria");
        });
        categoriaRepository.deleteById(id);
    }

    @Override
    public List<CategoriaDTO> verTodos() {
        logger.info("inicio servicio ver todas las categorias");
        List<Categoria> categorias = categoriaRepository.findAll();
        return categoriaMapper.categoriaListToCategoriaListDTO(categorias);
    }
}
