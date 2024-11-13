package com.example.learn_mybatis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAndCourseInfo implements Serializable {
    @JsonProperty(value = "sId")
    private String sId;
    @JsonProperty(value = "sName")
    private String sName;
    @JsonProperty(value = "sBirth")
    private String sBirth;
    @JsonProperty(value = "sSex")
    private String sSex;

    @JsonProperty(value = "scoreC1")
    private Integer scoreC1;
    @JsonProperty(value = "scoreC2")
    private Integer scoreC2;
}
