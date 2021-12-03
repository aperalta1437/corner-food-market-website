package com.cornerfoodmarketwebsite.data.domain.repository;

import com.cornerfoodmarketwebsite.data.domain.entity.AdministratorAccountItemInformation;
import com.cornerfoodmarketwebsite.data.domain.utils.ShoppingCartItemsList;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorAccountItemInformationRepository extends CustomJpaRepository<AdministratorAccountItemInformation, Short> {
//    @Query(value = "SELECT I1 FROM AccountItemInformation I1 LEFT JOIN I1.cartItems CI1 WITH CI1.itemId = I1.id AND CI1.customerId = :customerId WHERE I1.isOnSale = true and I1.quantity > 0")
//    Iterable<AccountItemInformation> findAllOnSale(@Param("customerId") short customerId);

    @Query(value = "SELECT AAII1 FROM AdministratorAccountItemInformation AAII1 WHERE AAII1.isOnSale = true AND AAII1.quantity > 0")
    Iterable<AdministratorAccountItemInformation> findAllOnSale();

    @Query(value = "SELECT I1 FROM AccountItemInformation I1 WHERE I1.customerId = ?1")
    ShoppingCartItemsList findAllInCart(short customerId);
}
