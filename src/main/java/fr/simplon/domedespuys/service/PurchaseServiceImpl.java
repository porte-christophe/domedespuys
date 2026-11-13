package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.PurchasePostDTO;
import fr.simplon.domedespuys.dto.PurchaseProductDTO;
import fr.simplon.domedespuys.dto.PurchasePutDTO;
import fr.simplon.domedespuys.entities.EnumPurchaseStatus;
import fr.simplon.domedespuys.entities.Purchase;
import fr.simplon.domedespuys.entities.PurchaseProduct;
import fr.simplon.domedespuys.entities.User;
import fr.simplon.domedespuys.repository.PurchaseRepository;
import fr.simplon.domedespuys.service.interfaces.IPurchaseProductService;
import fr.simplon.domedespuys.service.interfaces.IPurchaseService;
import fr.simplon.domedespuys.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements IPurchaseService {

    private PurchaseRepository purchaseRepository;
    private IUserService userService;
    private IPurchaseProductService purchaseProductService;

    @Override
    public List<Purchase> getCollection() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase create(PurchasePostDTO dto) {
        Purchase purchase = new Purchase();
        Random random = new Random();
        purchase.setNumber(String.valueOf((random.nextInt(0, 20000))));
        purchase.setStatus(EnumPurchaseStatus.PENDING.getValue());
        User user = userService.getById("5");
        purchase.setUser(user);
        purchase.setAddress(user.getAddresses().getFirst());
        purchaseRepository.saveAndFlush(purchase);
        for (PurchaseProductDTO purchaseProductDTO : dto.purchaseProducts){
            purchaseProductService.ICreatePurchaseProduct(purchase,purchaseProductDTO);
        }
        purchaseRepository.flush();
        return purchase;
    }

    @Override
    public Purchase getById(String id) {
        return purchaseRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public Purchase update(String id, PurchasePutDTO dto) {
        Purchase purchase = getById(id);
        purchase.setStatus(dto.status);
        purchaseRepository.saveAndFlush(purchase);
        return purchase;
    }
}
