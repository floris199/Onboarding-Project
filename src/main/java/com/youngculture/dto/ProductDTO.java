package com.youngculture.dto;

import com.youngculture.constants.Constants;

public class ProductDTO {

    private int productId;
    private String name;
    private String description;
    private int price;
    private int categoryId;
    private String categoryDescription;

    public ProductDTO()
    {
        this.productId = 0;
        this.name = Constants.EMPTY;
        this.description = Constants.EMPTY;
        this.price = 0;
    }

    public ProductDTO(int productId, String name, String description, int price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
