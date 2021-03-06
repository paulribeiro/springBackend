package com.ecommerce.microcommerce;

import com.ecommerce.microcommerce.dto.ProductDto;
import com.ecommerce.microcommerce.interfaces.IProductService;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.repository.ProductRepository;
import com.ecommerce.microcommerce.web.service.ProductService;
import mock.ProductMock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    private ProductService productService;
    private static ProductMock productMock;

    @MockBean
    private ProductRepository productRepository;

    @BeforeAll
    public static void initTestClass() {
        productMock = new ProductMock();
    }

    @BeforeEach
    public void initEachTest() {
        productService = new ProductService();
    }

    @Test
    @DisplayName("The product service should use the product repository to retrieve all products")
    public void testFindMaxMoneyProduct() {
        int countUsers = productMock.getProducts().size();
       // when(productRepository.findAll()).thenReturn(productMock.getProducts());

        Product product = productService.GetProductWithMoreMoney(productMock.getProducts());
        Assertions.assertThat(product).isNotNull();
        if(product != null)
        {
            Assertions.assertThat(product).isEqualTo(productMock.getP2());
        }
    }

}

