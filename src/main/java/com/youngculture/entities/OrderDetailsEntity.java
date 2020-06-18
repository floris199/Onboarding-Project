package com.youngculture.entities;

import javax.persistence.*;

@Entity
@Table(name = "order_details", schema = "mydb")
public class OrderDetailsEntity {
    private int orderDetailsid;
    private int orderId;
    private int quantity;
    private ProductsEntity productsEntity;

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
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    public ProductsEntity getProductsEntity() { return productsEntity; }

    public void setProductsEntity(ProductsEntity productsEntity) { this.productsEntity = productsEntity; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailsEntity that = (OrderDetailsEntity) o;

        if( productsEntity.getId() != that.productsEntity.getId() ) return false;
        if (orderId != that.orderId) return false;
        if (quantity != that.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + quantity;
        return result;
    }
}
