package com.johnwaithaka.angel.repositories;

import com.johnwaithaka.angel.entities.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {

    Admin findByUsername(String username);
}
