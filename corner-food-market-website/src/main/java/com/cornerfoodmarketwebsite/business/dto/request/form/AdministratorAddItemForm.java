package com.cornerfoodmarketwebsite.business.dto.request.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AdministratorAddItemForm {
    private String itemTitle;
    private String itemDescription;
    private double itemPrice;
    private short itemCategoryId;
    private String itemUpc;
    private short itemQuantity;
    private MultipartFile itemImageFile;
}
