package filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 缓存设置过滤器
 * @author haiyaojing
 *
 */
public class HtmlCacheFilter implements Filter {
	private FilterConfig config = null;
	private HashMap<String, Integer> map = new HashMap<String, Integer>();

	public void destroy() {
		config = null;
		map = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String ext = null;
		int dot = uri.lastIndexOf(".");
		if (dot != -1) {
			ext = uri.substring(dot + 1);
		}
		setResponseHeader(res, uri, ext);
		chain.doFilter(request, response);
	}
	/**
	 * 如果查到相应记录就进行设置
	 * @param res
	 * @param uri
	 * @param ext
	 */
	private void setResponseHeader(HttpServletResponse res, String uri,
			String ext) {
		if (ext != null && ext.length() > 0) {
			Integer expires = (Integer) map.get(ext);
			if (expires != null) {
				if (expires.intValue() > 0) {
					res.setHeader("Cache-Control",
							"max-age=" + expires.intValue() * 100); // HTTP 1.1
				} else {
					res.setHeader("Cache-Control", "no-cache");
					res.setHeader("Pragma", "no-cache"); // HTTP 1.0
					res.setDateHeader("Expires", 0);
				}
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		@SuppressWarnings("rawtypes")
		Enumeration enums = config.getInitParameterNames();
		while (enums.hasMoreElements()) {
			String name = (String) enums.nextElement();
			Integer k = Integer.parseInt(config.getInitParameter(name));
			map.put(name, k);
		}
	}

}
