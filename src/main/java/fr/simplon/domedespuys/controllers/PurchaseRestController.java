package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Purchase;
import fr.simplon.domedespuys.service.PurchaseServiceImpl;
import fr.simplon.domedespuys.view.PurchaseView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseRestController {
    private PurchaseServiceImpl purchaseService;

    @GetMapping
    @JsonView({PurchaseView.PurchaseListView.class})
    public List<Purchase> getPurchase(){return purchaseService.getCollection();}

    @GetMapping("/{id}")
    @JsonView({PurchaseView.PurchaseListView.class})
    public Purchase getPurchaseById(@PathVariable String id){return purchaseService.getById(id);}
}
