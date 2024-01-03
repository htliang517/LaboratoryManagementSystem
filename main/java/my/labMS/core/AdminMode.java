package my.labMS.core;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

// Check is the user has really login
@WebServlet(urlPatterns = "/admin.view")
public class AdminMode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    HashMap<String, UserInfo> userList;
    HashMap<Integer, BorrowInfo> borrowList;
    HashMap<Integer, ReturnInfo> returnList;
    HashMap<Integer, ExpenseInfo> expenseList;
    ServletContext context;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        context = request.getServletContext();
        userList = (HashMap<String, UserInfo>) context.getAttribute("userMap");
        borrowList = (HashMap<Integer, BorrowInfo>) context.getAttribute("borrowMap");
        returnList = (HashMap<Integer, ReturnInfo>) context.getAttribute("returnMap");
        expenseList = (HashMap<Integer, ExpenseInfo>) context.getAttribute("expenseMap");


        if(userList == null){
            userList = new HashMap<>();
        }
        if(borrowList == null){
            borrowList = new HashMap<>();
        }
        if(returnList == null){
            returnList = new HashMap<>();
        }
        if(expenseList == null){
            expenseList = new HashMap<>();
        }

        if (session.getAttribute("user") == null) {
            response.sendRedirect("/login.do");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Admin Mode</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Administrator, Welcome!</h1>");
        out.println("<h2>You can see all stored data in this page!!</h2>");

        out.println("<hr>");
        out.println("<br>");
        out.println("<p>");
        out.println("<a href='logout.do'>Logout Admin Mode(Click Here)</a>");
        out.println("</p>");

        // Output all user
        out.println("<br>");
        out.println("<hr>");
        out.println("<h3>All registered User :</h3>");
        out.println("<h4>Name / Gender / Email / Username / Password</h4>");
        out.println("<p>");
        for (String key: userList.keySet()){
            UserInfo user = userList.get(key);
            String email = user.getEmail();
            String name = user.getName();
            String username = user.getAccountName();
            String password = user.getPassword();
            String gender = user.getGender();

            out.println( name +" / "+ gender +" / " + email +" / " + username +" / " + password);
            out.println("<br>");
        }
        out.println("</p>");


        // Output all borrow list
        out.println("<br>");
        out.println("<hr>");
        out.println("<h3>All Borrow List :</h3>");
        out.println("<h4>Name / Phone / Item / Date / Quantity / Project Name / Other Purpose</h4>");
        out.println("<p>");
        for (Integer key: borrowList.keySet()){
            BorrowInfo borrow = borrowList.get(key);

            String name = borrow.getName();
            String phone = borrow.getPhone();
            String item = borrow.getItem();
            String date = borrow.getDate();
            String quantity = borrow.getQuantity();
            String project_name = borrow.getProject_name();
            String other_purpose = borrow.getOther_purpose();
            out.println( name +" / "+ phone +" / " + item +" / " + date +" / " + quantity +" / " + project_name +" / " + other_purpose);
            out.println("<br>");
        }
        out.println("</p>");

        // Output all return list
        out.println("<br>");
        out.println("<hr>");
        out.println("<h3>All Return List :</h3>");
        out.println("<h4>Name / Phone / Item / Date / Quantity </h4>");
        out.println("<p>");
        for (Integer key: returnList.keySet()){
            ReturnInfo returnInfo = returnList.get(key);

            String name = returnInfo.getName();
            String phone = returnInfo.getPhone();
            String item = returnInfo.getItem();
            String date = returnInfo.getDate();
            String quantity = returnInfo.getQuantity();
            out.println( name +" / "+ phone +" / " + item +" / " + date +" / " + quantity);
            out.println("<br>");
        }
        out.println("</p>");

        // Output all borrow list
        out.println("<br>");
        out.println("<hr>");
        out.println("<h3>All Expense List :</h3>");
        out.println("<h4>Name / Phone / Item / Expense Name / Amount / Project Name / Other Purpose</h4>");
        out.println("<p>");
        for (Integer key: expenseList.keySet()){
            ExpenseInfo expense = expenseList.get(key);

            String name = expense.getName();
            String phone = expense.getPhone();
            String expense_name = expense.getExpense_name();
            String amount = expense.getAmount();
            String project_name = expense.getProject_name();
            String other_purpose = expense.getOther_purpose();
            out.println( name +" / "+ phone +" / " + expense_name +" / " + amount +" / " + project_name +" / " + other_purpose);
            out.println("<br>");
        }
        out.println("</p>");

        out.println("<br>");
        out.println("<hr>");
        out.println("<h3>----- End of All List -----</h3>");

        out.println("</body>");
        out.println("</html>");

        out.close();


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