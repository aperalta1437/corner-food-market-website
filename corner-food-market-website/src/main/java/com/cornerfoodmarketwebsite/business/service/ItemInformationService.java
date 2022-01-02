package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.service.utils.ItemsInformationEnum;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import com.cornerfoodmarketwebsite.data.domain.repository.ItemDetailedInformationRepository;
import com.cornerfoodmarketwebsite.data.domain.repository.ItemInformationRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.BannerImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import static java.util.Map.entry;

@Service
public class ItemInformationService {

    private final ItemInformationRepository itemInformationRepository;
    private final ItemDetailedInformationRepository itemDetailedInformationRepository;
    private final BannerImageRepository bannerImageRepository;

    @Autowired
    public ItemInformationService(ItemInformationRepository itemInformationRepository,
                                  ItemDetailedInformationRepository itemDetailedInformationRepository, BannerImageRepository bannerImageRepository) {
        this.itemInformationRepository = itemInformationRepository;
        this.itemDetailedInformationRepository = itemDetailedInformationRepository;
        this.bannerImageRepository = bannerImageRepository;
    }

    public Map<ItemsInformationEnum, List<? extends Serializable>> getPopularItemsInformation() {
        List<ItemInformation> itemInformationList = this.itemInformationRepository.findAllOnSale();
        List<Integer> discountsIds = new ArrayList<>();
        itemInformationList.forEach(itemInformation -> {
            itemInformation.getDiscounts().forEach(discount -> {
                discountsIds.add(discount.getId());
            });
        });
//        EnumMap<ItemsInformationEnum>
        return Map.ofEntries(entry(ItemsInformationEnum.ITEMS, itemInformationList),
                entry(ItemsInformationEnum.BANNERS, this.bannerImageRepository.getPageBannerImages(discountsIds)));
//        return new ArrayList<List<Serializable>>(Arrays.<List<Serializable>>asList(new List[]{itemInformationList, this.bannerImageRepository.getPageBannerImages(discountsIds)}));
    }

    public List<List<ItemInformation>> getSearchResultsItemsInformation(String itemsSearchQuery) {
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

    public ItemDetailedInformation getCategoryItemsInformation(String itemSku) {
        ItemDetailedInformation itemDetailedInformation = this.itemDetailedInformationRepository.findBySku(itemSku);

        return itemDetailedInformation;
    }

    public ItemDetailedInformation getCategorySearchResultsItemsInformation(String itemSku, String itemsSearchQuery) {
        ItemDetailedInformation itemDetailedInformation = this.itemDetailedInformationRepository.findBySku(itemSku);

        return itemDetailedInformation;
    }

    public ItemDetailedInformation getItemDetailedInformation(String itemSku) {
        ItemDetailedInformation itemDetailedInformation = this.itemDetailedInformationRepository.findBySku(itemSku);

        return itemDetailedInformation;
    }
}
