package com.example.tlias_web_management.pojo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpLog {

    private Integer id;
    private LocalDateTime operateTime;
    private String info;
}
