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
	 * @˵��	�����û�����web.xml�����õĹ�������ʱ���ô˷���
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		chain.doFilter(request, response);
	}

	/**
	 * @˵��	AllFilter�ݻ�֮ǰ���ô˷���
	 */
	public void destroy() {}
	/**
	 * @˵��	AllFilter��ʼ��ʱ���ô˷���
	 */
	public void init(FilterConfig config) throws ServletException {}
}
