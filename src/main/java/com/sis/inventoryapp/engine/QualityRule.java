package com.sis.inventoryapp.engine;

import com.sis.inventoryapp.domain.Product;
import com.sis.inventoryapp.utils.TimeUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class QualityRule {

    public List<Product> execute(List<Product> productList) {
        List<Product> updatedProductList = new ArrayList<>();

        for (Product product : productList) {
            switch (product.getName()) {
                case "Aged Brie":
                    updateAgedBrie(product);
                    break;
                case "Backstage passes":
                    updateBackstagePass(product);
                    break;
                case "Sulfuras": //No change
                    break;
                case "Normal Product":
                    updateNormalProduct(product);
                    break;
                case "Conjured":
                    updateConjured(product);
                    break;
                default:
                    product.setName("NO SUCH ITEM");
                    break;
            }
            updatedProductList.add(product);
        }
        return updatedProductList;
    }

    private void updateAgedBrie(Product product) {
        long diffInDays = calculateDiffInDays(product);
        if (diffInDays > 0 && product.getQuality() < 50) {
            long updatedQuality = product.getQuality() + diffInDays;
            product.setQuality(updatedQuality);
        }
    }

    private void updateBackstagePass(Product product) {
        long updatedQuality;
        long diffInDays = calculateDiffInDays(product);
        long sellInDate = product.getSellInDate();
        long quality = product.getQuality();

        if (diffInDays > 0 && sellInDate <= 10 && sellInDate > 5) {
            updatedQuality = quality + (2 * diffInDays);
            product.setQuality(updatedQuality);
        } else if (diffInDays > 0 && sellInDate <= 5 && sellInDate > 0) {
            updatedQuality = quality + (3 * diffInDays);
            product.setQuality(updatedQuality);
        } else if (sellInDate <= 0) {
            product.setQuality(0);
        } else {
            updatedQuality = quality + diffInDays;
            product.setQuality(updatedQuality);
        }
    }

    private void updateNormalProduct(Product product) {
        long diffInDays = calculateDiffInDays(product);
        if (diffInDays > 0) {
            long updatedQuality = product.getQuality() - diffInDays;
            product.setQuality(updatedQuality);
        }
    }

    private void updateConjured(Product product) {
        long diffInDays = calculateDiffInDays(product);
        if (diffInDays > 0) {
            long updatedQuality = product.getQuality() - (2 * diffInDays);
            product.setQuality(updatedQuality);
        }
    }


    private Long calculateDiffInDays(Product product) {
        Date creationDate = Date.from(product.getCreatedOn());
        return TimeUtils.findTimeDifferenceInDays(creationDate);
    }
}