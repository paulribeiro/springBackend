package com.resume;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

    /*
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
*/
}

