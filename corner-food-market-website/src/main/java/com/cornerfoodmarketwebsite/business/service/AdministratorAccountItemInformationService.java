package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.dto.request.form.AdministratorAddItemForm;
import com.cornerfoodmarketwebsite.business.service.utils.FileTypeEnum;
import com.cornerfoodmarketwebsite.data.domain.entity.AdministratorAccountItemInformation;
import com.cornerfoodmarketwebsite.data.domain.repository.AdministratorAccountItemInformationRepository;
import com.cornerfoodmarketwebsite.data.single_table.entity.FileRelativePath;
import com.cornerfoodmarketwebsite.data.single_table.entity.Item;
import com.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;
import com.cornerfoodmarketwebsite.data.single_table.entity.ItemInventory;
import com.cornerfoodmarketwebsite.data.single_table.repository.FileRelativePathRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.ItemImageRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.ItemInventoryRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.ItemRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class AdministratorAccountItemInformationService {


    private final HttpServletRequest httpServletRequest;

    private final AdministratorAccountItemInformationRepository administratorAccountItemInformationRepository;
    private final ItemRepository itemRepository;
    private final ItemInventoryRepository itemInventoryRepository;
    private final FileRelativePathRepository fileRelativePathRepository;
    private final ItemImageRepository itemImageRepository;

    private final AwsS3BucketStorageService awsS3BucketStorageService;

//    private static final String clientsMediaDirectory = System.getProperty("user.dir") + "/src/main/resources/static/";
    private static final String awsS3BucketClientsMediaDirectory = "api/client-specific/";

    @Autowired
    public AdministratorAccountItemInformationService(HttpServletRequest httpServletRequest, AdministratorAccountItemInformationRepository administratorAccountItemInformationRepository, ItemRepository itemRepository, ItemInventoryRepository itemInventoryRepository, FileRelativePathRepository fileRelativePathRepository, ItemImageRepository itemImageRepository, AwsS3BucketStorageService awsS3BucketStorageService) {
        this.httpServletRequest = httpServletRequest;
        this.administratorAccountItemInformationRepository = administratorAccountItemInformationRepository;
        this.itemRepository = itemRepository;
        this.itemInventoryRepository = itemInventoryRepository;
        this.fileRelativePathRepository = fileRelativePathRepository;
        this.itemImageRepository = itemImageRepository;
        this.awsS3BucketStorageService = awsS3BucketStorageService;
    }

    public Iterable<AdministratorAccountItemInformation> getItemsInformation() {
        return this.administratorAccountItemInformationRepository.findAllOnSale();
    }

    public boolean removeOnSaleItem(int itemId) {
        return this.itemRepository.setIsOnSaleToFalseByItemId((short) itemId) > 0;
    }

    public void addNewItem(AdministratorAddItemForm administratorAddItemForm) throws URISyntaxException, IOException {
        ItemInventory newItemInventory = new ItemInventory(administratorAddItemForm.getItemQuantity());
//        newItemInventory = this.itemInventoryRepository.saveAndFlush(newItemInventory);
//        this.itemInventoryRepository.refresh(newItemInventory);
        Item newItem = new Item(administratorAddItemForm.getItemTitle(), administratorAddItemForm.getItemDescription(), administratorAddItemForm.getItemSku(), administratorAddItemForm.getItemCategoryId(), newItemInventory, administratorAddItemForm.getItemPrice(), true, true);
        newItem = this.itemRepository.save(newItem);

        String requestUrl = this.httpServletRequest.getHeader("Access-Control-Allow-Origin");
        URI requestUri = new URI(requestUrl);
        String requestHost = requestUri.getHost();
        String requestDomain = requestHost.startsWith("www.") ? requestHost.substring(4) : requestHost;
        final String newItemImageFileRelativePath = awsS3BucketClientsMediaDirectory + requestDomain + "/images/items/";

        FileRelativePath newFileRelativePath;
        if (this.fileRelativePathRepository.existsByRelativePath(newItemImageFileRelativePath)) {
            newFileRelativePath = this.fileRelativePathRepository.getFileRelativePathByRelativePath(newItemImageFileRelativePath);
        } else {
            newFileRelativePath = new FileRelativePath(FileTypeEnum.IMAGE.getFileTypeId(), newItemImageFileRelativePath);
            newFileRelativePath = this.fileRelativePathRepository.save(newFileRelativePath);
        }
        MultipartFile newItemImageMultipartFile = administratorAddItemForm.getItemImageFile();
        ItemImage newItemImage = new ItemImage(newItem.getId(), FilenameUtils.getExtension(newItemImageMultipartFile.getOriginalFilename()), (short) 1, newFileRelativePath);
        newItemImage = this.itemImageRepository.save(newItemImage);

        System.out.println(newItemImage.generateNewFileName());
        this.awsS3BucketStorageService.uploadFile(newItemImageFileRelativePath + newItemImage.generateNewFileName(), newItemImageMultipartFile);
        System.out.println("New iteam Id: " + newItem.getId());
    }
}
