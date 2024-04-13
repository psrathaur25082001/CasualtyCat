package com.CasualtyCat.repository;

import com.CasualtyCat.entity.Client;
import com.CasualtyCat.entity.Insurer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InsurerRepository extends MongoRepository<Insurer,String> {
    Optional<Client> findByContactNumber(long mobile);
    boolean existsByContactNumber(long mobile);

}
