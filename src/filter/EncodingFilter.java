package filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 统一设置编码为~
 * @author haiyaojing
 *
 */
public class EncodingFilter implements Filter{
	/**
	 * 从filter的配置参数中获取编码
	 */
	private String encoding = null;
	private FilterConfig config = null;
	public void destroy() {
		encoding = null;
		config = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (encoding != null ) {
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
		this.encoding = config.getInitParameter("encoding");
	}

}
