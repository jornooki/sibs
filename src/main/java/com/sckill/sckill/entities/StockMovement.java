package com.sckill.sckill.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "STOCK_MOVEMENTS")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "CREATION_DATE")
    LocalDateTime creationDate;

    @Column(name = "QUANTITY")
    int quantity;

    @OneToOne
    Item item;

    @ManyToOne
    Order order;

}
