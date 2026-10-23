package com.yourdomain.dao;

import com.yourdomain.model.ScenarioEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public void addRecord(ScenarioEntity entity) {
        String sql = "INSERT INTO laundry_orders (customer_name, weight_kg, total_amount, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, entity.customerName());
            pstmt.setDouble(2, entity.weightKg());
            pstmt.setDouble(3, entity.totalAmount());
            pstmt.setString(4, entity.status());
            pstmt.executeUpdate();
            System.out.println("\nSUCCESS: Laundry job order created!");
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to insert record: " + e.getMessage());
        }
    }

    public List<ScenarioEntity> getAllRecords() {
        List<ScenarioEntity> records = new ArrayList<>();
        String sql = "SELECT id, customer_name, weight_kg, total_amount, status FROM laundry_orders";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                records.add(new ScenarioEntity(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getDouble("weight_kg"),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Failed to retrieve records: " + e.getMessage());
        }
        return records;
    }
}