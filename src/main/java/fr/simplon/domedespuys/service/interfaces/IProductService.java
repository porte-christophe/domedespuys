package fr.simplon.domedespuys.service.interfaces;

import fr.simplon.domedespuys.dto.ProductDTO;
import fr.simplon.domedespuys.entities.Product;

public interface IProductService extends
        ICreate<Product, ProductDTO>,
        IUpdate<Product, ProductDTO>,
        IGetItem<Product>,
        ICollection<Product>
{}
