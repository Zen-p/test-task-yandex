package com.example.test.task.components.repositories;


import com.example.test.task.components.schemas.SystemItem;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemItemRepository extends JpaRepository<SystemItem, String> {

    Optional<SystemItem> findById (@NotNull(message = "ID cannot be null") @Size(min = 1, max = 255, message = "ID length must be between 1 and 255 characters") String id);

}
