package com.sckill.sckill.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
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

    @ManyToOne
    Item item;

    @ManyToOne
    Order order;

}
