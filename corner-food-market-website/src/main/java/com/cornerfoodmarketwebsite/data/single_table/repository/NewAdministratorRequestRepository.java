package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.NewAdministratorRequest;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewAdministratorRequestRepository extends CustomJpaRepository<NewAdministratorRequest, Short> {

    /**
     * Gets the latest new administrator request record based on specified email.
     * NOTE: nativeQuery = true is needed to limit the results and avoid query creation programmatically.
     * @param email The email of the new administrator.
     * @return The new administrator request database record.
     */
    @Query(nativeQuery = true, value = "SELECT NAR1.* FROM NEW_ADMINISTRATOR_REQUEST NAR1 WHERE NAR1.EMAIL = ?1 ORDER BY NAR1.CREATED_AT DESC LIMIT 1")
    NewAdministratorRequest getLatestNewAdministratorRequestByEmail(String email);
}
