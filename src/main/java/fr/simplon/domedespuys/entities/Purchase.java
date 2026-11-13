package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.view.PurchaseView;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//@Table(name = "purchase"):pour changer le nim par default
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(PurchaseView.Id.class)
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    @JsonView(PurchaseView.Number.class)
    private String number;

    @Column(nullable = false)
    @JsonView(PurchaseView.CreatedAt.class)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @JsonView(PurchaseView.Status.class)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(PurchaseView.User.class)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(PurchaseView.Address.class)
    private Address address;

    @OneToMany(mappedBy = "purchase")
    @JsonView(PurchaseView.PurchaseProducts.class)
    private List<PurchaseProduct> purchaseProducts;

}