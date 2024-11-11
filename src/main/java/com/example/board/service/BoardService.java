package com.example.board.service;

import com.example.board.DTO.BoardRequestDTO;
import com.example.board.DTO.BoardResponseDTO;
import com.example.board.entity.BoardEntity;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // 이 클래스가 Service 영역이라는걸 알려주는 어노테이션
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    // 게시글 생성 기능
    public void createBoard(BoardRequestDTO boardRequestDTO) {
        BoardEntity boardEntity = new BoardEntity(boardRequestDTO);
        boardRepository.save(boardEntity);
    }

    // 모든 게시글 조회 기능
    public Page<BoardResponseDTO> getBoards(int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC,"id"));
        return boardRepository.findAll(pageable).map(BoardResponseDTO::new); // 존재하면 DTO로 변환하여 반환
    }

    // 개별 게시글 조회 기능
    public Optional<BoardResponseDTO> getBoard(Long id) {
        return boardRepository.findById(id).map(BoardResponseDTO::new); // 존재하면 DTO로 변환하여 반환
    }

    // 게시글 삭제 기능
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    // 게시글 수정 기능
    public void updateBoard(Long id, BoardRequestDTO boardRequestDTO) {
        BoardEntity boardEntity = boardRepository.findById(id).orElseThrow();
        boardEntity.update(boardRequestDTO); // 업데이트 메서드 호출
        boardRepository.save(boardEntity); // 업데이트된 엔티티 저장
    }

}
