package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.service.interfaces.IBrandService;
import fr.simplon.domedespuys.view.BrandView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brand")
public class BrandRestController {

    private final IBrandService brandService;

    @GetMapping
    @JsonView({BrandView.BrandListView.class})
    public List<Brand> getBrand(){
        return brandService.getCollection();
    }


    @GetMapping("/{id}")
    @JsonView({BrandView.BrandDetailView.class})
    public Brand getBrandById(@PathVariable String id) {return brandService.getById(id);}
}
