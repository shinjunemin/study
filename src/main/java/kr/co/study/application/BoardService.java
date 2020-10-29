package kr.co.study.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.study.domain.Board;
import kr.co.study.repository.BoardMapper;
import kr.co.study.web.dto.BoardDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	@Transactional(readOnly = true)
	public List<BoardDTO> selectBoardList(){
		
		return boardMapper.selectBoardList().stream()
				.map(board -> BoardDTO.builder()
						.id(board.getId())
						.title(board.getTitle())
						.contents(board.getContents())
						.regId(board.getRegId())
						.regDate(board.getRegDate())
						.build()
					)
				.collect(Collectors.toList());
		//return boardMapper.selectBoardList();
	}
	
	@Transactional(readOnly = false)
	public void insertBoard(Board board) {
		boardMapper.insertBoard(board);
	}

	
}
