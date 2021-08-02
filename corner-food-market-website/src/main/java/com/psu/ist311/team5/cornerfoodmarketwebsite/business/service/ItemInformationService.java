package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository.ItemDetailedInformationRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository.ItemInformationRepository;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ItemInformationService {

    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemInventoryRepository itemInventoryRepository;
    private final DiscountRepository discountRepository;
    private final FileRelativePathRepository fileRelativePathRepository;
    private final ItemImageRepository itemImageRepository;
    private final ItemInformationRepository itemInformationRepository;
    private final ItemDetailedInformationRepository itemDetailedInformationRepository;


    @Autowired
    public ItemInformationService(ItemRepository itemRepository, ItemCategoryRepository itemCategoryRepository,
                                  ItemInventoryRepository itemInventoryRepository, DiscountRepository discountRepository,
                                  FileRelativePathRepository fileRelativePathRepository, ItemImageRepository itemImageRepository,
                                  ItemInformationRepository itemInformationRepository, ItemDetailedInformationRepository itemDetailedInformationRepository) {
        this.itemRepository = itemRepository;
        this.itemCategoryRepository = itemCategoryRepository;
        this.itemInventoryRepository = itemInventoryRepository;
        this.discountRepository = discountRepository;
        this.fileRelativePathRepository = fileRelativePathRepository;
        this.itemImageRepository = itemImageRepository;
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


//        Item item = this.itemRepository.findBySku(itemSku);
//
//        Optional<ItemCategory> itemCategory = this.itemCategoryRepository.findById(item.getCategoryId());
//        Optional<ItemInventory> itemInventory = this.itemInventoryRepository.findById(item.getInventoryId());
//
//        Short discountId = item.getDiscountId();
//        Optional<Discount> discount;
//        Boolean isPercentageBased;
//        Double discountPercent;
//        Double discountAmount;
//        if (discountId != null) {
//            discount = Optional.of(this.discountRepository.getById(discountId));
//            isPercentageBased = discount.get().getIsPercentageBased();
//            discountPercent = discount.get().getDiscountPercent();
//            discountAmount = discount.get().getDiscountAmount();
//        } else {
//            discount = Optional.empty();
//            isPercentageBased = null;
//            discountPercent = null;
//            discountAmount = null;
//        }
//        Optional<Iterable<ItemImage>> itemImages = Optional.of(this.itemImageRepository.getSortedItemImagesByItemId(item.getId()));
//        ArrayList<String> imagesFileNames = new ArrayList<>();
//        ArrayList<String> imagesFileRelativePaths = new ArrayList<>();
//        itemImages.get().forEach(itemImage -> {
//            imagesFileNames.add(itemImage.getFileName());
//            imagesFileRelativePaths.add(this.fileRelativePathRepository.getById(itemImage.getRelativePathId()).getRelativePath());
//        });
//
//        ItemDetailedInformation itemDetailedInformation = new ItemDetailedInformation(
//                item.getName(), item.getDescription(), item.getSku(), item.getPrice(), item.getIsPopular(),
//                isPercentageBased, discountPercent, discountAmount, itemInventory.get().getQuantity(),
//                itemCategory.get().getName(), itemCategory.get().getUrlRouteName(),
//                imagesFileRelativePaths.get(0), imagesFileNames.get(0), imagesFileRelativePaths, imagesFileNames);

        return itemDetailedInformation;
    }
}
