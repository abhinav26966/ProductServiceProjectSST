package com.abhinav.productservicesprojectsst.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    int id;
    String name;
    String description;
    double price;
    String image;
    Category category;
}
