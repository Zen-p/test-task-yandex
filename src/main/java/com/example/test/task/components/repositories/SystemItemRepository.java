package com.example.test.task.components.repositories;


import com.example.test.task.components.entities.SystemItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemItemRepository extends JpaRepository<SystemItem, String> {


}
