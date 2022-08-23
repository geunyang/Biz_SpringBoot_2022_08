package com.callor.todo.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "tbl_todo", schema = "bootDB")
public class TodoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 10)
    private String sdate;

    private String stime;

    @Column(length = 255, nullable = false)
    private String content;

    @Column(length = 10)
    private String edate;

    private String etime;


}
