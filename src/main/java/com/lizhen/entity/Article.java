package com.lizhen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article {
    private String id ;
    private String title;
    private String insert_img;
    private String content;
    private Date up_date;
    private String guruId;
    private String status;
}
