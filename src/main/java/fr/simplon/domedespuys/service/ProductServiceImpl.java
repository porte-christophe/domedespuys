package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.ImageDTO;
import fr.simplon.domedespuys.dto.ProductDTO;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.repository.BrandRepository;
import fr.simplon.domedespuys.repository.ProductRepository;
import fr.simplon.domedespuys.service.interfaces.IImageService;
import fr.simplon.domedespuys.service.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;

    private IImageService imageService;
    @Override
    public List<Product> getCollection() {
        return productRepository.findAll();
    }

    @Override
    public Product create(ProductDTO dto) {
        Product product = new Product();
        dtoToObject(dto,product);
        product.setCreatedAt(LocalDateTime.now());
        productRepository.saveAndFlush(product);
        for (ImageDTO imageDTO : dto.images){
            imageService.create(imageDTO,product);
        }
        productRepository.flush();
        return product;
    }

    @Override
    public Product getById(String id) {
        return productRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public Product update(String id, ProductDTO dto) {
        Optional<Product> optionalProduct = productRepository.findById(Long.parseLong(id));
        Product product = optionalProduct.orElseThrow();
        dtoToObject(dto,product);
        productRepository.saveAndFlush(product);
        return product;
    }


    private void dtoToObject(ProductDTO dto , Product product){
        product.setLabel(dto.label);
        product.setDescription(dto.description);
        product.setPrice(dto.price);
        Optional<Brand> optionalBrand = brandRepository.findById(Long.parseLong(dto.brandId));
        Brand brand = optionalBrand.orElseThrow();
        product.setBrand(brand);
    }
}
