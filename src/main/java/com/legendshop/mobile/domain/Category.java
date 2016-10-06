package com.legendshop.mobile.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The Category entity.                                                        
 * 
 */
@ApiModel(description = ""
    + "The Category entity.                                                   "
    + "")
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "icon")
    private String icon;

    @Column(name = "pic")
    private String pic;

    @OneToMany(mappedBy = "parentCategory")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Category> subCategories = new HashSet<>();

    @ManyToOne
    private Category parentCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Category name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public Category icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPic() {
        return pic;
    }

    public Category pic(String pic) {
        this.pic = pic;
        return this;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }

    public Category subCategories(Set<Category> categories) {
        this.subCategories = categories;
        return this;
    }

    public Category addSubCategory(Category category) {
        subCategories.add(category);
        category.setParentCategory(this);
        return this;
    }

    public Category removeSubCategory(Category category) {
        subCategories.remove(category);
        category.setParentCategory(null);
        return this;
    }

    public void setSubCategories(Set<Category> categories) {
        this.subCategories = categories;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public Category parentCategory(Category category) {
        this.parentCategory = category;
        return this;
    }

    public void setParentCategory(Category category) {
        this.parentCategory = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        if(category.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", icon='" + icon + "'" +
            ", pic='" + pic + "'" +
            '}';
    }
}