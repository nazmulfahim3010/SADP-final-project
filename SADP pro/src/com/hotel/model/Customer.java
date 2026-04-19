package com.hotel.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    private String customerId, name, phone, email;

    public Customer(String customerId, String name, String phone, String email) {
        this.customerId = customerId; this.name = name; this.phone = phone; this.email = email;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    @Override public String toString() { return name + " (" + customerId + ")"; }
}
