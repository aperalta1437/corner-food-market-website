package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.NewAdministratorRequest;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NewAdministratorRequestRepository extends CustomJpaRepository<NewAdministratorRequest, Short> {
    boolean existsByUuid(String uuid);

    @Query(value = "SELECT NAR1 FROM NewAdministratorRequest NAR1 WHERE NAR1.uuid = ?1")
    NewAdministratorRequest getNewAdministratorRequestByUuid(String uuid);
}
