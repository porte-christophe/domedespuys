package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.ImageDTO;
import fr.simplon.domedespuys.entities.Image;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.repository.ImageRepository;
import fr.simplon.domedespuys.service.interfaces.IImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements IImageService {

    private ImageRepository imageRepository;
    @Override
    public boolean delete(String id) {
        try {
            Optional<Image> optionalImage = imageRepository.findById(Long.parseLong(id));
            Image image = optionalImage.orElseThrow();
            imageRepository.delete(image);
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    @Override
    public Image create(ImageDTO dto, Product product) {
        Image image = new Image();
        image.setProduct(product);
        image.setIsDefault(dto.isDefault);
        image.setPath(dto.path);
        image.setExtension("png");
        imageRepository.save(image);
        return image;
    }
}
