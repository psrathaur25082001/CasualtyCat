package com.CasualtyCat.service;

import com.CasualtyCat.entity.Type;
import com.CasualtyCat.payload.InsurerDto;

import java.util.List;

public interface TypeService {
    Type createType(Type type);
    Type getTypeById(String id);
    List<Type> getTypeList();
    String deleteTypeById(String id);
    Type updateTypeById(String id,Type type);
}
