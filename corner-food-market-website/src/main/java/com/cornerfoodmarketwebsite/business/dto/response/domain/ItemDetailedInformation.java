package com.cornerfoodmarketwebsite.business.dto.response.domain;

import java.util.ArrayList;

public class ItemDetailedInformation extends ItemInformation{

    private String description;
    private Iterable<String> imagesSourceRelativePathNames;

    public ItemDetailedInformation(String name, String description, String sku, double price, boolean isPopular,
                                   Boolean isPercentageBasedDiscount, Double discountPercent, Double discountAmount,
                                   short quantity, String categoryName, String categoryUrlRouteName,
                                   String mainImageRelativePath, String mainImageFileName,
                                   ArrayList<String> imagesFileRelativePaths, ArrayList<String> imagesFileNames) {

        super(name, sku, price, isPopular, isPercentageBasedDiscount, discountPercent, discountAmount, quantity,
                categoryName, categoryUrlRouteName, mainImageRelativePath, mainImageFileName);

        this.description = description;

        ArrayList<String> tempImagesSourceRelativePathNames = new ArrayList<>();

        for (int index = 0; index < imagesFileNames.size(); index ++) {
            tempImagesSourceRelativePathNames.add(imagesFileRelativePaths.get(index) + imagesFileNames.get(index));
        }
        this.imagesSourceRelativePathNames = tempImagesSourceRelativePathNames;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Iterable<String> getImagesSourceRelativePathNames() {
        return imagesSourceRelativePathNames;
    }

    public void setImagesSourceRelativePathNames(ArrayList<String> imagesFileRelativePaths, ArrayList<String> imagesFileNames) {
        ArrayList<String> tempImagesSourceRelativePathNames = new ArrayList<>();

        for (int index = 0; index < imagesFileNames.size(); index ++) {
            tempImagesSourceRelativePathNames.add(imagesFileRelativePaths.get(index) + imagesFileNames.get(index));
        }
        this.imagesSourceRelativePathNames = tempImagesSourceRelativePathNames;
    }
}
