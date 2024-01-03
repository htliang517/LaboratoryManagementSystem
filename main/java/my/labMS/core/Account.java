package my.labMS.core;

import java.util.HashMap;

/*
 * 此Java Class扮演MVC架構中Model的角色, 會讓Controller來呼叫使用
 */
public class Account{

    public static boolean checkAccountNameExistence(String inputAccountName, HashMap<String, UserInfo> hashMap) {

        // Check if hashMap is null
        if (hashMap == null) {
            return false;
        }
        // Check if there exists an account
        return hashMap.containsKey(inputAccountName);
    }

    public static boolean checkPassword(String inputAccountName, String inputPassword, HashMap<String, UserInfo> hashMap) {

        // 此hashMap物件為空值
        if (hashMap == null)
            return false;
        // 取得此使用者名稱所對應的使用者物件
        UserInfo user = hashMap.get(inputAccountName);

        // 取得使用者帳號所對應的密碼
        String passwordOnRecord = user.getPassword();

        return inputPassword.equals(passwordOnRecord);

    }

    public static boolean checkPasswordEmpty(String password){
        if (password == null){
            return true;
        }
        return password.isEmpty();
    }
}