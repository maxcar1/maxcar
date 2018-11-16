package com.maxcar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
* @Description: 商户接口启动类
* @Author: 罗顺锋
* @Date: 2018/4/24
*/
@ServletComponentScan
@SpringBootApplication
@PropertySource(value = {"classpath:dubbo.properties","classpath:dasouche.properties", "classpath:redis-config.properties", "classpath:kafka.properties", "classpath:oos.properties"})
@ImportResource("classpath:dubbo/dubbox-customer.xml")
public class TenantApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenantApiApplication.class, args);
	}

}
