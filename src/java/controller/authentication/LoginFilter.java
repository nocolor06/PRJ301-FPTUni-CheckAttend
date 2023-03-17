/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
  
  private FilterConfig config;

  @Override
  public void init(FilterConfig config) throws ServletException {
    this.config = config;
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws ServletException, IOException {
    
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) resp;
    
    // Check if the user is logged in
    HttpSession session = request.getSession(false);
    boolean isLoggedIn = session != null && session.getAttribute("user") != null;
    
    if (isLoggedIn) {
      // If the user is logged in, allow the request to continue
     chain.doFilter(req, resp);
    } else {
      // If the user is not logged in, redirect to the login page
      response.sendRedirect(request.getContextPath() + "/auth/login");
    }
  }

  @Override
  public void destroy() {
    this.config = null;
  }
  
}
