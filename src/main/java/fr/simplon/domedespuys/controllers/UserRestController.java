package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.dto.UserPostDTO;
import fr.simplon.domedespuys.dto.UserPutDTO;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.service.interfaces.IUserService;
import fr.simplon.domedespuys.view.UserView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {
    private final IUserService userService;

    @GetMapping("/{id}")
    @JsonView({UserView.UserListView.class})
    public User getUserById(@PathVariable String id) {return userService.getById(id);}

    @PostMapping
    @JsonView({UserView.UserListView.class})
    public User createUser(@RequestBody UserPostDTO userPostDTO) {return userService.create(userPostDTO);}

    @PutMapping("/{id}")
    @JsonView({UserView.UserListView.class})
    public User updateUser(@PathVariable String id, @RequestBody UserPutDTO userPutDTO){return userService.update(id,userPutDTO);}

    @DeleteMapping("/{id}")
    @JsonView({UserView.UserListView.class})
    public boolean deleteUser(@PathVariable String id){return userService.delete(id);}
}
