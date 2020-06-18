package com.youngculture.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart", schema = "mydb")
public class CartEntity {
    private int id;
    private int userId;
    private List<CartDetailsEntity> cartDetailsEntities;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn( name="CART_ID" )
    public List<CartDetailsEntity> getCartDetails( ){ return cartDetailsEntities; }

    public void setCartDetails( List<CartDetailsEntity> cartDetailsEntities ) { this.cartDetailsEntities = cartDetailsEntities; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartEntity that = (CartEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        return result;
    }
}
