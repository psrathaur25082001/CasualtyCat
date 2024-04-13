package com.CasualtyCat.service;

import com.CasualtyCat.entity.Industry;

import java.util.List;

public interface IndustryService {

    public Industry createIndustry(Industry industry);
    public Industry getIndustryById(String id);
    public List<Industry> getIndustryList();
    String deleteIndustryById(String id);
    Industry updateIndustry(String id,Industry industry);

}
