package com.example.test.task.components;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemItemRepository extends JpaRepository<SystemItem, String> {


}
