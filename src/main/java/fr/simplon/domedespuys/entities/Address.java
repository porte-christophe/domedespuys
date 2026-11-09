package fr.simplon.domedespuys.entities;

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
    private Long id;

    @Column(length = 100 ,nullable = false)
    private String line;

    @Column(length = 100)
    private String extraLine;

    @Column(length = 50 ,nullable = false)
    private String city;

    @Column(length = 50 ,nullable = false)
    private String zipCode;

    @Column(length = 40 ,nullable = false)
    private String country;

    @Column(length = 50)
    private String phone;

    @Column(length = 100 ,nullable = false)
    private String firstname;

    @Column(length = 100 ,nullable = false)
    private String lastname;

    @Column(length = 50, nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;


}

