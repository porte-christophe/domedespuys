package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.service.interfaces.IProductService;
import fr.simplon.domedespuys.view.ProductView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductRestController {

    private final IProductService productService;

    @GetMapping
    @JsonView({ProductView.ProductListView.class})
    public List<Product> getProduct(){
        return productService.getCollection();
    }


    @GetMapping("/{id}")
    @JsonView({ProductView.ProductDetailView.class})
    public Product getProductById(@PathVariable String id) {return productService.getById(id);}
}
