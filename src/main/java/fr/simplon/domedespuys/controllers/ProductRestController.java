package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.repository.ProductRepository;
import fr.simplon.domedespuys.view.ProductView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductRestController {
    private ProductRepository productRepository;

    @GetMapping
    @JsonView({ProductView.ProductListView.class})
    public List<Product> getProduct(){
        return productRepository.findAll();
    }


    @GetMapping("/{slug}")
    @JsonView({ProductView.ProductDetailView.class})
    public Optional<Product> getProductBySlug(@PathVariable String slug) {return productRepository.findBySlug(slug);}
}
