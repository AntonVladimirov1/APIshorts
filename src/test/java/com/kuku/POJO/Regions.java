package com.kuku.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/// LOMBOK
//@Getter
//@Setter
//@ToString
@Data // creates getter/setter
@JsonIgnoreProperties(ignoreUnknown = true) // it ignores fields that we don't need

public class Regions {

    @JsonProperty("region_id") // we tell Jackson, find region_id from JSON response and set value to variable below
    private int regionId;
    @JsonProperty("region_name")
    private String regionName;
    private List<Regions> regionNames;
    private List<Regions> items;
    private List<Regions> links;
    private String rel;
    private String href;
    private int count;

}
