package com.callor.book.model;
/*
도서정보관리를 위하여 객체 추상화
도서명, 출판사, 저자, 발행연도, 가격 등의 항목이 필요하다
이러한 항목을 기준으로 VO 클래스 디자인

JPA 의 Entity 로 등록하고 물리적 table 로 구성하기
 */

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "tbl_books", schema = "bootdb")
public class BookVO {

    @Id // VO 클래스를 Entity 로 등록하려면 반드시 PK 지정 필요
    /*
    isbn 처럼 자릿수가 정해진 문자열은
    규격에 맞는 문자열을 지정하는것이 좋다
     */
    @Column(length = 13)
    private String isbn; // 도서코드

    @Column(length = 125, nullable = false)
    private String title; // 도서명

    private String comp; // 출판사
    private String author; // 저자

    @Column(length = 10)
    private String pubdate; // 출판일

    /*
    변수형이 int 형일 경우
    기본값이 not null 로 설정되므로
    임의로 nullable 을 true 로 하여 not null 을 해제 한다
     */
    @Column(nullable = true)
    private int price; // 정가
}
