package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // Các đường dẫn công khai không cần đăng nhập
        if (path.startsWith("/login") || path.startsWith("/register-customer") || path.equals("/") || 
            path.startsWith("/assets/") || path.endsWith(".css") || path.endsWith(".js")) {
            chain.doFilter(request, response);
            return;
        }

        // Kiểm tra đăng nhập cho admin (chỉ admin cần đăng nhập)
        if (path.startsWith("/vaccines") || path.startsWith("/statistics") || path.startsWith("/admin") || path.startsWith("/history")) {
            if (session == null || !"admin".equals(session.getAttribute("userType"))) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?type=admin");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

