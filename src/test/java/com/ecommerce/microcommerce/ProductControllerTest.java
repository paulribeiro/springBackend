package com.ecommerce.microcommerce;

import com.ecommerce.microcommerce.interfaces.IProductService;
import com.ecommerce.microcommerce.web.controller.ControllerResourceHandler;
import com.ecommerce.microcommerce.web.exceptions.ControllerTestException;
import mock.ProductMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    private static ProductMock productMock = new ProductMock();

    private ControllerResourceHandler controllerResourceHandler;

//    @BeforeEach
//    public void initEachTest() {
//        productMock.resetProduct();
//        controllerResourceHandler = new ControllerResourceHandler(mockMvc);
//    }
/*
    @Test
    @DisplayName("Retrieving all products from the product controller should return the same content as the product service")
    public void retrieveAllUsersShouldReturnAllUsersKnownByTheUserService200() throws ControllerTestException, IOException {
       // String content = controllerResourceHandler.retrieveResourceContent("user/GET_200_all_users.json");
        when(productService.GetProductWithMoreMoney()).thenReturn(productMock.getProducts());
        //controllerResourceHandler.performAndExpectStatusAndContentAndThrowException(get(USER_API_URL), status().isOk(), content().json(content));
    }*/
}
