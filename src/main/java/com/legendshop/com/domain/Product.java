package com.legendshop.com.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * The Product entity.                                                         
 * 
 */
@ApiModel(description = ""
    + "The Product entity.                                                    "
    + "")
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "icon")
    private String icon;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "sale_price", nullable = false)
    private Float salePrice;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "market_price", nullable = false)
    private Float marketPrice;

    @Column(name = "origin_price")
    private Float originPrice;

    @Column(name = "discount")
    private Float discount;

    @Column(name = "sale_count")
    private Integer saleCount;

    @Column(name = "is_new")
    private Boolean isNew;

    @Column(name = "good_remark")
    private Integer goodRemark;

    @Column(name = "comment_count")
    private Integer commentCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Float salePrice) {
        this.salePrice = salePrice;
    }

    public Float getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Float marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Float getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Float originPrice) {
        this.originPrice = originPrice;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Integer getGoodRemark() {
        return goodRemark;
    }

    public void setGoodRemark(Integer goodRemark) {
        this.goodRemark = goodRemark;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        if(product.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", icon='" + icon + "'" +
            ", salePrice='" + salePrice + "'" +
            ", marketPrice='" + marketPrice + "'" +
            ", originPrice='" + originPrice + "'" +
            ", discount='" + discount + "'" +
            ", saleCount='" + saleCount + "'" +
            ", isNew='" + isNew + "'" +
            ", goodRemark='" + goodRemark + "'" +
            ", commentCount='" + commentCount + "'" +
            '}';
    }
}
