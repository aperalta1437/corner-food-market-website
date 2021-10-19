package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.data.domain.entity.AdminAccountItemInformation;
import com.cornerfoodmarketwebsite.data.domain.repository.AdminAccountItemInformationRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminAccountItemInformationService {

    private final AdminAccountItemInformationRepository adminAccountItemInformationRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public AdminAccountItemInformationService(AdminAccountItemInformationRepository adminAccountItemInformationRepository, ItemRepository itemRepository) {
        this.adminAccountItemInformationRepository = adminAccountItemInformationRepository;
        this.itemRepository = itemRepository;
    }

    public Iterable<AdminAccountItemInformation> getItemsInformation() {
        return this.adminAccountItemInformationRepository.findAllOnSale();
    }

    public boolean removeOnSaleItem(int itemId) {
        return this.itemRepository.setIsOnSaleToFalseByItemId((short) itemId) > 0;
    }
}
