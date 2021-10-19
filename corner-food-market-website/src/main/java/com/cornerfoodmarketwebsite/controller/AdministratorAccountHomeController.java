package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.service.AdminAccountItemInformationService;
import com.cornerfoodmarketwebsite.data.domain.entity.AdminAccountItemInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/account")
@CrossOrigin(origins = "http://localhost:3000")
public class AdministratorAccountHomeController {

    private final AdminAccountItemInformationService adminAccountItemInformationService;

    @Autowired
    public AdministratorAccountHomeController(AdminAccountItemInformationService adminAccountItemInformationService) {
        this.adminAccountItemInformationService = adminAccountItemInformationService;
    }

    @GetMapping
    public Iterable<AdminAccountItemInformation> getOnSaleItemsList() {
        return this.adminAccountItemInformationService.getItemsInformation();
    }

    @PostMapping(value = "/remove-item/{item-id}")
    public ResponseEntity<Object> removeOnSaleItem(@PathVariable(value = "item-id") String itemId) {
        System.out.println("Hello");
        if (this.adminAccountItemInformationService.removeOnSaleItem(Integer.parseInt(itemId))) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
