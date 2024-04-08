package com.abhinav.productservicesprojectsst.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException{
    int id;
    public ProductNotFoundException(int id, String message) {
        super(message);
        this.id = id;
    }
}
