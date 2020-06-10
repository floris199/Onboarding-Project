package com.youngculture.entities;

import javax.persistence.*;

@Entity
@Table(name = "category", schema = "mydb")
public class CategoryEntity {
    private int id;
    private String description;
    private ProductsEntity productsEntity;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne( mappedBy = "categoryEntity", fetch=FetchType.LAZY )
    public ProductsEntity getProductsEntity() { return productsEntity; }

    public void setProductsEntity( ProductsEntity productsEntity ) { this.productsEntity = productsEntity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
