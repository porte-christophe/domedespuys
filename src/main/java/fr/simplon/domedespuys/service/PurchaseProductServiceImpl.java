package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.PurchaseProductDTO;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.entities.Purchase;
import fr.simplon.domedespuys.entities.PurchaseProduct;
import fr.simplon.domedespuys.repository.PurchaseProductRepository;
import fr.simplon.domedespuys.service.interfaces.IProductService;
import fr.simplon.domedespuys.service.interfaces.IPurchaseProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PurchaseProductServiceImpl implements IPurchaseProductService {

    private PurchaseProductRepository purchaseProductRepository;
    private IProductService productService;

    @Override
    public PurchaseProduct ICreatePurchaseProduct(Purchase purchase, PurchaseProductDTO purchaseProductDTO) {
        PurchaseProduct purchaseProduct = new PurchaseProduct();
        purchaseProduct.setQuantity(purchaseProductDTO.qty);
        purchaseProduct.setPrice(purchaseProductDTO.price);
        purchaseProduct.setPurchase(purchase);
        Product product = productService.getById(purchaseProductDTO.idProduct);
        purchaseProduct.setProduct(product);
        purchaseProductRepository.saveAndFlush(purchaseProduct);
        return purchaseProduct;
    }
}
