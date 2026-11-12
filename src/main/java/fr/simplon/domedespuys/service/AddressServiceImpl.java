package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.repository.AddressRepostory;
import fr.simplon.domedespuys.service.interfaces.IAddressService;
import fr.simplon.domedespuys.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private AddressRepostory addressRepository;

    private IUserService userService;
    @Override
    public Address create(AddressDTO dto) {
        Address address = new Address();
        dtoToObject(dto,address);

        address.setCreatedAt(LocalDateTime.now());

        User user = userService.getById("5");//user en dur
        address.setUser(user);

        addressRepository.saveAndFlush(address);
        return address;
    }

    @Override
    public boolean delete(String id) {
        try {
            addressRepository.deleteById(Long.parseLong(id));
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Address update(String id, AddressDTO dto) {
        try {
            Optional<Address> optionalAddress = addressRepository.findById(Long.parseLong(id));
            Address address = optionalAddress.orElseThrow();
            dtoToObject(dto,address);
            addressRepository.saveAndFlush(address);
            return address;
        } catch (NoSuchElementException e){
            throw new RuntimeException(e);
        }
    }


    public void dtoToObject(AddressDTO dto, Address address){
        address.setLine(dto.line);
        address.setExtraLine(dto.extraLine);
        address.setZipCode(dto.zipCode);
        address.setCity(dto.city);
        address.setCountry(dto.country);
        address.setFirstname(dto.firstName);
        address.setLastname(dto.lastName);

    }
}
