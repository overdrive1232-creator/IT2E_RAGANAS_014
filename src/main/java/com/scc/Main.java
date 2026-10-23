package com.yourdomain;

import com.yourdomain.dao.DatabaseConnection;
import com.yourdomain.ui.ScenarioMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize SQLite database and table structure
        DatabaseConnection.initializeDatabase();

        // Launch interactive console menu for LaundryJobSystem
        ScenarioMenu menu = new ScenarioMenu();
        menu.start();
    }
}