package com.kuku.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
@JsonIgnoreProperties(value = "id",allowSetters = true)
@Data
public class Spartan {

    private int id;
    private String name;
    private String gender;
    private long phone;

}
