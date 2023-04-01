package com.group30.major.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product implements Comparable<Product> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    private double price;
    private int stock;
    private String description;
    private String imageName;

    @Override
    public int compareTo(Product other) {
        // equal
        if (other.id == this.id){
            return 0;

        // less
        } else if (other.id < this.id) {
            return -1;

        //more
        } else {
            return 1;
        }
    }
}
