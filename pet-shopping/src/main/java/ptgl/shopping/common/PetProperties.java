package ptgl.shopping.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import lombok.data;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties()
//@Data
@PropertySource(value = { "classpath:application.properties" })
public class PetProperties {

	@Value("${elasticsearch.url}")
	private String ESUrl;
	
	
	
}
