package my.labMS.core;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/AddNewUser.do")
public class AddNewAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    HashMap<String, UserInfo> userList;
    ServletContext context;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Enter AddNewAccount");
        String PageToForward;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        context = request.getServletContext();
        userList = (HashMap<String, UserInfo>) context.getAttribute("userMap");
        if(userList == null){
            HashMap<String, UserInfo> newUserList = new HashMap<>();
            context.setAttribute("userMap",newUserList);
            //System.out.println("Create Userlist in Add New Account");
        }
        userList = (HashMap<String, UserInfo>) context.getAttribute("userMap");

        String page = request.getParameter("status");
        //System.out.println(page);
        if ("submit".equals(page)) {
            String username = request.getParameter("username");
            if(Account.checkAccountNameExistence(username, userList)){
                PageToForward = "addUserPageU.html";
            }
            else if(username.equals("admin")){
                PageToForward = "addUserPageU.html";
            }
            else {
                String new_email = request.getParameter("email");
                String new_name = request.getParameter("name");
                String new_username = request.getParameter("username");
                String new_password = request.getParameter("password");
                String new_gender = request.getParameter("gender");
                //System.out.println(new_username + " " + new_password);
                userList = (HashMap<String, UserInfo>) context.getAttribute("userMap");
                // Generate UserInfo
                UserInfo new_user = new UserInfo();
                new_user.setEmail(new_email);
                new_user.setName(new_name);
                new_user.setAccountName(new_username);
                new_user.setPassword(new_password);
                new_user.setGender(new_gender);
                // Generate Hashmap
                userList.put(new_username, new_user);

                PageToForward = "success_create.html";
            }
        }
        else {
            PageToForward = "addUserPage.html";
        }
        response.sendRedirect(PageToForward);
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
