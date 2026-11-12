package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.dto.ProductDTO;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.service.interfaces.IProductService;
import fr.simplon.domedespuys.view.ProductView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @JsonView({ProductView.ProductListView.class})
    public Product createProduct(@RequestBody ProductDTO productDTO){return productService.create(productDTO);}

    @PutMapping("/{id}")
    @JsonView({ProductView.ProductDetailView.class})
    public Product updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) {return productService.update(id, productDTO);}

}
