package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.UserPostDTO;
import fr.simplon.domedespuys.dto.UserPutDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.repository.UserRepository;
import fr.simplon.domedespuys.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    @Override
    public User create(UserPostDTO dto) {
        User user = new User();
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setFirstname(dto.firstname);
        user.setLastname(dto.lastname);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.saveAndFlush(user);
        return user;
    }


    @Override
    public boolean delete(String id) {
        User user = getById(id);

        user.setEmail("toto@toto.de");
        user.setFirstname("firstname");
        user.setLastname("lastname");
        user.setPhone("0000000000");

        List<Address> addresses = new ArrayList<>();
        user.setAddresses(addresses);
        return true;
    }

    @Override
    public User getById(String id) {
        return userRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public User update(String id, UserPutDTO dto) {
        User user = getById(id);

        user.setFirstname(dto.firstname);
        user.setLastname(dto.lastname);
        user.setPhone(dto.phone);

        userRepository.saveAndFlush(user);

        return user;
    }
}
