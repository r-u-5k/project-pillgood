package com.pillgood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.board.Board;
import com.pillgood.entity.board.BoardMenu;
import com.pillgood.entity.board.BoardType;

public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByBoardMenu(BoardMenu boardMenu);
//	List<Board> findByBoardTypeNot(BoardType type);
	
	Board findByBoardNo(Long boardNo);
	
}
