package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.service.interfaces.IUserService;
import fr.simplon.domedespuys.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {
    private final IUserService userService;

    @GetMapping("/{id}")
    @JsonView({UserView.UserListView.class})
    public User getUserById(@PathVariable String id) {return userService.getById(id);}


}
