package mock;

import com.ecommerce.microcommerce.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductMock {

    private final List<Product> products;       //Should be ProductDto
    private static Product p1;
    private static Product p2;
    private static Product p3;
    private static Product p4;


    public ProductMock() {
        resetProduct();
        products = initProducts();
    }

    public void resetProduct() {
        p1 = new Product(1,"table", 100,50);
        p2 = new Product(2,"chaise", 200,120);
        p3 = new Product(3,"lampe", 20,5);
        p4 = new Product(4,"chandelier", 10,5);
    }

    public List<Product> initProducts() {
        final List<Product> products = new ArrayList<Product>();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        return products;
    }


    public List<Product> getProducts() {
        return products;
    }

    public static Product getP1() {
        return p1;
    }

    public static Product getP2() {
        return p2;
    }

    public static Product getP3() {
        return p3;
    }

    public static Product getP4() {
        return p4;
    }

//    public List<Product> getAllUsers() {
//        return products.stream()
//                .map(UserConverter::dtoToUser)
//                .collect(Collectors.toList());
//    }

}