package com.ganesh.AOP.services;

public interface ShipmentService {
    String orderPackage(Long orderId);
    String trackPackage(Long orderId);
}
