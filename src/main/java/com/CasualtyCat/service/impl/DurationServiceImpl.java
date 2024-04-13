package com.CasualtyCat.service.impl;

import com.CasualtyCat.entity.Duration;
import com.CasualtyCat.exception.ResourceNotFoundException;
import com.CasualtyCat.repository.DurationRepository;
import com.CasualtyCat.service.DurationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DurationServiceImpl implements DurationService {
    private DurationRepository durationRepository;

    public DurationServiceImpl(DurationRepository durationRepository) {
        this.durationRepository = durationRepository;
    }

    @Override
    public Duration createDuration(Duration duration) {
        if(durationRepository.existsByDuration(duration.getDuration())){
            throw new ResourceNotFoundException("this duration already exits");
        }else {
            duration.setDurationId(UUID.randomUUID().toString());
            duration.setCreatedDate(LocalDate.now());
        }
        Duration save = durationRepository.save(duration);
        return save;
    }

    @Override
    public Duration getDurationById(String id) {
        Duration duration = durationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("duration not find with this Id")
        );
        return duration;
    }

    @Override
    public List<Duration> getDurationList() {
      return durationRepository.findAll();
    }

    @Override
    public Duration updateDurationById(String id, Duration duration) {
        Duration duration1 = durationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("duration not find with this Id")
        );
        duration1.setDuration(duration.getDuration());
        duration1.setCreatedDate(LocalDate.now());
        return  durationRepository.save(duration1);
    }

    @Override
    public String deleteDurationById(String id) {
        Duration duration1 = durationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("duration not find with this Id")
        );
        durationRepository.deleteById(id);
        return "Deleted Successfully";
    }
}
