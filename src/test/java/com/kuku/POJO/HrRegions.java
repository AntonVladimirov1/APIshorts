package com.kuku.POJO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

 //@Getter
 //@Setter
 //@ToString
@Data
public class HrRegions {

    private int region_id;
    private String region_name;
    private List<HrRegions> items;
    private List<HrRegions> links;
    private String rel;
    private String href;

}
