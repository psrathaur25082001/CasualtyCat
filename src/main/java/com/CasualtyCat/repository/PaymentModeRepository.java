package com.CasualtyCat.repository;

import com.CasualtyCat.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentModeRepository extends MongoRepository<Client,String> {
}
