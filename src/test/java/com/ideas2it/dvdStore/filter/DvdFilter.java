package com.ideas2it.dvdStore.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DvdFilter implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }  
     
    public void doFilter(ServletRequest request, ServletResponse response,  
           FilterChain chain) throws IOException, ServletException { 

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session  = req.getSession(false);
        Integer userId = null;
        if (null != session) {
            userId = (Integer) session.getAttribute("userid");
        }
 
       String uri = req.getRequestURI();

       if (session == null ) {
           RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
           rd.forward(request, response);
       } else {

System.out.println(userId);
System.out.println(uri);
System.out.println(request.getParameter("userId"));

           if ((null == userId) && (!(uri.endsWith("CustomerRegister"))) 
                   && (null == request.getParameter("userId")) ) {  
               RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
               rd.forward(request, response); 
           }  else {  
               res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
               res.setHeader("Pragma", "no-cache");
               res.setDateHeader("Expires", 0);
               chain.doFilter(request, response);
          }  
      }
   }  

   public void destroy() {
   }  
 
}
