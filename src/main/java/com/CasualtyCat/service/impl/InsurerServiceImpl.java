package com.CasualtyCat.service.impl;

import com.CasualtyCat.entity.Insurer;
import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.payload.InsurerDto;
import com.CasualtyCat.repository.InsurerRepository;
import com.CasualtyCat.service.InsurerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsurerServiceImpl implements InsurerService {

    @Autowired
    private InsurerRepository insurerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public InsurerServiceImpl(InsurerRepository insurerRepository, ModelMapper modelMapper) {
        this.insurerRepository = insurerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InsurerDto createInsurer(InsurerDto dto) {
        if(insurerRepository.existsByContactNumber(dto.getContactNumber())){
            throw new ResourceNotFoundException("contactNumber is already registered use different different contact number");
        }else{// creating Insurance using model mapper and before creating checking the  mobile number
            dto.setId(UUID.randomUUID().toString());
            Insurer map = modelMapper.map(dto, Insurer.class);
            Insurer save = insurerRepository.save(map);
            return modelMapper.map(save, InsurerDto.class);
        }
    }

    @Override
    public InsurerDto getInsurerById(String id) {
        Insurer insurer = insurerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("client not Found with this id")
        );
        return modelMapper.map(insurer,InsurerDto.class);
    }

    @Override
    public List<InsurerDto> getAllInsurer() {
        List<Insurer> all=insurerRepository.findAll();
        List<InsurerDto> collect = all.stream().map(t -> modelMapper.map(t, InsurerDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String deleteInsurerById(String id) {
        Insurer insurer = insurerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Insurance not found with this ID: " + id)
        );
        insurerRepository.deleteById(id);
        return "Insurance with ID " + id + " has been successfully deleted.";
    }

    @Override
    public InsurerDto updateInsurerById(String id, InsurerDto dto) {
        Insurer insurer = insurerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("client not Found with this id")
        );
        insurer.setCompanyName(dto.getCompanyName());
        insurer.setAddress(dto.getAddress());
        insurer.setCompanyLogo(dto.getCompanyLogo());
        insurer.setContactNumber(dto.getContactNumber());
        insurer.setLinkExpiryDate(dto.getLinkExpiryDate());
        insurer.setDurationOfSubscription(dto.getDurationOfSubscription());
        insurer.setNoOfAgents(dto.getNoOfAgents());
        insurer.setPaymentMode(dto.getPaymentMode());
        insurer.setNoOfTokens(dto.getNoOfTokens());
        Insurer save = insurerRepository.save(insurer);
        InsurerDto map = modelMapper.map(insurer, InsurerDto.class);

        return map;
    }
}
