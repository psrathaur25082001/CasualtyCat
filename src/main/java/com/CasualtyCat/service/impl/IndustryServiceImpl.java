package com.CasualtyCat.service.impl;

import com.CasualtyCat.entity.Industry;
import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.repository.IndustryRepository;
import com.CasualtyCat.service.IndustryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class IndustryServiceImpl implements IndustryService {
    private IndustryRepository industryRepository;

    public IndustryServiceImpl(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    @Override
    public Industry createIndustry(Industry industry) {
        industry.setId(UUID.randomUUID().toString());
        industry.setCreatedDate(LocalDate.now());
        return industryRepository.save(industry);

    }

    @Override
    public Industry getIndustryById(String id) {
        Industry industry = industryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("duration not find with this Id")
        );
        return industry;
    }

    @Override
    public List<Industry> getIndustryList() {
        return industryRepository.findAll();
    }

    @Override
    public String deleteIndustryById(String id) {
        industryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("duration not find with this Id")
        );
        industryRepository.deleteById(id);
        return "deleted Successfully";
    }

    @Override
    public Industry updateIndustry(String id, Industry industry) {
        Industry industry1 = industryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("duration not find with this Id")
        );
        industry1.setIndustryName(industry.getIndustryName());
        return industryRepository.save(industry1);
    }
}
