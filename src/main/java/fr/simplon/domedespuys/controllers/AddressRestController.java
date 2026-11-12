package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.service.interfaces.IAddressService;
import fr.simplon.domedespuys.view.AddressView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/address")
public class AddressRestController {

    private IAddressService addressService;

    @PostMapping
    @JsonView({AddressView.AddressDetailView.class})
    public Address createAddress(@RequestBody AddressDTO addressDTO){return addressService.create(addressDTO);}

    @PutMapping("/{id}")
    @JsonView({AddressView.AddressDetailView.class})
    public Address updateAddress(@RequestBody AddressDTO addressDTO, @PathVariable String id){
        return addressService.update(id,addressDTO);
    }

    @DeleteMapping("/{id}")
    @JsonView({AddressView.AddressDetailView.class})
    public boolean deleteAddress(@PathVariable String id){
        return addressService.delete(id);
    }
}
