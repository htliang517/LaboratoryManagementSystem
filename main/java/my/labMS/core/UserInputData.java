package my.labMS.core;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;

// Check is the user has really login
@WebServlet(urlPatterns = "/userInput.do")
public class UserInputData extends HttpServlet{
    private static final long serialVersionUID = 1L;
    int first = 0;
    int id_b = 0;
    int id_r = 0;
    int id_e = 0;
    HashMap<Integer, BorrowInfo> borrowList;
    HashMap<Integer, ReturnInfo> returnList;
    HashMap<Integer, ExpenseInfo> expenseList;
    ServletContext context;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        context = request.getServletContext();

        if(first == 0){
            HashMap<Integer, BorrowInfo> newBorrowList = new HashMap<>();
            context.setAttribute("borrowMap",newBorrowList);
            //System.out.println("Create newBorrowList");

            HashMap<Integer, ReturnInfo> newReturnList = new HashMap<>();
            context.setAttribute("returnMap",newReturnList);
            //System.out.println("Create newReturnList");

            HashMap<Integer, ExpenseInfo> newExpenseList = new HashMap<>();
            context.setAttribute("expenseMap",newExpenseList);
            //System.out.println("Create newExpenseList");

            first ++;
        }

        borrowList = (HashMap<Integer, BorrowInfo>) context.getAttribute("borrowMap");
        returnList = (HashMap<Integer, ReturnInfo>) context.getAttribute("returnMap");
        expenseList = (HashMap<Integer, ExpenseInfo>) context.getAttribute("expenseMap");

        String fromWhichPage = (String) request.getParameter("fromPage");

        //HttpSession session = request.getSession();
        //String current_user_name = (String) session.getAttribute("user");

        if(fromWhichPage.equals("B")){
            borrowList = (HashMap<Integer, BorrowInfo>) context.getAttribute("borrowMap");

            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String item = request.getParameter("item");
            String date = request.getParameter("date");
            String quantity = request.getParameter("quantity");
            String project_name = request.getParameter("project_name");
            String other_purpose = request.getParameter("other_purpose");

            BorrowInfo new_borrow = new BorrowInfo();
            new_borrow.setName(name);
            new_borrow.setPhone(phone);
            new_borrow.setItem(item);
            new_borrow.setDate(date);
            new_borrow.setQuantity(quantity);
            new_borrow.setProject_name(project_name);
            new_borrow.setOther_purpose(other_purpose);

            borrowList.put(id_b, new_borrow);
            id_b ++;
            response.sendRedirect("success_input.html");
            return;
        }
        else if (fromWhichPage.equals("R")){
            returnList = (HashMap<Integer, ReturnInfo>) context.getAttribute("returnMap");

            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String item = request.getParameter("item");
            String date = request.getParameter("date");
            String quantity = request.getParameter("quantity");

            ReturnInfo new_return = new ReturnInfo();
            new_return.setName(name);
            new_return.setPhone(phone);
            new_return.setItem(item);
            new_return.setDate(date);
            new_return.setQuantity(quantity);

            returnList.put(id_r, new_return);
            id_r ++;
            response.sendRedirect("success_input.html");
            return;
        }
        else if (fromWhichPage.equals("E")){
            expenseList = (HashMap<Integer, ExpenseInfo>) context.getAttribute("expenseMap");

            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String expense_name = request.getParameter("expense_name");
            String amount = request.getParameter("amount");
            String project_name = request.getParameter("project_name");
            String other_purpose = request.getParameter("other_purpose");

            ExpenseInfo new_expense = new ExpenseInfo();
            new_expense.setName(name);
            new_expense.setPhone(phone);
            new_expense.setExpense_name(expense_name);
            new_expense.setAmount(amount);
            new_expense.setProject_name(project_name);
            new_expense.setOther_purpose(other_purpose);

            expenseList.put(id_e, new_expense);
            id_e ++;
            response.sendRedirect("success_input.html");
            return;
        }
        else{
            //System.out.println("UserInputData did not get fromWhichPage!");
            response.sendRedirect("success_input.html");
            return;
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
