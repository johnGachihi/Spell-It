package com.johnwaithaka.angel.repositories;

import com.johnwaithaka.angel.entities.Level;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LevelRepository extends MongoRepository<Level, String> {

    List<Level> findAll();

    @Override
    Optional<Level> findById(String s);

    long count();
}
