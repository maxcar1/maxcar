package com.maxcar.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@PropertySource(value = {"classpath:oos.properties","classpath:dubbo.properties","classpath:kafka.properties"})
@ImportResource("classpath:dubbo/dubbox-customer.xml")
@EnableScheduling
public class MaxcarCommonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaxcarCommonApiApplication.class, args);
	}
	/**
	 * 跨域过滤器
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// 设置你要允许的网站域名，如果全允许则设为 *
		config.addAllowedOrigin("*");
		// 如果要限制 HEADER 或 METHOD 请自行更改
		config.addAllowedHeader(CorsConfiguration.ALL);
		config.addAllowedMethod(CorsConfiguration.ALL);
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		// 重要
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return new CorsFilter(source);
	}
}
