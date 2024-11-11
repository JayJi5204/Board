package com.example.board.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter와 Setter를 사용하기 위한 어노테이션
@NoArgsConstructor

public class BoardRequestDTO {
    private String title; // 게시글 제목
    private String content; // 게시글 내용

    public BoardRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
