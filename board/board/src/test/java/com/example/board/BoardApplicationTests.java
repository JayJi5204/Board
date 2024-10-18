package com.example.board;

import com.example.board.DTO.BoardRequestDTO;
import com.example.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardApplicationTests {
    @Autowired
    private BoardService boardService;

    @Test
    void contextLoads() {
        for (int i = 0; i < 300; i++) {
            BoardRequestDTO boardRequestDTO = new BoardRequestDTO("제목 " + i, "내용 " + i);
            boardService.createBoard(boardRequestDTO); // 게시글 저장
        }
    }
}

