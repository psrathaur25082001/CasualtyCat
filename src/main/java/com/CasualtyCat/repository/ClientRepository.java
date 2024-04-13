package com.CasualtyCat.repository;

import com.CasualtyCat.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@EnableMongoRepositories
public interface ClientRepository extends MongoRepository<Client,String> {
   Optional<Client> findByEmailId(String emailId);
   boolean existsByEmailId(String emailId);
   Optional<Client> findByMobile(long mobile);
   boolean existsByMobile(long mobile);
   List<Client> findByNameContaining(String name);
   Page<Client> findByEmailIdContainingIgnoreCase(String emailId,Pageable pageable);
   Page<Client> findByCompanyNameContainingIgnoreCase(String companyName ,Pageable pageable);
   @Query("{ $expr: { $eq: [ { $year: '$create_date' }, ?0 ] } }")
   Page<Client> findByCreateDateYear(int year, Pageable pageable);


   Page<Client> findByNameContainingIgnoreCase(String name ,Pageable pageable);
}
