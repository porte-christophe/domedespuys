package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.repository.UserRepository;
import fr.simplon.domedespuys.view.BrandView;
import fr.simplon.domedespuys.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {
    private UserRepository userRepository;

    @GetMapping
    @JsonView({UserView.UserListView.class})
    public List<User> getUsers(){return userRepository.findAll();}

    @GetMapping("/{id}")
    @JsonView({UserView.UserListView.class})
    public Optional<User> getUserById(@PathVariable Long id) {return userRepository.findById(id);}


}
