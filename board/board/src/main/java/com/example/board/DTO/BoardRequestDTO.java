package com.example.board.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequestDTO {
    private String title; // 게시글 제목
    private String content; // 게시글 내용

    public BoardRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
