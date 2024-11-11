package com.example.board.entity;

import com.example.board.DTO.BoardRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity // 이 클래스가 Entity 영역이라는 것을 알려주는 어노테이션
@Getter // getter를 사용하기 위한 어노테이션
@NoArgsConstructor // 파라미터가 없는 default 생성자 생성
@EntityListeners(AuditingEntityListener.class) // 생성, 수정 일자를 구현할 수 있게 하는 어노테이션
public class BoardEntity {

    @Id // 기본키 할당 어노테이션
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_id") //OracleDB는 SEQUENCE를 사용한다.
    @SequenceGenerator(name = "board_id", sequenceName = "BOARD_ID", allocationSize = 1)
    private Long id; // 글 번호

    @Column(nullable = false) // 글 제목은 null이 되서는 안된다.
    private String title; // 글 제목

    @Column(nullable = false) // 글 내용은 null이 되서는 안된다.
    private String content; // 글 내용

    @CreatedDate // 생성 일자가 자동으로 생성되게 하는 어노테이션
    @Column(updatable = false) // 생성일은 수정되지 않도록 설정
    private LocalDateTime createTime; // 생성 일자

    @LastModifiedDate // 수정 일자가 자동으로 생성되게 하는 어노테이션
    private LocalDateTime updateTime; // 수정 일자

    public BoardEntity(BoardRequestDTO boardRequestDTO) {
        this.title = boardRequestDTO.getTitle();
        this.content = boardRequestDTO.getContent();
    }

    public void update(BoardRequestDTO boardRequestDTO) {
        this.title = boardRequestDTO.getTitle();
        this.content = boardRequestDTO.getContent();
    }
}
