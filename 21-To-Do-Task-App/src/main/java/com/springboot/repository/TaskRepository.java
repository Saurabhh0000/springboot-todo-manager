package com.springboot.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.TaskEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    public List<TaskEntity> findByUserUserId(Integer userId);
    public List<TaskEntity> findByUserUserIdAndStatus(Integer userId, String status);
    Page<TaskEntity> findByUserUserId(Integer userId, Pageable pageable);


}




