package com.resellerapp.model.DTOs;

import com.resellerapp.model.Offer;
import com.resellerapp.model.User;

import java.math.BigDecimal;

public class ViewDTO {
    private long id;

    private String description;

    private String condition;

    private BigDecimal price;

    private User seller;

    public ViewDTO() {
    }

    public ViewDTO(Offer offer) {
        this.id = offer.getId();
        this.description = offer.getDescription();
        this.condition = offer.getCondition().getConditionName().name();
        this.price = offer.getPrice();
        this.seller = offer.getSeller();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
