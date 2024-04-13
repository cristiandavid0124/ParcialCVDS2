package co.edu.escuelaing.cvds.lab7.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "Product_ID")
    private int productId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Price")
    private int price;



    @Column(name = "Quantity")
    private int quantity;



    public enum Category {
        Clothes,
        Food,
        Technology,
        Drugstore
    }




}

