package fr.simplon.domedespuys.view;

public class UserView {
    public interface Id{};
    public interface Email{};
    public interface Firstname{};
    public interface Lastname{};
    public interface Phone{};
    public interface CreatedAt{};
    public interface Addresses {};
    public interface Purchases{};


    public interface UserListView extends Id,Email,Firstname,Lastname,Phone{};
    public interface UserDetailView extends UserListView,CreatedAt,Addresses, AddressView.AddressDetailView {};

}
