package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.data.domain.entity.AdministratorAccountItemInformation;
import com.cornerfoodmarketwebsite.data.domain.repository.AdministratorAccountItemInformationRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorAccountItemInformationService {

    private final AdministratorAccountItemInformationRepository administratorAccountItemInformationRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public AdministratorAccountItemInformationService(AdministratorAccountItemInformationRepository administratorAccountItemInformationRepository, ItemRepository itemRepository) {
        this.administratorAccountItemInformationRepository = administratorAccountItemInformationRepository;
        this.itemRepository = itemRepository;
    }

    public Iterable<AdministratorAccountItemInformation> getItemsInformation() {
        return this.administratorAccountItemInformationRepository.findAllOnSale();
    }

    public boolean removeOnSaleItem(int itemId) {
        return this.itemRepository.setIsOnSaleToFalseByItemId((short) itemId) > 0;
    }
}
