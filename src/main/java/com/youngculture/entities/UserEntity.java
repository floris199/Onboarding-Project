package com.youngculture.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users", schema = "mydb")
public class UserEntity {
    private int id;
    private String username;
    private String passwrd;
    private Set<OrdersEntity> ordersEntity;
    private CartEntity cartEntity;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USERNAME")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWRD")
    public String getPasswrd() {
        return passwrd;
    }

    public void setPasswrd(String passwrd) {
        this.passwrd = passwrd;
    }

    @OneToMany( cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn( name="USER_ID" )
    public Set<OrdersEntity> getOrdersEntity( ){ return ordersEntity; }

    public void setOrdersEntity( Set<OrdersEntity> ordersEntity ) { this.ordersEntity = ordersEntity; }

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn( name = "ID", referencedColumnName = "USER_ID" )
    public CartEntity getCartEntity( ){ return cartEntity; }

    public void setCartEntity( CartEntity cartEntity ) { this.cartEntity = cartEntity; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (passwrd != null ? !passwrd.equals(that.passwrd) : that.passwrd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (passwrd != null ? passwrd.hashCode() : 0);
        return result;
    }
}
