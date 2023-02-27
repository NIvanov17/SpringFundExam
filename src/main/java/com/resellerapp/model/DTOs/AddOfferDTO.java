package com.resellerapp.model.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddOfferDTO {

    @Size(min = 2,max = 50)
    private String description;

    @Positive()
    private BigDecimal price;

    @NotBlank()
    private String condition;

    public AddOfferDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "AddOfferDTO{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", condition='" + condition + '\'' +
                '}';
    }
}
