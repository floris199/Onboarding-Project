package com.youngculture.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart_details", schema = "mydb")
public class CartDetailsEntity {
    private int cartId;
    private Integer quantity;
    private ProductsEntity productsEntity;

    @Id
    @Column(name = "CART_ID")
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Basic
    @Column(name = "QUANTITY")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ManyToOne( fetch=FetchType.EAGER )
    @JoinColumn(name="PRODUCT_ID", referencedColumnName = "ID")
    public ProductsEntity getProductsEntity() { return productsEntity; }

    public void setProductsEntity(ProductsEntity productsEntity) { this.productsEntity = productsEntity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartDetailsEntity that = (CartDetailsEntity) o;

        if (cartId != that.cartId) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cartId;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
