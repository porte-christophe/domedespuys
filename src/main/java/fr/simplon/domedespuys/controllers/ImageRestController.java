package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.dto.ImageDTO;
import fr.simplon.domedespuys.entities.Image;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.service.interfaces.IImageService;
import fr.simplon.domedespuys.view.ImageView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/image")
public class ImageRestController {

    private IImageService imageService;

    @PostMapping
    @JsonView(ImageView.class)
    public Image createImage(@RequestBody ImageDTO imageDTO, @RequestBody Product product){
        return imageService.create(imageDTO,product);
    }

    @DeleteMapping("/{id}")
    @JsonView(ImageView.class)
    public boolean deleteImage(@PathVariable String id){
        return imageService.delete(id);
    }
}
