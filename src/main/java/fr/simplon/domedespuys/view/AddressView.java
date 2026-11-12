package fr.simplon.domedespuys.view;

public class AddressView {
    public interface Line{}
    public interface ExtraLine{}
    public interface ZipCode{}
    public interface City{}
    public interface Country{}
    public interface Phone{}
    public interface FirstName{}
    public interface LastName{}


    public interface AddressDetailView extends Line, ExtraLine, ZipCode, City, Country{}
}
