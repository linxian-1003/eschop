package com.neu.order_service.fallback;

import com.neu.common.utils.JsonModel;
import com.neu.order_service.client.ProductServiceClient;
import com.neu.product_service.domain.Product;
import org.springframework.stereotype.Component;

import javax.xml.bind.util.JAXBSource;

@Component
public class ProductServiceClientFallback implements ProductServiceClient {


    @Override
    public Product findById(Integer productId) {
        Product product = new Product();
        product.setId(productId);
       product.setName(productId+"商品不足");
        return product;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }


}
