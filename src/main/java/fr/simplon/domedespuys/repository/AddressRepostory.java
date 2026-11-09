package fr.simplon.domedespuys.repository;

import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepostory extends JpaRepository<Address,Long> {

    List<Address> findAllByUser(User user);

}
