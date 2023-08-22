package com.kuku.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

                        /// LOMBOK
@Data  // creates getter/setter/toString - behind the seen
@JsonIgnoreProperties(ignoreUnknown = true) // it ignores fields that we don't need

public class Employee_XXX {

    @JsonProperty("first_name")  // find region_id from JSON response and set value to variable below
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private int salary;

}
