package com.CasualtyCat.service;

import com.CasualtyCat.entity.Duration;

import java.util.List;

public interface DurationService {

    public Duration createDuration(Duration duration);
    public Duration getDurationById(String id);
    public List<Duration> getDurationList();
    public Duration  updateDurationById(String id,Duration duration);
    public String deleteDurationById(String id);
}
