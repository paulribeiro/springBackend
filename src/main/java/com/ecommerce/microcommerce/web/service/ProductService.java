package com.ecommerce.microcommerce.web.service;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public Product GetProductWithMoreMoney(List<Product> products)
    {
        int maxMoneyWin = 0;
        int moneyWin = 0;
        Product maxMoneyWinProduct = new Product();
        for(Product product : products)
        {
            moneyWin = product.getPrix() - product.getPrixAchat();
            if(moneyWin > maxMoneyWin)
            {
                maxMoneyWin = moneyWin;
                maxMoneyWinProduct = product;
            }
        }
        return maxMoneyWinProduct;
    }
}
