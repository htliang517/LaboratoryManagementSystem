package my.labMS.core;

// Import jakarta.servlet
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.util.HashMap;

import java.io.IOException;


// Used to check if the username and password is correct and whether auto login or not
@WebServlet(urlPatterns = "/login.do")
public class Login extends HttpServlet{
    private static final long serialVersionUID = 1L;
    int first = 0;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user;
        String password;
        //System.out.println("Enter Login.java");
        if(first == 0){
            //System.out.println("Enter first");
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    Cookie replace = new Cookie(name, "");
                    replace.setMaxAge(0);
                    response.addCookie(replace);
                    //System.out.println("clear cookie");
                }
            first ++;
            response.sendRedirect("login.do");
            return;
        }
        if(first == 1){
            first ++;
            response.sendRedirect("login_page.html");
            return;
        }
        //user = (String) request.getSession().getAttribute("user");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            //System.out.println("Enter Cookie");
            String username = null;
            String passwd = null;
            for(Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                //System.out.println(name);
                //System.out.println(value);
                if("user".equals(name)){
                    username = value;
                    request.getSession().setAttribute("user", username);
                }
                if("passwd".equals(name)){
                    passwd = value;
                    request.getSession().setAttribute("password", passwd);
                }
            }
            if(username != null && passwd != null){
                //System.out.println("Enter cookie login");
                //request.getRequestDispatcher("/user.view").forward(request, response);
                response.sendRedirect("user.do");
                return;
            }
        }

        //System.out.println("Enter other");

        user = request.getParameter("username");
        password = request.getParameter("password");


        HashMap<String, UserInfo> userList;
        ServletContext context = request.getServletContext();
        userList = (HashMap<String, UserInfo>) context.getAttribute("userMap");

        if(userList == null){
            HashMap<String, UserInfo> newUserList = new HashMap<>();
            context.setAttribute("userMap",newUserList);
            //System.out.println("Create Userlist in Login");
        }

        userList = (HashMap<String, UserInfo>) context.getAttribute("userMap");
        //System.out.println(user);

        HttpSession session = request.getSession();
        if(Account.checkAccountNameExistence((String) session.getAttribute("user"),userList) && Account.checkPassword((String) session.getAttribute("user"),(String) session.getAttribute("password"),userList)){
            response.sendRedirect("user.do");
            return;
        }
        if("admin".equals((String) session.getAttribute("user")) && "admin".equals((String) session.getAttribute("password"))){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.view");
            dispatcher.forward(request,response);
        }
        if(user == null){
            user = "";
        }
        if(password == null){
            password = "";
        }
        // Admin Mode
        if(user.equals("admin") && password.equals("admin")){
            //System.out.println("Enter admin mode!");
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("password", password);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin.view");
            dispatcher.forward(request,response);
        }
        if(Account.checkAccountNameExistence(user,userList) && Account.checkPassword(user,password,userList)){
            String auto_login = request.getParameter("auto_login");
            if("auto_login".equals(auto_login)){
                Cookie cookie_user = new Cookie("user", user);
                cookie_user.setMaxAge(7 * 24 * 60 * 60);     // Setting cookie is available in one week
                response.addCookie(cookie_user);

                Cookie cookie_passwd = new Cookie("passwd", password);
                cookie_passwd.setMaxAge(7 * 24 * 60 * 60);     // Setting cookie is available in one week
                response.addCookie(cookie_passwd);
            }
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("password", password);
            //request.getRequestDispatcher("/user.view").forward(request, response);
            response.sendRedirect("user.do");
            return;
        }
        else if(Account.checkAccountNameExistence(user,userList) && !Account.checkPassword(user,password,userList)){
            response.sendRedirect("login_p.html");
            return;
        }
        else if(!Account.checkAccountNameExistence(user,userList) && !Account.checkPasswordEmpty(password)){
            response.sendRedirect("login_u.html");
            return;
        }
        else{
            response.sendRedirect("login_page.html");
            return;
        }
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
