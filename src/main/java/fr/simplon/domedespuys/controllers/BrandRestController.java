package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.repository.BrandRepository;
import fr.simplon.domedespuys.view.BrandView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brand")
public class BrandRestController {

    private BrandRepository brandRepository;

    @GetMapping
    @JsonView({BrandView.BrandListView.class})
    public List<Brand> getBrand(){
        return brandRepository.findAll();
    }


    @GetMapping("/{slug}")
    @JsonView({BrandView.BrandDetailView.class})
    public Optional<Brand> getBrandBySlug(@PathVariable String slug) {return brandRepository.findBySlug(slug);}
}
