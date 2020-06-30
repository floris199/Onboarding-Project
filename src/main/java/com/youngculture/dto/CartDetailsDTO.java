package com.youngculture.dto;

import java.util.List;

public class CartDetailsDTO {

    private int cartDetailsId;
    private int cartId;
    private int quantity;
    private ProductDTO productDTO;

    public CartDetailsDTO() {
        this.cartDetailsId = 0;
        this.cartId = 0;
        this.quantity = 0;
        this.productDTO = new ProductDTO();
    }

    public int getCartDetailsId() {
        return cartDetailsId;
    }

    public void setCartDetailsId(int cartDetailsId) {
        this.cartDetailsId = cartDetailsId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTOs(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }
}
