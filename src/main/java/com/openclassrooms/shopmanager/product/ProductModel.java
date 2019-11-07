package com.openclassrooms.shopmanager.product;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductModel {

    private Long id;
    @NotBlank(message = "Product must have a name")
    private String name;            // Required
    private String description;
    private String details;
    @NotNull(message = "Quantity is required")
    @Min(value=1)
    private String  quantity;       // Required, Integer, Greater than zero
    @NotNull(message = "The price is required")
    @DecimalMin(value ="0.1",inclusive = true, message="The price must be a decimal value and greater than zero")
    private String  price;          // Required, Numeric, Greater than zero

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
