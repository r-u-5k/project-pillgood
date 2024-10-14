package com.pillgood.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pillgood.dto.BoardDto;
import com.pillgood.entity.Category;
import com.pillgood.entity.board.Board;
import com.pillgood.entity.board.BoardMenu;
import com.pillgood.entity.board.BoardType;
import com.pillgood.mapper.ToDtoMapper;
import com.pillgood.service.BoardService;


//@CrossOrigin("*")
@RestController
public class BoardRestController {

	@Autowired
	private BoardService boardService; 
	
	//게시판 생성
	@PostMapping("/api/board/createBoard")
	public ResponseEntity<BoardDto> createBoard (@RequestBody BoardDto boardDto) throws Exception {
		BoardDto createBoard=boardService.createBoard(boardDto);
		return new ResponseEntity<>(createBoard,HttpStatus.OK);
	}
	
	//게시판 수정
	@PutMapping("/api/board/updateBoard")
	public ResponseEntity<BoardDto> updateBoard (@RequestBody BoardDto boardDto) throws Exception {
		BoardDto updateBoard = boardService.updateBoard(boardDto);
		return new ResponseEntity<>(updateBoard,HttpStatus.OK);
	}
	
	//게시판 삭제
	@DeleteMapping("/api/board/{boardNo}") 
	public void deletBoard(@PathVariable(name = "boardNo")Long boardNo) throws Exception{
		boardService.deleteBoard(boardNo);
	}
	
	//게시판 공지사항리스트
	@GetMapping("/api/board/NoticeList")
	public ResponseEntity<List<BoardDto>> findBoardNoticeList() throws Exception {
		List<BoardDto> boards = boardService.findByBoardNotice();
		return new ResponseEntity<>(boards , HttpStatus.OK);
	}
	
	//게시판 QNA리스트
		@GetMapping("/api/board/QNAList")
		public ResponseEntity<List<BoardDto>> findBoardQnaList() throws Exception {
			List<BoardDto> boards = boardService.findByBoardQNA();
			System.out.println(boards);
			return new ResponseEntity<>(boards , HttpStatus.OK);
		}
	
	//게시판 번호로 찾기
	@GetMapping("/api/boardDetail/{boardNo}")
	public ResponseEntity<BoardDto> findBoardByOne(@PathVariable(name = "boardNo") Long boardNo) throws Exception {
		BoardDto findBoard = boardService.findBoardByOne(boardNo);
		return new ResponseEntity<>(findBoard , HttpStatus.OK);
	}
}
