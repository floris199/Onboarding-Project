package com.youngculture.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_details", schema = "mydb")
public class OrderDetailsEntity {
    private int orderDetailsid;
    private int orderId;
    private String productName;
    private String productDescription;
    private int productPrice;
    private int quantity;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getOrderDetailsId() {
        return orderDetailsid;
    }

    public void setOrderDetailsId(int orderDetailsid) {
        this.orderDetailsid = orderDetailsid;
    }

    @Basic
    @Column(name = "ORDER_ID")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "PRODUCT_NAME")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "PRODUCT_DESCRIPTION")
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Basic
    @Column(name = "PRODUCT_PRICE")
    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsEntity that = (OrderDetailsEntity) o;

        if( !productName.equals( that.productName) ) return false;
        if( !productDescription.equals( that.productDescription) ) return false;
        if( productPrice != that.productPrice ) return false;
        if (orderId != that.orderId) return false;
        if (quantity != that.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + quantity;
        result = 31 * result + productPrice;
        return result;
    }
}
