package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.view.AddressView;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({AddressView.Id.class})
    private Long id;

    @Column(length = 100 ,nullable = false)
    @JsonView({AddressView.Line.class})
    private String line;

    @Column(length = 100)
    @JsonView({AddressView.ExtraLine.class})
    private String extraLine;

    @Column(length = 50 ,nullable = false)
    @JsonView({AddressView.City.class})
    private String city;

    @Column(length = 50 ,nullable = false)
    @JsonView({AddressView.ZipCode.class})
    private String zipCode;

    @Column(length = 40 ,nullable = false)
    @JsonView({AddressView.Country.class})
    private String country;

    @Column(length = 50)
    @JsonView({AddressView.Phone.class})
    private String phone;

    @Column(length = 100 ,nullable = false)
    @JsonView({AddressView.FirstName.class})
    private String firstname;

    @Column(length = 100 ,nullable = false)
    @JsonView({AddressView.LastName.class})
    private String lastname;

    @Column(length = 50, nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;


}

