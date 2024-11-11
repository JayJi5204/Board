package com.example.board.repository;

import com.example.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 이 인터페이스가 Repository 영역이라는 걸 알려주는 어노테이션
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
