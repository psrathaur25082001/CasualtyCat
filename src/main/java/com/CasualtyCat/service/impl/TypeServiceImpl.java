package com.CasualtyCat.service.impl;

import com.CasualtyCat.entity.Industry;
import com.CasualtyCat.entity.Type;
import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.repository.TypeRepository;
import com.CasualtyCat.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class TypeServiceImpl implements TypeService {
    private TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type createType(Type type) {
        type.setId(UUID.randomUUID().toString());
        return typeRepository.save(type);
    }

    @Override
    public Type getTypeById(String id) {
        Type industry = typeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("type not find with this Id")
        );
        return industry;
    }


    @Override
    public List<Type> getTypeList() {
        return typeRepository.findAll();
    }

    @Override
    public String deleteTypeById(String id) {
        typeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("type not find with this Id")
        );
        typeRepository.deleteById(id);
        return "deleted successfully";
    }
    @Override
    public Type updateTypeById(String id, Type type) {
        Type industry = typeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("type not find with this Id")
        );
           industry .setType(type.getType());
           return typeRepository.save(industry);
    }
}
