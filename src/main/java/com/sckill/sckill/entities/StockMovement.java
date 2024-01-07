package com.sckill.sckill.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@Data
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

    @OneToOne
    Item item;

    @ManyToOne
    Order order;

}
