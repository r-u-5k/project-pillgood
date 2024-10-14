package com.pillgood.dto;

import java.util.Date;

import com.pillgood.entity.board.BoardMenu;
import com.pillgood.entity.board.BoardType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BoardDto {
	
	private Long boardNo;
    private String boardTitle;
    private String boardContent;
    private BoardType boardType;
    private BoardMenu boardMenu;
    private Date boardDate;
	private Long adminNo;

   
    
}
