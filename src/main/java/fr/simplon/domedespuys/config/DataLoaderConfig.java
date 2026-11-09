package fr.simplon.domedespuys.config;

import com.github.javafaker.Faker;
import fr.simplon.domedespuys.entities.*;
import fr.simplon.domedespuys.repository.*;
import fr.simplon.domedespuys.utils.Slugger;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
@AllArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private BrandRepository brandRepository;
    private AddressRepostory addressRepository;
    private ProductRepository productRepository;
    private ImageRepository imageRepository;
    private PurchaseRepository purchaseRepository;
    private PurchaseProductRepository purchaseProductRepository;

    private final int NB_USER = 10;

    private Slugger slugger;

    @Override
    public void run(String @NonNull ... args){
        createUsers();
        createBrands();
        createAddresses();
        createProduct();
        createImages();
        createPurchase();
        createPurchaseProduct();
    }

    private void createUsers(){
        if (0 == userRepository.count()){
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < NB_USER; i++) {
                User user = new User();
                user.setFirstname(faker.name().firstName());
                user.setLastname(faker.name().lastName());
                String email = faker.internet().emailAddress(user.getFirstname().toLowerCase(Locale.ROOT) + user.getLastname().toLowerCase(Locale.ROOT));
                user.setEmail(email);
                user.setPassword(faker.internet().password(12,20));
                if (hasProbability(2,3)){
                    user.setPhone(faker.phoneNumber().phoneNumber());
                }
                LocalDateTime localDateTime = getRandomDate(faker,0,5);
                user.setCreatedAt(localDateTime);
                userRepository.save(user);
            }
            userRepository.flush();
        }

    }


    private void createBrands(){
        if (0 == brandRepository.count()){
            Faker faker = new Faker();

            for (int i = 0; i < 30; i++) {
                Brand brand = new Brand();
                brand.setLabel(faker.company().name());
                brand.setLogo(faker.company().logo());
                brand.setSlug(slugger.slugify(i + "-" + brand.getLabel()));
                brandRepository.save(brand);
            }
            brandRepository.flush();
        }
    }

    private void createAddresses() {
        if (0 == addressRepository.count()){
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 20; i++) {
                Address address = new Address();
                address.setLine(faker.address().streetName());
                address.setCity(faker.address().city());
                address.setZipCode(faker.address().zipCode());
                address.setCountry("France");
                LocalDateTime localDateTime = getRandomDate(faker,0,5);
                address.setCreatedAt(localDateTime);

                if (hasProbability(1,4)){
                    address.setExtraLine(faker.address().buildingNumber());
                }

                Random random = new Random();
                Long id = random.nextLong(1, NB_USER);
                Optional<User> optionalUser = userRepository.findById(id);
                optionalUser.ifPresent(address::setUser);

                if (hasProbability(1,2)){
                    address.setPhone(address.getUser().getPhone());
                    address.setFirstname(address.getUser().getFirstname());
                    address.setLastname(address.getUser().getLastname());
                } else {
                    address.setPhone(faker.phoneNumber().phoneNumber());
                    address.setFirstname(faker.name().firstName());
                    address.setLastname(faker.name().lastName());
                }

                addressRepository.save(address);
            }
            addressRepository.flush();
        }
    }
    public void createProduct(){
        if (0 == productRepository.count()){
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 50; i++) {
                Product product = new Product();
                Random randomPrice = new Random();
                product.setLabel(faker.commerce().productName());
                product.setPrice(randomPrice.nextInt(500, 250000));
                product.setDescription(faker.lorem().paragraph());
                product.setSlug(slugger.slugify(i + "-" + product.getLabel()));
                LocalDateTime localDateTime = getRandomDate(faker,0,5);
                product.setCreatedAt(localDateTime);

                Random random = new Random();
                Long id = random.nextLong(1,30);
                Optional<Brand> optionalBrand = brandRepository.findById(id);
                Brand brand = optionalBrand.orElseThrow();
                product.setBrand(brand);
                productRepository.save(product);
            }
            productRepository.flush();
        }
    }

    public void createImages() {
        if (0 == imageRepository.count()) {
            List<Product> products = productRepository.findAll();
            for (Product product : products) {
                createImagesByProduct(product, true);

                do {
                    createImagesByProduct(product, false);
                } while(hasProbability(1, 100));
            }

            imageRepository.flush();
        }
    }

    public void createPurchase(){
        if(0 == purchaseRepository.count()){
            Faker faker = new Faker(Locale.of("fr"));

            for (int i = 0; i < 50; i++) {
                Purchase purchase = new Purchase();
                String tmpNumber;
                if (i < 10) {
                    tmpNumber = "0000000000" + i;
                } else {
                    tmpNumber = "000000000" + i;
                }
                String number = "PUR-" + LocalDateTime.now().getYear() + "-" + tmpNumber;
                purchase.setNumber(number);

                User user;
                List<Address> addresses;
                Random random = new Random();

                do {
                    Long idUser = random.nextLong(1,10);
                    Optional<User> optionalUser = userRepository.findById(idUser);
                    user = optionalUser.orElseThrow();
                    addresses = addressRepository.findAllByUser(user);
                } while (addresses.isEmpty());

                purchase.setUser(user);
                purchase.setAddress(addresses.getFirst());

                LocalDateTime localDateTime = getRandomDate(faker,0,4);
                purchase.setCreatedAt(localDateTime);
                
                int year = user.getCreatedAt().getYear();
                int currentYear = LocalDate.now().getYear();
                int yearBetween = currentYear - year;
                purchase.setCreatedAt(getRandomDate(faker, 0, yearBetween));

                if (yearBetween > 1) {
                    purchase.setStatus(EnumPurchaseStatus.DELIVERED.getValue());
                } else {
                    int status = faker.number().numberBetween(1, 5);
                    switch (status) {
                        case 1:
                            purchase.setStatus(EnumPurchaseStatus.PENDING.getValue());
                            break;
                        case 2:
                            purchase.setStatus(EnumPurchaseStatus.PAID.getValue());
                            break;
                        case 3:
                            purchase.setStatus(EnumPurchaseStatus.SHIPPED.getValue());
                            break;
                        default:
                            purchase.setStatus(EnumPurchaseStatus.CANCELLED.getValue());
                            break;
                    }
                }

                purchaseRepository.save(purchase);
            }
            purchaseRepository.flush();
        }
    }


    private void createPurchaseProduct() {
        if (0 == purchaseProductRepository.count()) {
            List<Purchase> purchases = purchaseRepository.findAll();
            for (Purchase purchase : purchases) {
                createPurchaseProductByPurchase(purchase);

                do {
                    createPurchaseProductByPurchase(purchase);
                } while(hasProbability(1, 3));
            }

            purchaseProductRepository.flush();
        }
    }





    private LocalDateTime getRandomDate(Faker faker, int min, int max) {
        return new java.sql.Date(faker.date().birthday(min, max).getTime()).toLocalDate().atTime(0, 0);
    }
    private boolean hasProbability(int expected, int max){
        Random random = new Random();

        return random.nextInt(max) <= expected;
    }
    private void createImagesByProduct(Product product, boolean isDefault) {
        Image image = new Image();
        Random rand = new Random();
        image.setPath("https://picsum.photos/400/300?r=" + rand.nextInt(300));
        image.setExtension("png");
        image.setIsDefault(isDefault);
        image.setProduct(product);
        imageRepository.save(image);
    }
    private void createPurchaseProductByPurchase(Purchase purchase) {
        Faker faker = new Faker(Locale.of("fr"));
        PurchaseProduct purchaseProduct = new PurchaseProduct();
        purchaseProduct.setPurchase(purchase);
        purchaseProduct.setQuantity(faker.number().numberBetween(1, 6));

        Optional<Product> productOptional = productRepository.findById(faker.number().numberBetween(1L, 50L));
        productOptional.ifPresent(purchaseProduct::setProduct);

        int priceProduct = purchaseProduct.getProduct().getPrice();
        purchaseProduct.setPrice(priceProduct);
        if (hasProbability(1, 6)) {
            purchaseProduct.setPrice((int)(priceProduct * 1.2));
        }

        purchaseProductRepository.save(purchaseProduct);
    }




}
