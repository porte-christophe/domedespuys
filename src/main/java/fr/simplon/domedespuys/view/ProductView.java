package fr.simplon.domedespuys.view;

public class ProductView {
    public interface Id{};
    public interface Label{};
    public interface Description{};
    public interface Price{};
    public interface Slug{};
    public interface CreatedAt{};
    public interface Images{};
    public interface Brand{};


    public interface ProductListView extends
            Label,
            Description,
            Price,
            Slug{};

    public interface ProductDetailView extends
            Label,
            Description,
            Price,
            Slug,
            Images,
            ImageView.Id,
            ImageView.Path,
            ImageView.Extension,
            Brand,
            BrandView.BrandListView{};

}
