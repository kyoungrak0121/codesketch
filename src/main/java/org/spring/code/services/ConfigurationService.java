package org.spring.code.services;

import javax.annotation.PostConstruct;

import org.spring.code.vo.dto.ConfigurationDto;
import org.springframework.stereotype.Service;


@Service
public class ConfigurationService{
	
	
	// DAO 생성 후 DB로 가져오기 
	
	private static ConfigurationDto configurationDto ;
	
	@PostConstruct
	public void init() {
		// 나중에 Dao 만들어서 DB 읽기
		configurationDto = ConfigurationDto.getInstance();
	}
	
	public ConfigurationDto getConfigurationDto(){
		return configurationDto;
	}
	


}
