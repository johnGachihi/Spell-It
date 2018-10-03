package com.johnwaithaka.angel.repositories;

import com.johnwaithaka.angel.entities.Angel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AngelRepository extends MongoRepository<Angel, String> {

//    @Autowired
//    MongoTemplate mongoTemplate;


    long count();

    @Query(value = "{$expr: {$eq: [{$month: '$regDate'}, ?0]}}", count = true)
    long countByRegDate(int month);
}
