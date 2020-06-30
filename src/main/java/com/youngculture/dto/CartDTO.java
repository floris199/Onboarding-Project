package com.youngculture.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private int id;
    private int userId;
    private List<CartDetailsDTO> cartDetailsDTOs;

    public CartDTO()
    {
        this.userId = 0;
        this.id = 0;
        this.cartDetailsDTOs = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int cartId) {
        this.id = cartId;
    }

    public List<CartDetailsDTO> getCartDetailsDTOs() {
        return cartDetailsDTOs;
    }

    public void setCartDetailsDTOs(List<CartDetailsDTO> cartDetailsDTOs) {
        this.cartDetailsDTOs = cartDetailsDTOs;
    }
}
