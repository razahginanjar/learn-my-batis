package com.example.learn_mybatis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student implements Serializable {
    @JsonProperty(value = "sId")
    private String sId;
    @JsonProperty(value = "sName")
    private String sName;
    @JsonProperty(value = "sBirth")
    private String sBirth;
    @JsonProperty(value = "sSex")
    private String sSex;
}
