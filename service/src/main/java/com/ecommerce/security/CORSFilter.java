package com.ecommerce.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@PropertySource("classpath:securityConstants.properties")
@Configuration
public class CORSFilter implements Filter {

	@Value("${FRONTEND_ADDRESS}")
	private String frontEndAddress;

	@Value("${SECOND_BACKEND}")
	private String secondBackEnd;



	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		System.out.println("Filtering on...........................................................");
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		if (frontEndAddress.equalsIgnoreCase(request.getHeader("Origin"))) {
			response.setHeader("Access-Control-Allow-Origin", frontEndAddress);
		}


		else if(secondBackEnd.equalsIgnoreCase(request.getHeader("Origin"))){
			response.setHeader("Access-Control-Allow-Origin", secondBackEnd);
		}


        response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT,PATCH, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");

		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}