package kr.co.study.web.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class JoinDTO {
//	@Getter
//	@ToString
//	@AllArgsConstructor
	//클래스에서 가져온 그대로의 값을 써야함
	//@AllArgsConstructor : 필드값을 모두 포함한 생성자를 자동 생성해준다.
// joinDTO에 있는 변수명은 join.html에 th:field="*{ID}"의 ID와 같아야함
	//에러가 낫을경우 <label class="err" th:if="${#fields.hasErrors('NAME')}" th:errors="*{NAME}">NAME Error</label> th:errors안에 message가 들어갈 것임
	@NotEmpty(message=" 아이디를 입력해 주세요. ")
	private String id;

	@NotEmpty(message=" 이름을 입력해 주세요. ")
	private String name;

	@Size(min=2, max=12, message=" 비밀번호를 입력해 주세요. ")
	private String password;

}
