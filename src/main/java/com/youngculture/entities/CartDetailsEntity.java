package com.youngculture.entities;

import javax.persistence.*;

@Entity
@Table(name = "cart_details", schema = "mydb")

public class CartDetailsEntity {
    private Integer cartDetailsid;
    private Integer quantity;
    private Integer cartId;
    private ProductsEntity productsEntity;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    public Integer getCartDetailsId() {
        return cartDetailsid;
    }

    public void setCartDetailsId(int cartDetailsid) {
        this.cartDetailsid = cartDetailsid;
    }

    @Basic
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

        if (cartDetailsid != that.cartDetailsid) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if( productsEntity.getProductId() !=  that.getProductsEntity().getProductId() ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
