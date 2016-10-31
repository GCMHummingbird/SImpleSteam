package top.qlbxxxz.tools;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AllFilter implements Filter {
	/**
	 * @说明	当有用户访问web.xml中设置的过滤内容时调用此方法
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		chain.doFilter(request, response);
	}

	/**
	 * @说明	AllFilter摧毁之前调用此方法
	 */
	public void destroy() {}
	/**
	 * @说明	AllFilter初始化时调用此方法
	 */
	public void init(FilterConfig config) throws ServletException {}
}
