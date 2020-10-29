package kr.co.study.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import kr.co.study.domain.Account;
import kr.co.study.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BoardDTO {//DTO는 화면의 용도에 따라 이름을 달리함
	//@EqualsAndHashCode ? 
	private String id;
	//private String title;
	//private String contents;
	private String regId;
	private LocalDate regDate;
	
	@NotEmpty(message = "제목을 입력하세요.")
	@Size(max = 10, min = 0, message = "1~10자만 입력가능.")
	private String title;
	
	@NotEmpty(message = "내용을 입력하세요.")
	private String contents;
	
	public Board toBoard() {
		return this.toBoard(null);
	}

	public Board toBoard(Account account) {
		return Board.builder()
		.id(this.id)
		.title(this.title)
		.contents(this.contents)
		.regId(account == null ? this.regId : account.getId())
		.regDate(this.regDate)
		.build();
	}

	
}
