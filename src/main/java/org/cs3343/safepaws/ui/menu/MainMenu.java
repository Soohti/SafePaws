package org.cs3343.safepaws.ui.menu;

import org.cs3343.safepaws.ui.TestDb;
import org.cs3343.safepaws.ui.UI;
import org.cs3343.safepaws.util.DbManager;
import org.cs3343.safepaws.util.Session;
import org.cs3343.safepaws.entity.Account;


public class MainMenu extends Menu {
    private static final String name = "Main Menu";
    private static final String description = "Welcome to SafePaws!";
    private final UI[] menuItems = new UI[]{
            new TestMenu(this),
            new TestDb(this),
    };

    public MainMenu() {
        super(name, description, null);
        setMenuItems(menuItems);
    }

    @Override
    public boolean isVisibleTo(Session session) {
        return true;
    }
    
    @Override
    public UI getNextUI(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Create Shelter Account");
        System.out.println("2. Create Member Account");
        System.out.println("3. Login");
        System.out.println("4. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符

        switch (choice) {
            case 1:
                createAccount(scanner, "shelter");
                break;
            case 2:
                createAccount(scanner, "member");
                break;
            case 3:
                login(scanner);
                break;
            case 4:
                return null; // 退出程序
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return this; // 返回当前菜单
    }

    

    private void login(Scanner scanner) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        // 实现登录逻辑
        try {
            // 这里需要验证用户名和密码，确保角色为 shelter 或 member
            boolean isAuthenticated = DbManager.authenticateUser(username, password);
            if (isAuthenticated) {
                System.out.println("Login successful!");
                // 根据角色跳转到不同的界面或功能
            } else {
                System.out.println("Login failed. Please check your credentials.");
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }
}
