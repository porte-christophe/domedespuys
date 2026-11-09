package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.view.ImageView;
import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ImageView.Id.class)
    private Long id;

    @Column(length = 50 ,nullable = false)
    @JsonView(ImageView.Path.class)
    private String path;

    @Column(length = 10, nullable = false)
    @JsonView(ImageView.Extension.class)
    private String extension;

    @Column(nullable = false)
    @JsonView(ImageView.IsDefault.class)
    private Boolean isDefault;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Product product;
}
