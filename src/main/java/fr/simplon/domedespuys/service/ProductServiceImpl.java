package fr.simplon.domedespuys.service;

import fr.simplon.domedespuys.dto.ProductDTO;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.service.interfaces.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    @Override
    public List<Product> getCollection() {
        return List.of();
    }

    @Override
    public Product create(ProductDTO dto) {
        return null;
    }

    @Override
    public Product getById(String id) {
        return null;
    }

    @Override
    public Product update(String id, ProductDTO dto) {
        return null;
    }
}
