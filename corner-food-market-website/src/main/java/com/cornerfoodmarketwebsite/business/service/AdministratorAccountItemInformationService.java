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
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AdministratorAccountItemInformationService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    private final AdministratorAccountItemInformationRepository administratorAccountItemInformationRepository;
    private final ItemRepository itemRepository;
    private final ItemInventoryRepository itemInventoryRepository;
    private final FileRelativePathRepository fileRelativePathRepository;
    private final ItemImageRepository itemImageRepository;
    private static final String clientsMediaDirectory = System.getProperty("user.dir") + "/src/main/resources/static/";

    @Autowired
    public AdministratorAccountItemInformationService(AdministratorAccountItemInformationRepository administratorAccountItemInformationRepository, ItemRepository itemRepository, ItemInventoryRepository itemInventoryRepository, FileRelativePathRepository fileRelativePathRepository, ItemImageRepository itemImageRepository) {
        this.administratorAccountItemInformationRepository = administratorAccountItemInformationRepository;
        this.itemRepository = itemRepository;
        this.itemInventoryRepository = itemInventoryRepository;
        this.fileRelativePathRepository = fileRelativePathRepository;
        this.itemImageRepository = itemImageRepository;
    }

    public Iterable<AdministratorAccountItemInformation> getItemsInformation() {
        return this.administratorAccountItemInformationRepository.findAllOnSale();
    }

    public boolean removeOnSaleItem(int itemId) {
        return this.itemRepository.setIsOnSaleToFalseByItemId((short) itemId) > 0;
    }

    public void addNewItem(AdministratorAddItemForm administratorAddItemForm) throws URISyntaxException {
        ItemInventory newItemInventory = new ItemInventory(administratorAddItemForm.getItemQuantity());
        newItemInventory = this.itemInventoryRepository.save(newItemInventory);
        Item newItem = new Item(administratorAddItemForm.getItemTitle(), administratorAddItemForm.getItemDescription(), administratorAddItemForm.getItemSku(), administratorAddItemForm.getItemCategoryId(), newItemInventory, administratorAddItemForm.getItemPrice(), true, true);
        newItem = this.itemRepository.save(newItem);

        String requestUrl = this.httpServletRequest.getHeader("Access-Control-Allow-Origin");
        URI requestUri = new URI(requestUrl);
        String requestHost = requestUri.getHost();
        String requestDomain = requestHost.startsWith("www.") ? requestHost.substring(4) : requestHost;
        String newItemImageFileRelativePath = "client-specific/" + requestDomain + "/images/items/";
        String clientItemsImagesDirectory = clientsMediaDirectory + newItemImageFileRelativePath;
        FileRelativePath newFileRelativePath;
        if (this.fileRelativePathRepository.existsByRelativePath(newItemImageFileRelativePath)) {
            newFileRelativePath = this.fileRelativePathRepository.getFileRelativePathByRelativePath(newItemImageFileRelativePath);
        } else {
            newFileRelativePath = new FileRelativePath(FileTypeEnum.IMAGE.getFileTypeId(), newItemImageFileRelativePath);
            newFileRelativePath = this.fileRelativePathRepository.save(newFileRelativePath);
        }
        MultipartFile newItemImageFile = administratorAddItemForm.getItemImageFile();
        ItemImage newItemImage = new ItemImage(newItem.getId(), FilenameUtils.getExtension(newItemImageFile.getOriginalFilename()), (short) 1, newFileRelativePath);
        newItemImage = this.itemImageRepository.saveAndFlush(newItemImage);
        this.itemImageRepository.refresh(newItemImage);
        System.out.println(newItemImage.getFileName());
        System.out.println(clientItemsImagesDirectory);
        this.saveItemImageFileToDirectory(clientItemsImagesDirectory, newItemImage.getFileName(), newItemImageFile);
        System.out.println("New iteam Id: " + newItem.getId());
    }

    public void saveItemImageFileToDirectory(String clientItemsImagesDirectory, String fileName, MultipartFile multipartFile) throws URISyntaxException {
        makeDirectoryIfNotExist(clientItemsImagesDirectory);
        Path fileNamePath = Paths.get(clientItemsImagesDirectory, fileName);

        try {
            Files.write(fileNamePath, multipartFile.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void makeDirectoryIfNotExist(String imageDirectory) {
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            System.out.println("Was directory created? " + directory.mkdirs());
        }
    }
}
