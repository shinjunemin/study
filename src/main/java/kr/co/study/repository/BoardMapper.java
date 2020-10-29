package kr.co.study.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.study.domain.Board;

@Repository
@Mapper
public interface BoardMapper {
	List<Board> selectBoardList();
	
	Integer insertBoard(Board board);

}
