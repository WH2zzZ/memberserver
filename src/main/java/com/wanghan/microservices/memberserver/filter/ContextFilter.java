package com.wanghan.microservices.memberserver.filter;

import com.wanghan.microservices.memberserver.context.ContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ContextFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "username";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String username = request.getHeader(AUTHORIZATION_HEADER);
        ContextHolder.getContext().setUser(username);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
