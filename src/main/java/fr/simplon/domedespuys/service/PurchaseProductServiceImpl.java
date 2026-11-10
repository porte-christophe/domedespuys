package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.PurchaseProductDTO;
import fr.simplon.domedespuys.entities.Purchase;
import fr.simplon.domedespuys.entities.PurchaseProduct;
import fr.simplon.domedespuys.service.interfaces.IPurchaseProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PurchaseProductServiceImpl implements IPurchaseProductService {
    @Override
    public PurchaseProduct ICreatePurchaseProduct(Purchase purchase, PurchaseProductDTO purchaseProductDTO) {
        return null;
    }
}
