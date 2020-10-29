package kr.co.study.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groovy.util.logging.Slf4j;
import kr.co.study.repository.MainMapper;
import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@Service
public class MainService {

	private final MainMapper mainMapper;
	
	@Transactional(readOnly = true)
	public Integer getUserCount() {
		return mainMapper.getUserCount();
	}
	
}
