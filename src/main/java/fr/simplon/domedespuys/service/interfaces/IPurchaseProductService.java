package fr.simplon.domedespuys.service.interfaces;

import fr.simplon.domedespuys.dto.PurchaseProductDTO;
import fr.simplon.domedespuys.entities.Purchase;
import fr.simplon.domedespuys.entities.PurchaseProduct;

public interface IPurchaseProductService {
    PurchaseProduct ICreatePurchaseProduct(Purchase purchase, PurchaseProductDTO purchaseProductDTO);
}
