package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.utils.TimeUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SellInRule {

    public List<Product> execute(List<Product> productList){
        long diffInDays;
        Date creationDate;

        for(Product product: productList) {
            creationDate = Date.from(product.getCreatedOn());
            diffInDays = TimeUtils.findTimeDifferenceInDays(creationDate);
            if (diffInDays > 0) {
                long newSellInDate = product.getSellInDate() - diffInDays;
                product.setSellInDate(newSellInDate);
            }
        }
        return productList;
    }
}
