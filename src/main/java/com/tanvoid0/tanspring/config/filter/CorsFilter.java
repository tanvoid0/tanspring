//package com.tanvoid0.tanspring.config.filter;
//
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class CorsFilter implements Filter {
//  public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain filterChain) throws IOException, ServletException {
//    HttpServletResponse response = (HttpServletResponse) res;
//    response.setHeader("Access-Control-Allow-Origin", "*");
//    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//    response.setHeader("Access-Control-Max-Age", "3600");
//    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Content-Length, X-Requested-With");
//    filterChain.doFilter(req, res);
//  }
//}
