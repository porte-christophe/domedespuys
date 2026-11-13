package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.view.UserView;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UserView.Id.class)
    private Long id;

    @Column(length = 100 ,nullable = false)
    @JsonView(UserView.Email.class)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 100 ,nullable = false)
    @JsonView(UserView.Firstname.class)
    private String firstname;

    @Column(length = 100 ,nullable = false)
    @JsonView(UserView.Lastname.class)
    private String lastname;

    @Column(length = 50)
    @JsonView(UserView.Phone.class)
    private String phone;

    @Column(length = 50, nullable = false)
    @JsonView(UserView.CreatedAt.class)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    @JsonView(UserView.Addresses.class)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @JsonView(UserView.Purchases.class)
    private List<Purchase> purchases;
}
