package com.maxcar.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maxcar.core.utils.CollectionUtil;
import com.maxcar.core.utils.StringUtils;

/**
 * CORS 实现客户端发出AJAX跨域请求 过滤所有的HTTP请求，并将CORS响应头写入response对象中
 * 
 * @ClassName: CorsFilter
 * @author huangxu
 * @date 2017年11月17日 上午10:54:57
 *
 */
public class CorsFilter implements Filter {
	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	public void init(FilterConfig filterConfig) throws ServletException {
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (StringUtils.isNotEmpty(allowOrigin)) {
			List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
			if (CollectionUtil.listIsNotEmpty(allowOriginList)) {
				String currentOrigin = request.getHeader("Origin");
				if (allowOriginList.contains(currentOrigin)) {
					response.setHeader("Access-Control-Allow-Origin", currentOrigin);
				}
			}
		}
		if (StringUtils.isNotEmpty(allowMethods)) {
			//允许访问的方法名，多个方法名用逗号分割
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		if (StringUtils.isNotEmpty(allowCredentials)) {
			//是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true。
			response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
		}
		if (StringUtils.isNotEmpty(allowHeaders)) {
			//允许服务端访问的客户端请求头，多个请求头用逗号分割
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		if (StringUtils.isNotEmpty(exposeHeaders)) {
			//允许客户端访问的服务端响应头，多个响应头用逗号分割
			response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
		}
		chain.doFilter(req, res);
	}

	public void destroy() {
	}
}