package com.johnwaithaka.angel.repositories;

import com.johnwaithaka.angel.entities.Angel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AngelRepository extends MongoRepository<Angel, String> {
    long count();
}
