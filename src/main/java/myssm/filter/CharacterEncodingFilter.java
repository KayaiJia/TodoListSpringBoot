package myssm.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * 更改编码的过滤器
 * @author kayai
 * @version 1.0
 * @date: 2022/4/21 21:01
 */
@WebFilter("*.do")
public class CharacterEncodingFilter implements Filter {
    private String encoding = "UTF-8";
    @Override
    public void init(FilterConfig filterConfig) {
        String encodingStr = filterConfig.getInitParameter("encoding");
        if (!"".equals(encodingStr) && encodingStr != null){
            encoding = encodingStr;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
    }
}
