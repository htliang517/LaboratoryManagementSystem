package my.labMS.core;

// Import jakarta.servlet
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/logout.do")
public class Logout extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Enter Logout,java");
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        session.invalidate();
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                Cookie replace = new Cookie(name, "");
                replace.setMaxAge(0);
                response.addCookie(replace);
                //System.out.println("clear cookie");
            }
        response.sendRedirect("logout_page.html");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }
}