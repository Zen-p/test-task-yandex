package com.example.test.task.components.repositories;


import com.example.test.task.components.schemas.SystemItemHistoryUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<SystemItemHistoryUnit, String> {

    @Query("SELECT si FROM SystemItemHistoryUnit si WHERE si.date >= (CURRENT_TIMESTAMP - 24 HOUR) AND si.date <= CURRENT_TIMESTAMP")
    Optional<List<SystemItemHistoryUnit>> findUpdates();

}
