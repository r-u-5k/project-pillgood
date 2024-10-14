package com.pillgood.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pillgood.dto.BoardDto;
import com.pillgood.entity.board.Board;
import com.pillgood.entity.board.BoardMenu;
import com.pillgood.entity.board.BoardType;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.Data;

@Data
@Transactional
@Service

public class BoardService {

	private final BoardRepository boardRepository;

	// 게시판 생성
	public BoardDto createBoard(BoardDto boardDto) throws Exception {
		System.out.println();

		Board createBoard = new Board();

		createBoard.setBoardTitle(boardDto.getBoardTitle());
		createBoard.setBoardContent(boardDto.getBoardContent());
		createBoard.setBoardMenu(boardDto.getBoardMenu());
		createBoard.setBoardDate(boardDto.getBoardDate());

		if (boardDto.getBoardMenu() == (BoardMenu.Notice)) {

			createBoard.setBoardType(BoardType.NEWS);

		} else if (boardDto.getBoardMenu() == (BoardMenu.QNA) && (boardDto.getBoardType() == (BoardType.NEWS))) {

			createBoard.setBoardMenu(BoardMenu.Notice);
			createBoard.setBoardType(boardDto.getBoardType());

		} else {

			createBoard.setBoardType(boardDto.getBoardType());

		}

		System.out.println(">>>>>>>>>>>>>>>>>>> " + boardDto);

		Board saveBoard = boardRepository.save(createBoard);
		return ToDtoMapper.toBoardDto(saveBoard);
	}

	// 게시판 수정
	public BoardDto updateBoard(BoardDto boardDto) throws Exception {

		Board board = boardRepository.findByBoardNo(boardDto.getBoardNo());
		
		board.setBoardTitle(boardDto.getBoardTitle());
		board.setBoardContent(boardDto.getBoardContent());

		return ToDtoMapper.toBoardDto(boardRepository.save(board));
	}

	// 게시판 삭제
	public void deleteBoard(Long boardNo) throws Exception {
		boardRepository.deleteById(boardNo);

	}

	//게시판 공지사항리스트
	public List<BoardDto> findByBoardNotice() throws Exception {
		List<Board> boardList = boardRepository.findByBoardMenu(BoardMenu.Notice);
		List<BoardDto> boardNoticeListDto = boardList.stream().map(ToDtoMapper::toBoardDto).collect(Collectors.toList());
		return boardNoticeListDto;
	}

	//게시판 QNA리스트
	public List<BoardDto> findByBoardQNA() throws Exception {
		List<Board> boardList = boardRepository.findByBoardMenu(BoardMenu.QNA);
		List<BoardDto> boardListDto = boardList.stream().map(ToDtoMapper::toBoardDto).collect(Collectors.toList());
		return boardListDto;
	}
	
	// 게시판 번호로 찾기
	public BoardDto findBoardByOne(Long boardNo) {

		return ToDtoMapper.toBoardDto(boardRepository.findByBoardNo(boardNo));

	}

}
