package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.view.BrandView;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(BrandView.Id.class)
    private Long id;


    @Column(length = 64, nullable = false)
    @JsonView(BrandView.Label.class)
    private String label;

    @Column(length = 100)
    @JsonView(BrandView.Logo.class)
    private String logo;

    @Column(nullable = false, unique = true)
    @JsonView(BrandView.Slug.class)
    private String slug;

    @OneToMany(mappedBy = "brand")
    @JsonView(BrandView.Products.class)
    private List<Product> products;
}
