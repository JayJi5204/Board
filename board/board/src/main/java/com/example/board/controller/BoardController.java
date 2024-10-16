package com.example.board.controller;

import com.example.board.DTO.BoardRequestDTO;
import com.example.board.DTO.BoardResponseDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 게시판 메인 페이지
    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10); // 페이지 크기를 10으로 설정
        Page<BoardResponseDTO> boardPage = boardService.getBoards(pageable);
        model.addAttribute("boardPage", boardPage);
        return "Index"; // 메인 페이지로 이동
    }


    // 게시글 작성 페이지
    @GetMapping("/create")
    public String getCreatePage() {
        return "CreatePage";
    }

    @PostMapping("/create")
    public String postCreatePage(@ModelAttribute BoardRequestDTO boardRequestDTO) {
        boardService.createBoard(boardRequestDTO);
        return "redirect:/"; // 메인 페이지로 리다이렉트
    }

    // 게시글 페이지
    @GetMapping("/{id}")
    public String getIdPage(@PathVariable Long id, Model model) {
        Optional<BoardResponseDTO> boardResponseDTO = boardService.getBoard(id);
        model.addAttribute("board", boardResponseDTO);
        return "IdPage"; // 게시글 페이지로 이동
    }

    // 게시글 수정 페이지
    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        Optional<BoardResponseDTO> boardResponseDTO = boardService.getBoard(id);
        model.addAttribute("board", boardResponseDTO);
        return "UpdatePage"; // 수정 페이지로 이동
    }


    @PostMapping("/update/{id}")
    public String postUpdatePage(@PathVariable Long id, @ModelAttribute BoardRequestDTO boardRequestDTO) {
        boardService.updateBoard(id, boardRequestDTO);
        return "redirect:/{id}"; // 수정된 게시글 페이지로 리다이렉트
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String postDeletePage(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/"; // 삭제 후 메인 페이지로 리다이렉트
    }
}
