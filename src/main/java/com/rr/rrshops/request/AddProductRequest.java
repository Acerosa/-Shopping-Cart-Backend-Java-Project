package com.rr.rrshops.request;

import com.rr.rrshops.model.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class AddProductRequest {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private int inventory;

    private Category category;
}
