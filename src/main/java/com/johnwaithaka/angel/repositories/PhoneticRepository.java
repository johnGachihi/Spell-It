package com.johnwaithaka.angel.repositories;

import com.johnwaithaka.angel.entities.Phonetic;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneticRepository extends MongoRepository<Phonetic, String> {
//    List<Phonetic> findAllByText(List<String> texts);
//    <S extends Phonetic> List<S> findAll(Example<S> example);

    List<Phonetic> findAll();

    List<Phonetic> findAllByText(List<String> iterable);

    boolean existsByText(String text);

    @Override
    <S extends Phonetic> List<S> saveAll(Iterable<S> iterable);
}
