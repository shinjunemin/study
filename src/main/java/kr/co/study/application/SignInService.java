package kr.co.study.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import kr.co.study.domain.Account;
import kr.co.study.repository.SignInMapper;
import kr.co.study.web.dto.JoinDTO;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignInService implements UserDetailsService{ //수정되면 안되고 다른 서비스를 만들어야함. 
	
	@Autowired
	private final SignInMapper signInMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = signInMapper.selectUserById(username);
		if(account == null) {
			throw new UsernameNotFoundException("사용자 정보를 찾을 수 없습니다.");
		}
		
		//account.setPassword(passwordEncoder.encode(account.getPassword()));
		log.debug("User Info : {}", account);
		
		return account;
		
		//return new User(account.getId(), passwordEncoder.encode(account.getPassword()), Collections.singleton(new SimpleGrantedAuthority(account.getRole())));
		
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
//		
//		//db userId
//		return new User(username, passwordEncoder.encode("123456"), authorities);
	}

	public Integer insertUser(JoinDTO joinDTO) {
		log.info("SignInService.insertUser!!");
		//return signInMapper.insertUser(joinDTO);
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
		
		User user = new User(joinDTO.getId(), passwordEncoder.encode(joinDTO.getPassword()), authorities);
		
		return signInMapper.insertUser(joinDTO);
		
		//Account account = Account.toAccount(joinDTO);
	}
}
