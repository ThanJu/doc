package me.phoibe.doc.cms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {
	
	@Value("${breakpoint.upload.window}")
	private String window;
	@Value("${breakpoint.upload.linux}")
	private String linux;
	@Value("${breakpoint.upload.status}")
	private String status;
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String finalDirPath="";
		if(status.equals("1")) {
			finalDirPath = window;
		}else {
			finalDirPath = linux;
		}
        registry.addResourceHandler("/docword/**").addResourceLocations("file:"+finalDirPath);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }
}
