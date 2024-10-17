package org.cs3343.safepaws.ui.account;


import org.cs3343.safepaws.entity.Account;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;

import java.sql.SQLException;

public class Login extends UI{
	

    public boolean authenticate(Account account, String username, String password) {
        // 简单的用户名和密码匹配
        return account.getUsername().equals(username) && account.getPassword().equals(password);
    }

    // 创建新账户
    public void createAccount(String username, String password, String role) {
        Account account = new Account(username, password,role);
        try {
            DbManager.insertAccount(account);  // 将账户信息插入数据库
        } catch (SQLException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }
}
