package com.CasualtyCat.repository;

import com.CasualtyCat.entity.Duration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DurationRepository extends MongoRepository<Duration,String> {
    boolean existsByDuration(String duration);

}
