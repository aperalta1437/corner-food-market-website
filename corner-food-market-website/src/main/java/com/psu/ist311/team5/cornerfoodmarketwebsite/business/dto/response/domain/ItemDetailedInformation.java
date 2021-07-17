package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.FileRelativePath;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.ItemImage;

import java.util.ArrayList;

public class ItemDetailedInformation extends ItemInformation{

    private String description;
    private Iterable<String> imagesFileNames;
    private Iterable<String> imagesFileRelativePaths;

    public ItemDetailedInformation(String name, String description, String sku, double price, boolean isPopular,
                                   Boolean isPercentageBasedDiscount, Double discountPercent, Double discountAmount,
                                   short quantity, String categoryName, String categoryUrlRouteName,
                                   String mainImageFileName, String mainImageRelativePath,
                                   Iterable<String> imagesFileNames, Iterable<String> imagesFileRelativePaths) {

        super(name, sku, price, isPopular, isPercentageBasedDiscount, discountPercent, discountAmount, quantity,
                categoryName, categoryUrlRouteName, mainImageFileName, mainImageRelativePath);

        this.description = description;
        this.imagesFileNames = imagesFileNames;
        this.imagesFileRelativePaths = imagesFileRelativePaths;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Iterable<String> getImagesFileNames() {
        return imagesFileNames;
    }

    public void setImagesFileNames(Iterable<String> imagesFileNames) {
        this.imagesFileNames = imagesFileNames;
    }

    public Iterable<String> getImagesFileRelativePaths() {
        return imagesFileRelativePaths;
    }

    public void setImagesFileRelativePaths(Iterable<String> imagesFileRelativePaths) {
        this.imagesFileRelativePaths = imagesFileRelativePaths;
    }
}
