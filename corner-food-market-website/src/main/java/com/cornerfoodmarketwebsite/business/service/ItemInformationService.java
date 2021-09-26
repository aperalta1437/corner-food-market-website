package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import com.cornerfoodmarketwebsite.data.domain.repository.ItemDetailedInformationRepository;
import com.cornerfoodmarketwebsite.data.domain.repository.ItemInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ItemInformationService {

    private final ItemInformationRepository itemInformationRepository;
    private final ItemDetailedInformationRepository itemDetailedInformationRepository;

    @Autowired
    public ItemInformationService(ItemInformationRepository itemInformationRepository,
                                  ItemDetailedInformationRepository itemDetailedInformationRepository) {
        this.itemInformationRepository = itemInformationRepository;
        this.itemDetailedInformationRepository = itemDetailedInformationRepository;
    }

    public List<List<ItemInformation>> getItemsInformation() {
        Iterable<ItemInformation> itemInformationList = this.itemInformationRepository.findAllOnSale();

        List<List<ItemInformation>> itemInformationLists = new ArrayList<>();

        AtomicInteger listNumber = new AtomicInteger(-1);
        AtomicInteger itemNumber = new AtomicInteger();
        itemInformationList.forEach(itemInformation -> {
            if ((itemNumber.get() % 4) == 0) {
                itemInformationLists.add(new ArrayList<>());
                listNumber.getAndIncrement();
            }

            itemInformationLists.get(listNumber.get()).add(itemInformation);
            itemNumber.getAndIncrement();
        });

        return itemInformationLists;
    }

    public ItemDetailedInformation getItemDetailedInformation(String itemSku) {
        ItemDetailedInformation itemDetailedInformation = this.itemDetailedInformationRepository.findBySku(itemSku);

        return itemDetailedInformation;
    }
}
