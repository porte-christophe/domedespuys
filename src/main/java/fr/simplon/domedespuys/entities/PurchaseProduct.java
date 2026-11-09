package fr.simplon.domedespuys.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchaseProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Purchase purchase;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Product product;
}
