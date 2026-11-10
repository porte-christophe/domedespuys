package fr.simplon.domedespuys.service.interfaces;

import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.entities.Address;

public interface IAddressService extends
        ICreate<Address,AddressDTO>,
        IUpdate<Address, AddressDTO>,
        IDelete
{}
