package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.service.AdministratorAccountItemInformationService;
import com.cornerfoodmarketwebsite.data.domain.entity.AdministratorAccountItemInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/account")
@CrossOrigin(origins = "http://localhost:3000")
public class AdministratorAccountHomeController {

    private final AdministratorAccountItemInformationService administratorAccountItemInformationService;

    @Autowired
    public AdministratorAccountHomeController(AdministratorAccountItemInformationService administratorAccountItemInformationService) {
        this.administratorAccountItemInformationService = administratorAccountItemInformationService;
    }

    @GetMapping
    public Iterable<AdministratorAccountItemInformation> getOnSaleItemsList() {
        return this.administratorAccountItemInformationService.getItemsInformation();
    }

    @PostMapping(value = "/remove-item/{item-id}")
    public ResponseEntity<Object> removeOnSaleItem(@PathVariable(value = "item-id") String itemId) {
        System.out.println("Inside removeOnSaleItem");
        if (this.administratorAccountItemInformationService.removeOnSaleItem(Integer.parseInt(itemId))) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
