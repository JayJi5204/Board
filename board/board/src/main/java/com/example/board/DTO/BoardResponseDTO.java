package com.example.board.DTO;

import com.example.board.Entity.BoardEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter // getter를 사용하기 위한 어노테이션
@NoArgsConstructor // default 생성자를 자동으로 생성해주는 어노테이션
public class BoardResponseDTO { // Service와 Controller로 데이터를 보낼 때 사용하는 객체
    private Long id; // 게시글 번호
    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private LocalDateTime createTime; // 생성 시간
    private LocalDateTime updateTime; // 수정 시간

    // Entity -> DTO 변환 생성자
    public BoardResponseDTO(BoardEntity boardEntity) {
        this.id = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.createTime = boardEntity.getCreateTime();
        this.updateTime = boardEntity.getUpdateTime();
    }
}
