package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.view.ProductView;
import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50 ,nullable = false)
    @JsonView(ProductView.Label.class)
    private String label;

    @Column(columnDefinition="TEXT")
    @JsonView(ProductView.Description.class)
    private String description;

    @Column(nullable = false)
    @JsonView(ProductView.Price.class)
    private Integer price;

    @Column(nullable = false, unique = true)
    @JsonView(ProductView.Slug.class)
    private String slug;

    @Column(length = 50, nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product")
    @JsonView(ProductView.Images.class)
    private List<Image> images;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(ProductView.Brand.class)
    private Brand brand;
}
