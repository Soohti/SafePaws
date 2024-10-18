package org.cs3343.safepaws.entity;

public class Account {
    private String username;
    private String password;
    private String role;

    
    public static String encryptPassword(String password) {
        // TODO: 在此实现密码加密算法
        return password; // 这里返回原始密码，替换为加密后的密码
    }
    
    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
