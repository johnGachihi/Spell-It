package com.johnwaithaka.angel.services;

import com.johnwaithaka.angel.entities.Level;
import com.johnwaithaka.angel.repositories.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("levelsService")
//@EnableCaching
public class LevelsService {

    private LevelRepository levelRepository;

    private List<Level> levels = new ArrayList<>();

    public LevelsService() {
    }

    @Autowired
    private LevelsService(LevelRepository levelRepository){
        this.levelRepository = levelRepository;
    }

    @Cacheable(cacheNames = "levels")
    public List<Level> findAllLevels(){
        return levelRepository.findAll();
    }

    @Cacheable(cacheNames = "levels", key = "#id")
    public Optional<Level> findById(String id){
        return levelRepository.findById(id);
    }

    @Cacheable(cacheNames = "levels")
    public int countLevels(){
        return (int)levelRepository.count();
    }

    @CacheEvict(cacheNames = "levels", allEntries = true)
    public Level save(Level l){
        return levelRepository.save(l);
    }
}
