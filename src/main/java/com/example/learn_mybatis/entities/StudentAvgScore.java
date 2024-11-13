package com.example.learn_mybatis.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentAvgScore {
    private String studentId;
    private String studentName;
    private float avgScore;
}
