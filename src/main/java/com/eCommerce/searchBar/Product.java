package com.eCommerce.searchBar;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "product")
public class Product {

    @Id
    @Getter
    @Setter
    private String id;

    @Field(type = FieldType.Text, name = "name")
    @Getter
    @Setter
    private String name;

    @Field(type = FieldType.Text, name = "description")
    @Getter
    @Setter
    private String description;

    @Field(type = FieldType.Double, name = "price")
    @Getter
    @Setter
    private double price;
}
