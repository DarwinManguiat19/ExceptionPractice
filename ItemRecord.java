/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author User
 */
public class ItemRecord {
    private final String recordId;
    private final String name;
    private final int quantity;
 
    public ItemRecord(String recordId, String name, int quantity) {
        if (recordId == null || recordId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "record ID must not be blank.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "name must not be blank.");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException(
                    "quantity must be 1 or higher. You entered "
                    + quantity + ".");
        }
 
        this.recordId = recordId.trim();
        this.name = name.trim();
        this.quantity = quantity;
    }
 
    public String getRecordId() {
        return recordId;
    }
 
    public String getName() {
        return name;
    }
 
    public int getQuantity() {
        return quantity;
    }

}
