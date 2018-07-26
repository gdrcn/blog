package com.rdc.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter {
	@Override

	public void init(FilterConfig filterConfig) throws ServletException { }

	@Override

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) arg1;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type");
		arg2.doFilter(arg0, arg1);
	}
	@Override
	public void destroy() { }
}
