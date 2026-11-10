package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.service.interfaces.IAddressService;

public class AddressServiceImpl implements IAddressService {
    @Override
    public Address create(AddressDTO dto) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public Address update(String id, AddressDTO dto) {
        return null;
    }
}
