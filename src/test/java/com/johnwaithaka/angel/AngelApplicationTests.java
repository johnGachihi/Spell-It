package com.johnwaithaka.angel;

import com.johnwaithaka.angel.Utility.BinarySearch;
import com.johnwaithaka.angel.entities.Admin;
import com.johnwaithaka.angel.repositories.AdminRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AngelApplicationTests {

    @Autowired
    private AdminRepository adminRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void binarySearchTest(){
//        List<Admin> adminList = adminRepository.findAll();
//        BinarySearch.getLevelById(adminList, "5ba78b4fafe7d43ac84ad9d3");
        Long.parseLong("5ba78b4fafe7d43ac84ad9d3", 16);
    }

}
