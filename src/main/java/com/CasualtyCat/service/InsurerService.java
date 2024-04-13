package com.CasualtyCat.service;

import com.CasualtyCat.payload.InsurerDto;

import java.util.List;

public interface InsurerService {

    InsurerDto createInsurer(InsurerDto dto);
    InsurerDto getInsurerById(String id);
    List<InsurerDto> getAllInsurer();
    String deleteInsurerById(String id);
    InsurerDto updateInsurerById(String id,InsurerDto dto);
}
