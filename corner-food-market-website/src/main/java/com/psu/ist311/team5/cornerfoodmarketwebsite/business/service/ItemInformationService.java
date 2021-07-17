package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain.ItemDetailedInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain.ItemInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.*;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.*;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ItemInformationService {

    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemInventoryRepository itemInventoryRepository;
    private final DiscountRepository discountRepository;
    private final FileRelativePathRepository fileRelativePathRepository;
    private final ItemImageRepository itemImageRepository;


    @Autowired
    public ItemInformationService(ItemRepository itemRepository, ItemCategoryRepository itemCategoryRepository,
                                  ItemInventoryRepository itemInventoryRepository, DiscountRepository discountRepository,
                                  FileRelativePathRepository fileRelativePathRepository, ItemImageRepository itemImageRepository) {
        this.itemRepository = itemRepository;
        this.itemCategoryRepository = itemCategoryRepository;
        this.itemInventoryRepository = itemInventoryRepository;
        this.discountRepository = discountRepository;
        this.fileRelativePathRepository = fileRelativePathRepository;
        this.itemImageRepository = itemImageRepository;
    }

    public List<List<ItemInformation>> getItemsInformation() {
        Iterable<Item> items = this.itemRepository.findAllOnSale();
        List<List<ItemInformation>> itemInformationLists = new ArrayList<>();

        AtomicInteger listNumber = new AtomicInteger(-1);
        AtomicInteger itemNumber = new AtomicInteger();
        items.forEach(item -> {
            Optional<ItemCategory> itemCategory = this.itemCategoryRepository.findById(item.getCategoryId());
            Optional<ItemInventory> itemInventory = this.itemInventoryRepository.findById(item.getInventoryId());

            Short discountId = item.getDiscountId();
            Optional<Discount> discount;
            Boolean isPercentageBased;
            Double discountPercent;
            Double discountAmount;
            if (discountId != null) {
                discount = Optional.of(this.discountRepository.getById(discountId));
                isPercentageBased = discount.get().getIsPercentageBased();
                discountPercent = discount.get().getDiscountPercent();
                discountAmount = discount.get().getDiscountAmount();
            } else {
                discount = Optional.empty();
                isPercentageBased = null;
                discountPercent = null;
                discountAmount = null;
            }
            Optional<ItemImage> itemImage = Optional.of(this.itemImageRepository.getMainItemImageByItemId(item.getId()));
            Optional<FileRelativePath> fileRelativePath = Optional.of(this.fileRelativePathRepository.getById(itemImage.get().getRelativePathId()));

            ItemInformation itemInformation = new ItemInformation(
                    item.getName(), item.getSku(), item.getPrice(), item.getIsPopular(),
                    isPercentageBased, discountPercent, discountAmount, itemInventory.get().getQuantity(),
                    itemCategory.get().getName(), itemCategory.get().getUrlRouteName(), itemImage.get().getFileName(),
                    fileRelativePath.get().getRelativePath());



            if ((itemNumber.get() % 4) == 0) {
                itemInformationLists.add(new ArrayList<ItemInformation>());
                listNumber.getAndIncrement();
            }

            itemInformationLists.get(listNumber.get()).add(itemInformation);
            itemNumber.getAndIncrement();
        });

        return itemInformationLists;
    }

    public ItemDetailedInformation getItemDetailedInformation(String itemSku) {
        Item item = this.itemRepository.findBySku(itemSku);

        Optional<ItemCategory> itemCategory = this.itemCategoryRepository.findById(item.getCategoryId());
        Optional<ItemInventory> itemInventory = this.itemInventoryRepository.findById(item.getInventoryId());

        Short discountId = item.getDiscountId();
        Optional<Discount> discount;
        Boolean isPercentageBased;
        Double discountPercent;
        Double discountAmount;
        if (discountId != null) {
            discount = Optional.of(this.discountRepository.getById(discountId));
            isPercentageBased = discount.get().getIsPercentageBased();
            discountPercent = discount.get().getDiscountPercent();
            discountAmount = discount.get().getDiscountAmount();
        } else {
            discount = Optional.empty();
            isPercentageBased = null;
            discountPercent = null;
            discountAmount = null;
        }
        Optional<Iterable<ItemImage>> itemImages = Optional.of(this.itemImageRepository.getSortedItemImagesByItemId(item.getId()));
        ArrayList<String> imagesFileNames = new ArrayList<>();
        ArrayList<String> imagesFileRelativePaths = new ArrayList<>();
        itemImages.get().forEach(itemImage -> {
            imagesFileNames.add(itemImage.getFileName());
            imagesFileRelativePaths.add(this.fileRelativePathRepository.getById(itemImage.getRelativePathId()).getRelativePath());
        });

        ItemDetailedInformation itemDetailedInformation = new ItemDetailedInformation(
                item.getName(), item.getDescription(), item.getSku(), item.getPrice(), item.getIsPopular(),
                isPercentageBased, discountPercent, discountAmount, itemInventory.get().getQuantity(),
                itemCategory.get().getName(), itemCategory.get().getUrlRouteName(), imagesFileNames.get(0),
                imagesFileRelativePaths.get(0), imagesFileNames, imagesFileRelativePaths);

        return itemDetailedInformation;
    }
}
