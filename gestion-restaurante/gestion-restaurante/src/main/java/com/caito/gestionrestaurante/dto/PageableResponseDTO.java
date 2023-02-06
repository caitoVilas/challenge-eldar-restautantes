package com.caito.gestionrestaurante.dto;

import com.caito.gestionrestaurante.mapper.PageableResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageableResponseDTO<I> {
    private List<I> content;
    private int page;
    private Long results;
    private int totalPages;
}
