package com.CasualtyCat.repository;

import com.CasualtyCat.entity.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends MongoRepository<Type,String > {

}
