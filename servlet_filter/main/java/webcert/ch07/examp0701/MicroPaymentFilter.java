package webcert.ch07.examp0701;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MicroPaymentFilter implements Filter {
	
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		System.out.println(req.getParameter("password"));
		if(req.getParameter("password")==null || req.getParameter("password").isBlank()) {
			System.out.println("Password non inserita");
		} else {
			chain.doFilter(request, response);
		}

	}

	public void destroy() {
	}
	
}
