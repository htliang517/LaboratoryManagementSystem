package my.labMS.core;

import java.io.Serializable;

public class ExpenseInfo implements Serializable {
    private String name;
    private String phone;
    private String expense_name;
    private String amount;
    private String project_name;
    private String other_purpose;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getExpense_name() {
        return expense_name;
    }
    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }

    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProject_name() {
        return project_name;
    }
    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getOther_purpose() {
        return other_purpose;
    }
    public void setOther_purpose(String other_purpose) {
        this.other_purpose = other_purpose;
    }
}