package kr.co.study.repository;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import kr.co.study.domain.Account;
import kr.co.study.web.dto.JoinDTO;

@MapperScan
@Repository
public interface SignInMapper {

	Account selectUserById(String id);
	
	Integer insertUser(JoinDTO join); 
	//Integer insertUser(User user); 
}
