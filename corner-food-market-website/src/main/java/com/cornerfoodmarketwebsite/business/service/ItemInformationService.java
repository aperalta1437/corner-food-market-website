package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.service.utils.ItemsInformationEnum;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import com.cornerfoodmarketwebsite.data.domain.repository.ItemDetailedInformationRepository;
import com.cornerfoodmarketwebsite.data.domain.repository.ItemInformationRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.BannerImageRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.implementation.BannerImageRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

import static java.util.Map.entry;

@Service
public class ItemInformationService {

    private final ItemInformationRepository itemInformationRepository;
    private final ItemDetailedInformationRepository itemDetailedInformationRepository;
    private final BannerImageRepository bannerImageRepository;
    private final BannerImageRepositoryImplementation bannerImageRepositoryImplementation;

    @Autowired
    public ItemInformationService(ItemInformationRepository itemInformationRepository,
                                  ItemDetailedInformationRepository itemDetailedInformationRepository, BannerImageRepository bannerImageRepository, BannerImageRepositoryImplementation bannerImageRepositoryImplementation) {
        this.itemInformationRepository = itemInformationRepository;
        this.itemDetailedInformationRepository = itemDetailedInformationRepository;
        this.bannerImageRepository = bannerImageRepository;
        this.bannerImageRepositoryImplementation = bannerImageRepositoryImplementation;
    }

    public Map<ItemsInformationEnum, List<? extends Serializable>> getPopularItemsInformation() {
        List<ItemInformation> itemInformationList = this.itemInformationRepository.findAllOnSale();
        List<Integer> discountsIds = new ArrayList<>();
        itemInformationList.forEach(itemInformation -> {
            itemInformation.getDiscounts().forEach(discount -> {
                discountsIds.add(discount.getId());
            });
        });
        return Map.ofEntries(entry(ItemsInformationEnum.ITEMS, itemInformationList),
                entry(ItemsInformationEnum.BANNERS, this.bannerImageRepositoryImplementation.getPageBannerImages(discountsIds)));
    }

    public Map<ItemsInformationEnum, List<? extends Serializable>> getSearchResultsItemsInformation(String itemsSearchQuery) {
        List<ItemInformation> itemInformationList = this.itemInformationRepository.findAllOnSale();
        List<Integer> discountsIds = new ArrayList<>();
        itemInformationList.forEach(itemInformation -> {
            itemInformation.getDiscounts().forEach(discount -> {
                discountsIds.add(discount.getId());
            });
        });
        return Map.ofEntries(entry(ItemsInformationEnum.ITEMS, itemInformationList),
                entry(ItemsInformationEnum.BANNERS, this.bannerImageRepositoryImplementation.getPageBannerImages(discountsIds)));
    }

    public ItemDetailedInformation getCategoryItemsInformation(String itemUpc) {

        return this.itemDetailedInformationRepository.findByUpc(itemUpc);
    }

    public ItemDetailedInformation getCategorySearchResultsItemsInformation(String itemUpc, String itemsSearchQuery) {

        return this.itemDetailedInformationRepository.findByUpc(itemUpc);
    }

    public ItemDetailedInformation getItemDetailedInformation(String itemUpc) {

        return this.itemDetailedInformationRepository.findByUpc(itemUpc);
    }
}
