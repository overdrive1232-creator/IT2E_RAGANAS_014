package com.yourdomain.model;

public record ScenarioEntity(
        int id,
        String customerName,
        double weightKg,
        double totalAmount,
        String status
) {}