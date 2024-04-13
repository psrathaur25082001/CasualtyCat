package com.CasualtyCat.service.impl;

import com.CasualtyCat.entity.Insurance;
import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.payload.InsuranceDto;
import com.CasualtyCat.repository.InsuranceRepository;
import com.CasualtyCat.service.InsuranceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private ModelMapper modelMapper;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, ModelMapper modelMapper) {
        this.insuranceRepository = insuranceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InsuranceDto createInsurance(InsuranceDto dto) {
        if(insuranceRepository.existsByContactNumber(dto.getContactNumber())){
            throw new ResourceNotFoundException("contactNumber is already registered use different different contact number");
        }else{// creating Insurance using model mapper and before creating checking the  mobile number
            dto.setId(UUID.randomUUID().toString());
           Insurance map = modelMapper.map(dto, Insurance.class);
            Insurance save = insuranceRepository.save(map);
            return modelMapper.map(save, InsuranceDto.class);
        }
    }

    @Override
    public InsuranceDto getInsuranceById(String id) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("client not Found with this id")
        );
        return modelMapper.map(insurance,InsuranceDto.class);
    }

    @Override
    public List<InsuranceDto> getAllInsurance() {
        List<Insurance> all=insuranceRepository.findAll();
        List<InsuranceDto> collect = all.stream().map(t -> modelMapper.map(t, InsuranceDto.class)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String deleteInsuranceById(String id) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Insurance not found with this ID: " + id)
        );
        insuranceRepository.deleteById(id);
        return "Insurance with ID " + id + " has been successfully deleted.";
    }

    @Override
    public InsuranceDto updateInsuranceById(String id, InsuranceDto dto) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("client not Found with this id")
        );
        insurance.setCompanyName(dto.getCompanyName());
        insurance.setAddress(dto.getAddress());
        insurance.setCompanyLogo(dto.getCompanyLogo());
        insurance.setContactNumber(dto.getContactNumber());
        insurance.setExpiryDate(dto.getExpiryDate());
        insurance.setDurationOfSubscription(dto.getDurationOfSubscription());
        insurance.setNumberOfAgent(dto.getNumberOfAgent());
        insurance.setPaymentModeId(dto.getPaymentModeId());
        insurance.setNoOfTokens(dto.getNoOfTokens());
        Insurance save = insuranceRepository.save(insurance);
        InsuranceDto map = modelMapper.map(insurance, InsuranceDto.class);

        return map;
    }
}
