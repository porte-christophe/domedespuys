package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Purchase;
import fr.simplon.domedespuys.repository.PurchaseRepository;
import fr.simplon.domedespuys.view.PurchaseView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseRestController {
    private PurchaseRepository purchaseRepository;

    @GetMapping
    @JsonView({PurchaseView.PurchaseListView.class})
    public List<Purchase> getPurchase(){
        return purchaseRepository.findAll();
    }
}
