package com.johnwaithaka.angel.repositories;

import com.johnwaithaka.angel.entities.Angel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AngelRepository extends MongoRepository<Angel, String> {

    long count();

    @Query(value = "" +
            "{$expr: " +
            "   {$and: [" +
            "       {$eq: [{$month: '$regDate'}, ?0]}," +
            "       {$eq: [{$year: '$regDate'}, {$year: ?1}]}" +
            "   ]}}", count = true)
    long countByRegDate(int month, Date currentDate);

    /*MUST CONVERT RETURN TYPE TO Angel*/
    Optional<Angel> findByUsername(String s);
}
