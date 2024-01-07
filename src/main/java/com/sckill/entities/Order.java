package com.sckill.entities;

import com.sckill.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder(toBuilder = true)
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

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    OrderStatus status;

}
