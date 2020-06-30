package com.youngculture.dto;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class OrderDTO {

    private int id;
    private Set<OrderDetailsDTO> products;
    private Timestamp createdAt;

    public OrderDTO()
    {
        this.id = 0;
        this.products = new HashSet<OrderDetailsDTO>();
        this.createdAt = new Timestamp( 0 );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<OrderDetailsDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<OrderDetailsDTO> products) {
        this.products = products;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
