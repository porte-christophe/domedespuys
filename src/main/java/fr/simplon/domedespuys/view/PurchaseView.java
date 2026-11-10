package fr.simplon.domedespuys.view;

public class PurchaseView {
    public interface Id{};
    public interface Number{};
    public interface CreatedAt{};
    public interface Status{};
    public interface User{};
    public interface Address{};
    public interface PurchaseProducts{};

    public interface PurchaseListView extends
            Number,
            CreatedAt,
            User,
            UserView.UserListView,
            Address,
            PurchaseProducts{};
}
