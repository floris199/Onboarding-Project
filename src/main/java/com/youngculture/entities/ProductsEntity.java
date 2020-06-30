package com.youngculture.entities;

import javax.persistence.*;

@Entity
@Table(name = "products", schema = "mydb")
public class ProductsEntity {
    private int productId;
    private String name;
    private String description;
    private CategoryEntity categoryEntity;
    private PricesEntity pricesEntity;

    @Id
    @Column(name = "ID")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(referencedColumnName = "PRODUCT_ID")
    public PricesEntity getPricesEntity() { return pricesEntity; }

    public void setPricesEntity( PricesEntity priceEntity ) { this.pricesEntity = priceEntity; }

    @OneToOne( cascade = CascadeType.ALL )
    @JoinTable( name = "PRODUCTS_CATEGORY",
        joinColumns = {@JoinColumn( name = "PRODUCT_ID", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn( name = "CATEGORY_ID", referencedColumnName = "id")})
    public CategoryEntity getCategoryEntity() { return categoryEntity; }

    public void setCategoryEntity( CategoryEntity categoryEntity) { this.categoryEntity = categoryEntity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (productId != that.productId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
