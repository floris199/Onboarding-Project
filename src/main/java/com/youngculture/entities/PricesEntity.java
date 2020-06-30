package com.youngculture.entities;

import javax.persistence.*;

@Entity
@Table(name = "products_prices", schema = "mydb")
public class PricesEntity {
    @Id
    @Column(name = "PRODUCT_ID")
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "PRICE")
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PricesEntity that = (PricesEntity) o;

        if (price != that.price) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return price;
    }
}
