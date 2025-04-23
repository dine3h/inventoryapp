package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class SellInRule implements Rule{

    public List<Product> execute(List<Product> productList){
        long diffInDays;
        Date creationDate;
        LocalDateTime now = LocalDateTime.now();
        Date nowDate = java.util.Date
                            .from(now.atZone(ZoneId.systemDefault()).toInstant());

        for(Product product: productList) {
            creationDate = Date.from(product.getCreatedOn());
            diffInDays = findTimeDifferenceInDays(nowDate, creationDate);
            if (diffInDays > 0) {
                long newSellInDate = product.getSellInDate() - diffInDays;
                product.setSellInDate(newSellInDate);
            }
        }
        return productList;
    }

    private Long findTimeDifferenceInDays(Date now, Date creationTimestamp) {
        long diffInMillies = Math.abs(now.getTime() - creationTimestamp.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
