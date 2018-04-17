package org.spring.code.vo.dto;

public class ConfigurationDto extends GenericDto{
	
	private static final long serialVersionUID = 1420635747715993129L;
	
	private static ConfigurationDto configurationDto = null ; 
	
	private ConfigurationDto(){}

	public static ConfigurationDto getInstance() {
		if(configurationDto == null  ) {
			configurationDto =  new ConfigurationDto();
		}
		
		return configurationDto; 
	}
	
	@SuppressWarnings("static-access")
	public void setConfigurationDto(ConfigurationDto configurationDto) {
		this.configurationDto = configurationDto ; 
	}
	
}
