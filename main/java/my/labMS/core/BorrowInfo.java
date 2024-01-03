package my.labMS.core;

import java.io.Serializable;

public class BorrowInfo implements Serializable {
    private String name;
    private String phone;
    private String item;
    private String date;
    private String quantity;
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

    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
