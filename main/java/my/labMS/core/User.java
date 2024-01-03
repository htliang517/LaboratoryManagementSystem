package my.labMS.core;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

// Check is the user has really login
@WebServlet(urlPatterns = "/user.do")
public class User extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Enter User.java");
        String user_go_to = (String) request.getParameter("status");
        //System.out.println(user_go_to);

        if(user_go_to == null){
            user_go_to = "";
        }

        if(user_go_to.equals("borrow")){
            response.sendRedirect("borrow_page.html");
            return;
        }
        else if(user_go_to.equals("return")){
            response.sendRedirect("return_page.html");
            return;
        }
        else if(user_go_to.equals("expense")){
            response.sendRedirect("expense_page.html");
            return;
        }
        else if(user_go_to.equals("logout")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logout.do");
            dispatcher.forward(request,response);
            return;
            //response.sendRedirect("/logout.do");
        }
        else {
            response.sendRedirect("userInfoPage.html");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
