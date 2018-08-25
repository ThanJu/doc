package me.phoibe.doc.cms;


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 启动类
 */
@SpringBootApplication
@EnableEncryptableProperties
@Configuration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    /**
     * 文件上传配置
     * 
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("1048580000MB"); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize("10485800000MB");
        return factory.createMultipartConfig();
    }
}
