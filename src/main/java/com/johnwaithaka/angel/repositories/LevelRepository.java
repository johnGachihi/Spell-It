package com.johnwaithaka.angel.repositories;

import com.johnwaithaka.angel.entities.Level;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LevelRepository extends MongoRepository<Level, String> {

    List<Level> findAll();
    long count();
}
