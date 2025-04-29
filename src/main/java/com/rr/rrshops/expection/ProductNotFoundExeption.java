package com.rr.rrshops.expection;

public class ProductNotFoundExeption extends RuntimeException {
    public ProductNotFoundExeption(String productNotFound) {
        super(productNotFound);
    }
}
