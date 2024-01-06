package com.sckill.sckill.entities;

import com.sckill.sckill.entities.enums.OrderSituation;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name = "CREATION_DATE")
    LocalDateTime creationDate;

    @OneToMany(mappedBy = "order")
    List<StockMovement> stockMovements;

    @ManyToOne(optional = false)
    User user;

    @Column(name = "SITUATION", nullable = false)
    @Enumerated(EnumType.STRING)
    OrderSituation situation;

}
