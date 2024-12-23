package com.example.board.controller;

import com.example.board.DTO.BoardRequestDTO;
import com.example.board.DTO.BoardResponseDTO;
import com.example.board.service.BoardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// 이 클래스가 Controller 영역이라는 어노테이션
@Controller // 이 클래스가 Controller 영역이라는걸 알려주는 어노테이션
@RequiredArgsConstructor // 자동으로 생성자 주입에 대한 코드를 생성
public class BoardController {
    private final BoardService boardService;

    // 게시판 메인 페이지
    @GetMapping("/")
    public String mainPage(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<BoardResponseDTO> paging = boardService.getBoards(page);
        model.addAttribute("paging", paging);
        return "Index"; // 메인 페이지로 이동
    }

    // 게시글 작성 페이지
    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("boardRequestDTO", new BoardRequestDTO());
        return "CreatePage";
    }

    @PostMapping("/create")
    public String postCreatePage(@ModelAttribute BoardRequestDTO boardRequestDTO) {
        boardService.createBoard(boardRequestDTO);
        return "redirect:/"; // 메인 페이지로 리다이렉트
    }

    // 개별 게시글 페이지
    @GetMapping("/boards/{id}")
    public String getIdPage(@PathVariable Long id, Model model) {
        BoardResponseDTO boardResponseDTO = boardService.getBoard(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다.")); // Optional에서 값 꺼내기
        model.addAttribute("board", boardResponseDTO); // DTO를 모델에 추가
        return "IdPage"; // 게시글 페이지로 이동
    }

    // 게시글 수정 페이지
    @GetMapping("/update/{id}")
    public String getUpdatePage(@PathVariable Long id, Model model) {
        BoardResponseDTO boardResponseDTO = boardService.getBoard(id)
                .orElseThrow(() -> new EntityNotFoundException("게시글을 찾을 수 없습니다."));;
        model.addAttribute("board", boardResponseDTO);
        return "UpdatePage"; // 수정 페이지로 이동
    }

    @PostMapping("/update/{id}")
    public String postUpdatePage(@PathVariable Long id,@ModelAttribute("board") BoardRequestDTO boardRequestDTO) {
        boardService.updateBoard(id, boardRequestDTO);
        return "redirect:/boards/" + id; // 수정된 게시글 페이지로 리다이렉트
    }

    // 게시글 삭제 페이지
    @PostMapping("/delete/{id}")
    public String postDeletePage(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/"; // 삭제 후 메인 페이지로 리다이렉트
    }
}
