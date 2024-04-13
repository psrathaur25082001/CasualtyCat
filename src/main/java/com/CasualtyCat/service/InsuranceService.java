package com.CasualtyCat.service;

import com.CasualtyCat.payload.InsuranceDto;

import java.util.List;

public interface InsuranceService {
    InsuranceDto createInsurance(InsuranceDto dto);
    InsuranceDto getInsuranceById(String id);
    List<InsuranceDto> getAllInsurance();
    String deleteInsuranceById(String id);
    InsuranceDto updateInsuranceById(String id,InsuranceDto dto);
}
