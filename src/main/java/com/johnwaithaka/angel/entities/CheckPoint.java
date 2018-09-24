package com.johnwaithaka.angel.entities;

import lombok.Data;

import javax.persistence.Id;
import java.io.File;

@Data
public class CheckPoint {

    @Id
    String id;
    File wordImage;
    String incompleteTestWord;
}
