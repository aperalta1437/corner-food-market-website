package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.form.AdministratorAddItemForm;
import com.cornerfoodmarketwebsite.business.dto.response.domain.AdministratorAddItemFormItemCategoryInformation;
import com.cornerfoodmarketwebsite.business.service.AdministratorAccountItemInformationService;
import com.cornerfoodmarketwebsite.business.service.AdministratorItemCategoryService;
import com.cornerfoodmarketwebsite.business.service.ExceptionLogService;
import com.cornerfoodmarketwebsite.data.single_table.repository.ExceptionLogRepository;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/account/add-item")
public class AdministratorAccountAddItemController {
    private final AdministratorItemCategoryService administratorItemCategoryService;
    private final AdministratorAccountItemInformationService administratorAccountItemInformationService;

    private final ExceptionLogService exceptionLogService;

    @Autowired
    public AdministratorAccountAddItemController(AdministratorItemCategoryService administratorItemCategoryService, AdministratorAccountItemInformationService administratorAccountItemInformationService, ExceptionLogService exceptionLogService) {
        this.administratorItemCategoryService = administratorItemCategoryService;
        this.administratorAccountItemInformationService = administratorAccountItemInformationService;
        this.exceptionLogService = exceptionLogService;
    }

    @GetMapping(value = "/item-categories")
    public List<AdministratorAddItemFormItemCategoryInformation> getFormItemCategories() {
        return this.administratorItemCategoryService.getAddItemFormItemCategories();
    }

    @PostMapping(value = "/upload-new-item")
    public ResponseEntity<String> uploadNewItem(AdministratorAddItemForm administratorAddItemForm, HttpServletRequest httpServletRequest) {
        JSONObject jsonResponse = new JSONObject();

        try {
            this.administratorAccountItemInformationService.addNewItem(administratorAddItemForm);

            jsonResponse.put("Message", "New item was added successfully.");

            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
        } catch (Exception exception) {
            try {
                jsonResponse.put("Message", "An issue happened at the server. Please try again later. If the issue persist, please contact your system administrator");
            } catch (JSONException jsonException) {
                this.exceptionLogService.logException(jsonException);
                jsonException.printStackTrace();
                return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            this.exceptionLogService.logException(exception);
            exception.printStackTrace();

            return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
