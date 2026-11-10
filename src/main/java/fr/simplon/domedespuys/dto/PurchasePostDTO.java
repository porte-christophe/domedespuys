package fr.simplon.domedespuys.dto;

import fr.simplon.domedespuys.entities.PurchaseProduct;

import java.util.List;

public class PurchasePostDTO {
    public AddressDTO address;
    public List<PurchaseProduct> purchaseProducts;
}
