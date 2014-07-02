package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO: 验证用户是否已经登录
 * @author haiyaojing
 *
 */
public class CheckUserOnlineFilter implements Filter{

	@SuppressWarnings("unused")
	private FilterConfig config = null;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		chain.doFilter(req, res);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}
	
	
}
